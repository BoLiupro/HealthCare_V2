package com.utf8coding.healthcare.adapters.article_reading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.data.CommentData

class ArticleReadingCommentAdapter(private val commentList: ArrayList<CommentData>): RecyclerView.Adapter<ArticleReadingCommentAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_reading_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.view.findViewById<TextView>(R.id.userNameTextView).text =
        holder.view.findViewById<TextView>(R.id.commentTextView).text = "${commentList[position].userName}：${commentList[position].content}"
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}