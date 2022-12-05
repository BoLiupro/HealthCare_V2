package com.utf8coding.healthcare.activities.secondary

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.adapters.InfoRecyclerViewAdapter
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.utils.DensityUtils
import com.utf8coding.healthcare.utils.GenerateTestContentUtils
import com.utf8coding.healthcare.view_models.CollectionActivityViewModel
import com.utf8coding.healthcare.view_models.MainActivityViewModel

class CollectionActivity : AppCompatActivity() {
    private lateinit var viewModel: CollectionActivityViewModel
    private val collectionRecyclerView: RecyclerView
        get() {
            return findViewById(R.id.collectionRecyclerView)
        }
    private val articleList: ArrayList<ArticleData> = ArrayList()
    private lateinit var recyclerViewAdapter: InfoRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        window.statusBarColor = Color.TRANSPARENT

        viewModel = ViewModelProvider(this)[CollectionActivityViewModel::class.java]

        recyclerViewAdapter = InfoRecyclerViewAdapter(articleList,
            arrayListOf(
                DensityUtils.horizontalDp(this).toInt(),
                DensityUtils.verticalDp(this).toInt()),
            object: InfoRecyclerViewAdapter.OnItemClickListener{
                override fun onInfoClick(imageView: View, item: ArticleData) {
                    val imagePair = android.util.Pair(imageView,"transitionImgView")
                    val bundle =
                        ActivityOptions.makeSceneTransitionAnimation(
                            this@CollectionActivity,
                            imagePair
                        )
                            .toBundle()
                    val intent = Intent(this@CollectionActivity, ArticleReadingActivity::class.java)
                    intent.putExtra("articleData", item)
                    startActivity(intent, bundle)
                }

                override fun onInfoClick(item: ArticleData) {
                    val intent = Intent(this@CollectionActivity, ArticleReadingActivity::class.java)
                    intent.putExtra("articleData", item)
                    startActivity(intent)
                }
            }
        )

        collectionRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        collectionRecyclerView.adapter = recyclerViewAdapter
        getCollection()
    }

    override fun onResume() {
        super.onResume()
        getCollection()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCollection(){
        val userId = MyApplication.context.getSharedPreferences("userData", Context.MODE_PRIVATE).getInt("userId", -1)
        viewModel.getCollection(userId).observe(this, Observer { collectionList ->
            articleList.clear()
            articleList.addAll(collectionList)
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }
}