package com.utf8coding.healthcare.activities.secondary

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.MyApplication.Companion.context
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.adapters.MedSearchAdapter
import com.utf8coding.healthcare.data.MedData
import com.utf8coding.healthcare.view_models.MedSearchActivityViewModel
import com.utf8coding.healthcare.view_models.MedSearchActivityViewModel.Companion.BY_NAME
import com.utf8coding.healthcare.view_models.MedSearchActivityViewModel.Companion.BY_PRODUCER
import com.utf8coding.healthcare.view_models.MedSearchActivityViewModel.Companion.BY_TYPE
import kotlin.properties.Delegates

@Suppress("TypeParameterFindViewById")
class MedSearchActivity : AppCompatActivity() {
    lateinit var viewModel: MedSearchActivityViewModel
    private val searchButton: ImageView
        get() {
            return findViewById(R.id.searchButton) as ImageView
        }
    private val filterButton: ImageButton
        get() {
            return findViewById(R.id.filterButton) as ImageButton
        }
    private val recyclerView: RecyclerView
        get() {
            return findViewById(R.id.medSearchRecyclerView)
        }
    private val searchEditText: EditText
        get(){
            return findViewById(R.id.medSearchEditText) as EditText
        }
    private val clearInputButton: ImageButton
        get() {
            return findViewById(R.id.clearInputButton) as ImageButton
        }
    private val medListForAdapter: ArrayList<MedData> = ArrayList()
    private var medAdapter: MedSearchAdapter = MedSearchAdapter(medListForAdapter)
    private var preY by Delegates.notNull<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_search)
        viewModel = ViewModelProvider(this)[MedSearchActivityViewModel::class.java]
        window.statusBarColor = Color.TRANSPARENT

        searchButton.animate().alpha(1f)
            .setDuration(100).startDelay = 350

        initRecyclerView()
        searchButton.setOnClickListener {
            onSearch()
            val manager =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
        filterButton.setOnClickListener{ v: View ->
            showMenu(v, R.menu.menu_med_search_popup_filter)
        }
        searchEditText.addTextChangedListener{
            if (!it.isNullOrEmpty()) {
                clearInputButton.animate().alpha(1f).duration = 120
            } else {
                clearInputButton.animate().alpha(0f).duration = 120
            }
        }
        clearInputButton.setOnClickListener {
            searchEditText.setText("")
        }
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = medAdapter
        preY = recyclerView.y
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onSearch(){
        when (viewModel.searchMode){
            BY_NAME -> {
                viewModel.getMedListByName(searchEditText.text.toString())
            }
            BY_TYPE -> {
                viewModel.getMedListByType(searchEditText.text.toString())
            }
            BY_PRODUCER -> {
                viewModel.getMedListByManufacturer(searchEditText.text.toString())
            }
        }
        medListForAdapter.clear()
        medAdapter.notifyDataSetChanged()
        viewModel.medList.observe(this) {
            if (it.size != 0) {
                recyclerView.alpha = 0f
                medListForAdapter.clear()
                medListForAdapter.addAll(it)
                medAdapter.notifyDataSetChanged()
                recyclerView.y = preY + 150f
                recyclerView.alpha = 0f
                recyclerView.animate().alpha(1f).yBy(20f).setDuration(300).interpolator =
                    OvershootInterpolator(4f)
            }
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId){
                R.id.byMedName -> viewModel.searchMode = BY_NAME
                R.id.byType -> viewModel.searchMode = BY_TYPE
                R.id.byManufacturer -> viewModel.searchMode = BY_PRODUCER
                }
            true
        }
        popup.setOnDismissListener {}
        // Show the popup menu.
        popup.show()
    }

    //tools
    private fun makeToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun makeILog(msg: String){
        Log.i("MainActivity:", msg)
    }
}