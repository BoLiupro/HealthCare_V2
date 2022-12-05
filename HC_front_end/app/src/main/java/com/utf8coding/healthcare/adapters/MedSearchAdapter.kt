package com.utf8coding.healthcare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.data.MedData

class MedSearchAdapter(private val medList: ArrayList<MedData>): RecyclerView.Adapter<MedSearchAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.medName)
        val type: TextView = view.findViewById(R.id.medType)
        val quantity: TextView = view.findViewById(R.id.medQuantity)
        val manufacturer: TextView = view.findViewById(R.id.medProducer)
        val price: TextView = view.findViewById(R.id.medPrice)
        val medSearchListElement = view.findViewById<View>(R.id.medSearchListElement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_med_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //多语言化就算了www
        holder.name.text = "药品：" + medList[position].name
        holder.type.text = "类型：" + medList[position].type
        holder.quantity.text = "数量：" + medList[position].quantity
        holder.manufacturer.text = "制造商" + medList[position].manufacturer
        holder.price.text = medList[position].price.toString() + "元"
        holder.name.text = medList[position].name
        holder.medSearchListElement.setOnClickListener {
            //contd. in future
        }
    }

    override fun getItemCount(): Int {
        return medList.size
    }
}