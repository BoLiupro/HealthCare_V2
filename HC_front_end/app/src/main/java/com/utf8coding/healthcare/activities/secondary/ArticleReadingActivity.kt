package com.utf8coding.healthcare.activities.secondary

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.slider.Slider
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.adapters.article_reading.ArticleReadingCommentAdapter
import com.utf8coding.healthcare.adapters.article_reading.ArticleReadingLeaveCommentAdapter
import com.utf8coding.healthcare.adapters.article_reading.ArticleReadingTextAdapter
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.data.CommentData
import com.utf8coding.healthcare.utils.DensityUtils
import com.utf8coding.healthcare.view_models.ArticleReadingActivityViewModel

@Suppress("TypeParameterFindViewById")
class ArticleReadingActivity : AppCompatActivity() {
    lateinit var viewModel: ArticleReadingActivityViewModel
    private val headerImg: ImageView
        get() {
            return findViewById(R.id.articleReadingHeaderImg) as ImageView
        }
    private val recyclerView: RecyclerView
        get() {
            return findViewById(R.id.readingRecyclerView)
        }
    private val collapsingToolbarLayout: CollapsingToolbarLayout
        get() {
            return findViewById(R.id.collapsingToolbar)
        }
    private val appBarLayout: AppBarLayout
        get() {
            return findViewById(R.id.appBarLayout)
        }
    private val toolBar: androidx.appcompat.widget.Toolbar
        get() {
            return findViewById(R.id.toolbar)
        }
    private val textSizeSlider: Slider
        get() {
            return findViewById(R.id.textSizeSlider)
        }
    private val closeTextSizeButton: ImageButton
        get() {
            return findViewById(R.id.closeTextSizeButton)
        }
    private val textSizeCard: CardView
        get() {
            return findViewById(R.id.textSizeCard)
        }
    private var articleData: ArticleData = ArticleData(0, "出错了", "出错了", null)
    private val commentListForRecyclerView = arrayListOf<CommentData>()
    private var readingTextAdapter = ArticleReadingTextAdapter(articleData)
    private val readingCommentAdapter = ArticleReadingCommentAdapter(commentListForRecyclerView)
    private var isBackAnimate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_reading)
        viewModel = ViewModelProvider(this)[ArticleReadingActivityViewModel::class.java]
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        articleData = intent.getSerializableExtra("articleData") as ArticleData

        closeTextSizeButton.setOnClickListener {
            textSizeCard.animate().alpha(0f).setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    textSizeCard.visibility = View.INVISIBLE
                }
                override fun onAnimationCancel(animation: Animator?) {
                }
                override fun onAnimationRepeat(animation: Animator?) {
                }
            })
        }

        initCollapsingBarLayout()

        initCollectButton()

        intiCollectButtonLogic()

        initRecyclerView()

        initTextSizeSlider()
    }


    override fun onDestroy() {
        if (viewModel.isCollected.value!!){
            viewModel.collectArticle(articleData)
        } else {
            viewModel.unCollectArticle(articleData)
        }
        super.onDestroy()
    }

    override fun onBackPressed() {

        if (!isBackAnimate) {
            headerImg.transitionName = ""
        }
        super.onBackPressed()
    }

    private fun initCollapsingBarLayout(){
        collapsingToolbarLayout.title = articleData.title
        if (articleData.headPicUrl != null) {
            Glide.with(this).load(articleData.headPicUrl).into(headerImg)
        } else {
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandingCollapsingViewTitleTextBlack)
            collapsingToolbarLayout.expandedTitleMarginStart -= DensityUtils.dp2px(this, 20f).toInt()
            appBarLayout.layoutParams.height = DensityUtils.dp2px(this, 120f).toInt()
        }

        //单独拎出来防止一个 Overload resolution ambiguity 的问题，这里是根据是页面否在顶部判断按返回上一个 activity 时是否有图片飞回的动画
        val listener =  AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            isBackAnimate = verticalOffset == 0
        }
        appBarLayout.addOnOffsetChangedListener(listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        readingTextAdapter = ArticleReadingTextAdapter(articleData)
        viewModel.getCommentList(articleData).observe(this
        ) { newCommentList ->
            newCommentList?.let {
                commentListForRecyclerView.clear()
                commentListForRecyclerView.addAll(it)
                readingCommentAdapter.notifyDataSetChanged()
                val leaveCommentAdapter = ArticleReadingLeaveCommentAdapter(
                    object : ArticleReadingLeaveCommentAdapter.ArticleLeaveCommentListener {
                        override fun onSendCommentAndUnfocus(content: String) {
                            viewModel.sendComment(content,  articleData.id)
                            Toast.makeText(this@ArticleReadingActivity,
                                "已发送，请稍等审核！", Toast.LENGTH_SHORT).show()
                            val manager: InputMethodManager =
                                applicationContext
                                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            manager.hideSoftInputFromWindow(
                                currentFocus!!.windowToken,
                                InputMethodManager.HIDE_NOT_ALWAYS
                            )
                        }
                    }
                )
                val finalAdapter =
                    ConcatAdapter(readingTextAdapter, leaveCommentAdapter, readingCommentAdapter)
                recyclerView.adapter = finalAdapter
                recyclerView.layoutManager = LinearLayoutManager(
                    this@ArticleReadingActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
        recyclerView.outlineSpotShadowColor = resources.getColor(R.color.active_green_light)
    }

    private fun initTextSizeSlider(){
        textSizeSlider.value = 20f
        textSizeSlider.addOnChangeListener{ _, value, _ ->
            readingTextAdapter.setTextSize(value)
        }
    }

    private fun intiCollectButtonLogic(){
        toolBar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.collect -> {
                    viewModel.isCollected.value = !viewModel.isCollected.value!!
                    viewModel.isCollected.value?.let { isCollected ->
                        if (isCollected) {
                            toolBar.menu.getItem(1).icon =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.ic_round_favorite_24, null
                                )
                        } else {
                            toolBar.menu.getItem(1).icon =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.ic_baseline_favorite_border_24, null
                                )
                        }
                    }

                    true
                }
                R.id.textSize -> {
                    textSizeCard.alpha = 0f
                    textSizeCard.visibility = View.VISIBLE
                    textSizeCard.animate()
                        .setListener(EmptyAnimationListener())
                        .alpha(1f)
                    true
                }
                else -> false
            }
        }
    }

    private fun initCollectButton(){
        viewModel.getIsCollected(articleData).observe(this) { isCollected ->
            if (isCollected) {
                toolBar.menu.getItem(1).icon =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_round_favorite_24, null
                    )
            } else {
                toolBar.menu.getItem(1).icon =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_favorite_border_24, null
                    )
            }
        }
    }

    inner class EmptyAnimationListener: Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }
}