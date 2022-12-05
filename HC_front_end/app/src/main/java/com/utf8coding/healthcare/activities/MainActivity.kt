package com.utf8coding.healthcare.activities

import android.graphics.Color
import android.os.Bundle
import android.os.health.HealthStats
import android.util.Log
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.fragments.BaseFragment
import com.utf8coding.healthcare.fragments.HealthFragment
import com.utf8coding.healthcare.fragments.InfoFragment
import com.utf8coding.healthcare.fragments.MineFragment
import com.utf8coding.healthcare.view_models.MainActivityViewModel
import com.utf8coding.healthcare.view_models.MainActivityViewModel.Companion.DOUBLE_BACK_PRESS_DELAY


class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainActivityViewModel
    @Suppress("TypeParameterFindViewById")
    private val fragmentContainer: FrameLayout
        get() {
            return findViewById(R.id.fragmentContainer) as FrameLayout
        }
    private val bottomNavigationBar: BottomNavigationView
        get() {
            return findViewById(R.id.bottomNavigationBar)
        }
    private lateinit var infoFragment: BaseFragment
    private lateinit var healthFragment: BaseFragment
    private lateinit var mineFragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overridePendingTransition(R.anim.activity_main_start_enter, R.anim.activity_main_start_exit)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        window.statusBarColor = Color.TRANSPARENT

        initFragments()
        initListeners()
    }

    override fun onBackPressed() {
        //双击返回键
        if (viewModel.firstBackPressedTime == 0L){
            viewModel.firstBackPressedTime = System.currentTimeMillis()
            makeToast(getString(R.string.再按一次返回键退出))
        } else {
            val timeDifference = System.currentTimeMillis() - viewModel.firstBackPressedTime
            if (timeDifference > DOUBLE_BACK_PRESS_DELAY){
                viewModel.firstBackPressedTime = 0L
            } else {
                ActivityCollector.finishAll()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    //init fragments:
    private fun initFragments(){
        viewModel.currentFragmentNumber = 1
        infoFragment = InfoFragment()
        healthFragment = HealthFragment()
        mineFragment = MineFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(R.id.fragmentContainer, infoFragment)
            .commit()
    }
    //init listener
    private fun initListeners(){
        bottomNavigationBar.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.info -> {
                    replaceFragment(1)
                    true
                }
                R.id.health -> {
                    replaceFragment(2)
                    true
                }
                R.id.mine -> {
                    replaceFragment(3)
                    true
                }
                else -> false
            }
        }
    }

    //function that replaces fragments:
    private fun replaceFragment(fragmentNumber: Int){
        fun commitTransaction(fragment: BaseFragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            val currentFragment: BaseFragment = when(viewModel.currentFragmentNumber){
                1 -> infoFragment
                2 -> healthFragment
                3 -> mineFragment
                else -> infoFragment //todo might be a hidden bug, come out an another idea.
            }
            transaction.remove(currentFragment)
            transaction.add(R.id.fragmentContainer, fragment)
                .commit()
        }

        if (viewModel.currentFragmentNumber == fragmentNumber) {
            when(fragmentNumber){
                1-> {
                    infoFragment.refresh()
                }
                2-> {
                    healthFragment.refresh()
                }
                3-> {
                    mineFragment.refresh()
                }
            }
        } else {
            when(fragmentNumber){
                1 -> {
                    commitTransaction(infoFragment)
                    viewModel.currentFragmentNumber = 1
                }
                2 -> {
                    commitTransaction(healthFragment)
                    viewModel.currentFragmentNumber = 2
                }
                3 -> {
                    commitTransaction(mineFragment)
                    viewModel.currentFragmentNumber = 3
                }
            }
        }
    }


    //tools:
    private fun makeToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun makeILog(msg: String){
        Log.i("MainActivity:", msg)
    }
}