package com.utf8coding.healthcare.adapters.article_reading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.R

class ArticleReadingLeaveCommentAdapter(private val listener: ArticleLeaveCommentListener): RecyclerView.Adapter<ArticleReadingLeaveCommentAdapter.ViewHolder>() {
    interface ArticleLeaveCommentListener {
        fun onSendCommentAndUnfocus(content: String)
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val cardView: CardView = view.findViewById(R.id.leaveCommentCardView)
        val editText: EditText = view.findViewById(R.id.leaveCommentEdittext)
        val commentButton: Button = view.findViewById(R.id.sendCommentButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_reading_leave_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentButton.setOnClickListener {
            listener.onSendCommentAndUnfocus(holder.editText.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}