package com.utf8coding.healthcare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.utils.DensityUtils

class InfoRecyclerViewHeaderAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<InfoRecyclerViewHeaderAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
    }
    interface OnItemClickListener{
        fun onMedSearchButtonClick(bgView: View, frontText: View, buttonView: View)
        fun onArticleSearchButtonClick(bgView: View, frontText: View, buttonView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_header, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: InfoRecyclerViewHeaderAdapter.ViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.medSearchButton).setOnClickListener {
            listener.onMedSearchButtonClick(
                holder.view.findViewById<ImageView>(R.id.medSearchButtonBg),
                holder.view.findViewById<ImageView>(R.id.medSearchText),
                holder.view.findViewById(R.id.medSearchButton)
            )
        }
        holder.view.findViewById<ImageView>(R.id.articleSearchButton).setOnClickListener {
            listener.onArticleSearchButtonClick(
                holder.view.findViewById<ImageView>(R.id.articleSearchButtonBg),
                holder.view.findViewById<ImageView>(R.id.articleSearchText),
                holder.view.findViewById(R.id.articleSearchButton)
            )
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}