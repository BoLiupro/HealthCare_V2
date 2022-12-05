package com.utf8coding.healthcare.activities.secondary

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.adapters.InfoRecyclerViewAdapter
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.utils.DensityUtils
import com.utf8coding.healthcare.view_models.ArticleSearchActivityViewModel
import kotlin.properties.Delegates

@Suppress("TypeParameterFindViewById")
class ArticleSearchActivity : AppCompatActivity() {
    lateinit var viewModel: ArticleSearchActivityViewModel
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
            return findViewById(R.id.articleSearchRecyclerView)
        }
    private val searchEditText: EditText
        get() {
            return findViewById(R.id.articleSearchEditText) as EditText
        }
    private val clearInputButton: ImageButton
        get() {
            return findViewById(R.id.clearInputButton) as ImageButton
        }
    private val articleListForAdapter: ArrayList<ArticleData> = ArrayList()
    private lateinit var articleAdapter: InfoRecyclerViewAdapter
    private var preY by Delegates.notNull<Float>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_search)
        viewModel = ViewModelProvider(this)[ArticleSearchActivityViewModel::class.java]
        window.statusBarColor = Color.TRANSPARENT

        searchButton.animate().alpha(1f)
            .setDuration(100).startDelay = 350

        articleAdapter = InfoRecyclerViewAdapter(
            articleListForAdapter,
            arrayListOf(
            DensityUtils.horizontalDp(this).toInt(), DensityUtils.verticalDp(this).toInt(),
            ),
            object: InfoRecyclerViewAdapter.OnItemClickListener{
                override fun onInfoClick(imageView: View, item: ArticleData) {
                    val imagePair = android.util.Pair(imageView,"transitionImgView")
                    val bundle =
                        ActivityOptions.makeSceneTransitionAnimation(
                            this@ArticleSearchActivity,
                            imagePair
                        )
                            .toBundle()
                    val intent = Intent(this@ArticleSearchActivity, ArticleReadingActivity::class.java)
                    intent.putExtra("articleData", item)
                    startActivity(intent, bundle)
                }
                override fun onInfoClick(item: ArticleData) {
                    val intent = Intent(this@ArticleSearchActivity, ArticleReadingActivity::class.java)
                    intent.putExtra("articleData", item)
                    intent.putExtra("articleData", item)
                    startActivity(intent)
                }
            }
        )
        initRecyclerView()
        searchButton.setOnClickListener {
            onSearch()
            val manager =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
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
        recyclerView.adapter = articleAdapter
        preY = recyclerView.y
    }

    private fun onSearch(){
        makeILog("onSearch")
        viewModel.getResultMedList(searchEditText.text.toString())
        viewModel.articleList.observe(this) { articleList ->
            if(articleList.size != 0){
                recyclerView.alpha = 0f
                articleListForAdapter.clear()
                articleListForAdapter.addAll(articleList)
                articleAdapter.notifyDataSetChanged()
                recyclerView.y = preY + 150f
                recyclerView.animate().alpha(1f).yBy(20f).interpolator = OvershootInterpolator(4f)
            }
        }
    }

    //tools
    private fun makeToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun makeILog(msg: String){
        Log.i("ArticleSearchActivity:", msg)
    }

}