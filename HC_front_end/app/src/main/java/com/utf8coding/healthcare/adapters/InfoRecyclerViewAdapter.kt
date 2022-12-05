package com.utf8coding.healthcare.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.utils.DensityUtils


class InfoRecyclerViewAdapter(private val dataList: ArrayList<ArticleData>, private val windowParams: ArrayList<Int>, private var listener: OnItemClickListener?): RecyclerView.Adapter<InfoRecyclerViewAdapter.ViewHolder>() {

    val cookie = MyApplication.context.getSharedPreferences("cookie", Context.MODE_PRIVATE).getString("cookie", "")

    constructor(dataList: ArrayList<ArticleData>, windowParams: ArrayList<Int>) : this(dataList, windowParams, null)

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
    interface OnItemClickListener{
        fun onInfoClick(imageView: View, item: ArticleData)
        fun onInfoClick(item: ArticleData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articleData = dataList[position]
        val title = holder.view.findViewById<TextView>(R.id.titleTextView)
        val content = holder.view.findViewById<TextView>(R.id.contentIntroTextView)
        val headPic = holder.view.findViewById<ImageView>(R.id.articleHeadPic)

        title.text = articleData.title
        holder.view.findViewById<CardView>(R.id.cardView).setOnClickListener {
            //方便做图片共享元素：
            if (articleData.headPicUrl != null)
                listener?.onInfoClick(holder.view.findViewById(R.id.articleHeadPic), articleData)
            else
                listener?.onInfoClick(articleData)

        }

        if (articleData.headPicUrl == null){
            //没有头图，宽度设置为零，拓宽TextView，去掉圆角，内容预览不超过60字：
            content.text = if (articleData.content.length >= 60){
                "${articleData.content.subSequence(0, 58)}……"
            } else {
                articleData.content
            }
            holder.view.findViewById<ImageView>(R.id.roundedCorner).visibility = GONE
            headPic.layoutParams.width = 0
            title.layoutParams.width = DensityUtils.dp2px(MyApplication.context, (windowParams[0] - 55).toFloat()).toInt()
            content.layoutParams.width = DensityUtils.dp2px(MyApplication.context, (windowParams[0] - 55).toFloat()).toInt()
        } else {
            //有头图，直接直接进行一个加的载，内容预览不超过46字：
            content.text = if (articleData.content.length >= 46){
                "${articleData.content.subSequence(0, 43)}……"
            } else {
                articleData.content
            }
            Glide.with(MyApplication.context)
                .load(articleData.headPicUrl)
                .into(holder.view.findViewById(R.id.articleHeadPic))
        }

    }

    fun setOnItemClickListener(mListener: OnItemClickListener){
        listener = mListener
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    //tools:
    private fun makeILog(msg: String) {
        Log.i("InfoRecyclerViewAdapter:", msg)
    }
}