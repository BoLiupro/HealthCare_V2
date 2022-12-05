package com.utf8coding.healthcare.fragments

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.activities.secondary.ArticleReadingActivity
import com.utf8coding.healthcare.activities.secondary.ArticleSearchActivity
import com.utf8coding.healthcare.activities.secondary.MedSearchActivity
import com.utf8coding.healthcare.adapters.InfoRecyclerViewAdapter
import com.utf8coding.healthcare.adapters.InfoRecyclerViewHeaderAdapter
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.utils.DensityUtils
import com.utf8coding.healthcare.view_models.InfoFragmentViewModel

class InfoFragment : BaseFragment() {

    private lateinit var viewModel: InfoFragmentViewModel
    private val recyclerView: RecyclerView?
        get() {
            return view?.findViewById(R.id.recyclerView)
        }
    private val refreshLayout: RefreshLayout?
        get() {
            return view?.findViewById(R.id.refreshLayout)
        }
    private val loadingHeader: MaterialHeader?
        get() {
            return view?.findViewById(R.id.loadingHeader)
        }

    private var medSearchButton: ImageView? = null
    private var articleSearchButton: ImageView? = null
    private var recyclerViewAdapter = InfoRecyclerViewAdapter(ArrayList(), arrayListOf(0, 0))
    private var recyclerViewHeaderAdapter = InfoRecyclerViewHeaderAdapter(object: InfoRecyclerViewHeaderAdapter.OnItemClickListener{
        override fun onMedSearchButtonClick(bgView: View, frontText: View, buttonView: View) {}
        override fun onArticleSearchButtonClick(bgView: View, frontText: View, buttonView: View) {}
    })
    private val articleDataListForRecyclerView = ArrayList<ArticleData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[InfoFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        refreshLayout?.setOnRefreshListener {
            refresh()
        }
        loadingHeader?.setOnHoverListener { _, _ ->
            refresh()
            false
        }
    }

    override fun onResume() {
        super.onResume()
        medSearchButton?.visibility = VISIBLE
        articleSearchButton?.visibility = VISIBLE
        medSearchButton?.animate()?.y(10f)?.alpha(1f)?.interpolator = DecelerateInterpolator()
        articleSearchButton?.animate()?.y(10f)?.alpha(1f)?.interpolator = DecelerateInterpolator()
     }

    @SuppressLint("NotifyDataSetChanged")
    override fun refresh(){
        viewModel.getArticleList().observe(viewLifecycleOwner) { newArticleDataList ->
            articleDataListForRecyclerView.clear()
            articleDataListForRecyclerView.addAll(newArticleDataList)
            recyclerViewAdapter.notifyDataSetChanged()
            refreshLayout?.finishRefresh()
        }
    }

    private fun initRecyclerView(){
        //拿List：
        viewModel.getArticleList().observe(viewLifecycleOwner) { newArticleDataList ->
            articleDataListForRecyclerView.clear()
            articleDataListForRecyclerView.addAll(newArticleDataList)
            val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerView?.layoutManager = layoutManager

            //header adapter
            recyclerViewHeaderAdapter = InfoRecyclerViewHeaderAdapter(object: InfoRecyclerViewHeaderAdapter.OnItemClickListener{
                override fun onMedSearchButtonClick(bgView: View, frontText: View, buttonView: View) {
                    //转存，供从搜索activity返回时调用
                    medSearchButton = buttonView as ImageView

                    //英语字母退出：
                    medSearchButton?.animate()?.y(80f)?.alpha(0f)?.setInterpolator(AccelerateInterpolator())
                        ?.setListener(object: Animator.AnimatorListener{
                            override fun onAnimationStart(animation: Animator?) {}
                            override fun onAnimationEnd(animation: Animator?) {
                                //字母退出后，启动搜索activity，包含共享元素动画
                                val imagePair = android.util.Pair(bgView,"transitionBgImgView")
                                val textPair = android.util.Pair(frontText,"transitionFrontImgView")
                                val bundle =
                                    ActivityOptions.makeSceneTransitionAnimation(activity,
                                        imagePair, textPair)
                                        .toBundle()
                                startActivity(Intent(activity, MedSearchActivity::class.java), bundle)

                                //重置监听器防BUG
                                medSearchButton!!.animate().setListener(EmptyAnimationListener())
                            }
                            override fun onAnimationCancel(animation: Animator?) {}
                            override fun onAnimationRepeat(animation: Animator?) {}
                        })
                        ?.duration = 100
                }

                override fun onArticleSearchButtonClick(bgView: View, frontText: View, buttonView: View) {
                    //转存，供从搜索activity返回时调用
                    articleSearchButton = buttonView as ImageView

                    //英语字母退出：
                    articleSearchButton?.animate()?.y(80f)?.alpha(0f)?.setInterpolator(AccelerateInterpolator())
                        ?.setListener(object: Animator.AnimatorListener{
                        override fun onAnimationStart(animation: Animator?) {}
                        override fun onAnimationEnd(animation: Animator?) {
                            //字母退出后，启动搜索activity，包含共享元素动画
                            val imagePair = android.util.Pair(bgView,"transitionBgImgView")
                            val textPair = android.util.Pair(frontText,"transitionFrontImgView")
                            val bundle =
                                ActivityOptions.makeSceneTransitionAnimation(activity,
                                    imagePair, textPair)
                                    .toBundle()
                            startActivity(Intent(activity, ArticleSearchActivity::class.java), bundle)

                            //重置监听器防BUG
                            articleSearchButton!!.animate().setListener(EmptyAnimationListener())
                        }
                        override fun onAnimationCancel(animation: Animator?) {}
                        override fun onAnimationRepeat(animation: Animator?) {}
                    })
                        ?.duration = 100
                }
            })

            //初始化adapter
            recyclerViewAdapter = InfoRecyclerViewAdapter(
                articleDataListForRecyclerView,
                arrayListOf(DensityUtils.horizontalDp(activity as Context).toInt(),
                DensityUtils.verticalDp(activity as Context).toInt()),
                object: InfoRecyclerViewAdapter.OnItemClickListener{

                    override fun onInfoClick(imageView: View, item: ArticleData) {
                        val imagePair = android.util.Pair(imageView,"transitionImgView")
                        val bundle =
                            ActivityOptions.makeSceneTransitionAnimation(
                                activity,
                                imagePair
                            )
                                .toBundle()
                        val intent = Intent(activity, ArticleReadingActivity::class.java)
                        intent.putExtra("articleData", item)
                        startActivity(intent, bundle)
                    }

                    override fun onInfoClick(item: ArticleData) {
                        val intent = Intent(activity, ArticleReadingActivity::class.java)
                        intent.putExtra("articleData", item)
                        startActivity(intent)
                    }
                }
            )
            //用于 header：
            val concatAdapter = ConcatAdapter(recyclerViewHeaderAdapter, recyclerViewAdapter)
            recyclerView?.adapter = concatAdapter
        }
    }
    //tools:
    private fun makeToast(msg: String){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
    private fun makeILog(msg: String) {
        Log.i("InfoFragment:", msg)
    }

    inner class EmptyAnimationListener: Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }
}