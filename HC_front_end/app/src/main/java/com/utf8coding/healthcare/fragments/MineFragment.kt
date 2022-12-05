package com.utf8coding.healthcare.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.activities.ActivityCollector
import com.utf8coding.healthcare.activities.LoginActivity
import com.utf8coding.healthcare.activities.secondary.CollectionActivity
import com.utf8coding.healthcare.view_models.MineFragmentViewModel


class MineFragment : BaseFragment() {

    private val collectionCard: CardView?
        get(){
            return view?.findViewById(R.id.collectionCard)
        }
    private val collectionLayout: ConstraintLayout?
        get() {
            return view?.findViewById(R.id.collectionLayout)
        }
    private val logoutButton: CardView?
        get() {
            return view?.findViewById(R.id.logOutCard)
        }
    private val userNameText: TextView?
        get(){
            return view?.findViewById(R.id.userNameText)
        }
    private val userHeadImage: ImageView?
        get() {
            return view?.findViewById(R.id.userHeadImage)
        }

    private lateinit var viewModel: MineFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MineFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glideUrl = GlideUrl(
            viewModel.getUserHeadUri(),
            LazyHeaders.Builder().addHeader("Cookie", viewModel.getCookie()).build()
        )
        userHeadImage?.let { Glide.with(it).load(glideUrl).into(it) }
        userNameText?.text = viewModel.getUserName()
        collectionCard?.setOnClickListener {
            val intent = Intent(activity, CollectionActivity::class.java)
            activity?.startActivity(intent)
        }
        logoutButton?.setOnClickListener {
            activity?.getSharedPreferences("userData", Context.MODE_PRIVATE)?.let {
                it.edit()
                    .putString("userName", "")
                    .putString("passWord", "")
                    .putString("userId", "")
                    .apply()
            }
            ActivityCollector.finishAll()
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun refresh() {
    }

}