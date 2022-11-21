package com.example.sql_onactivitys.data

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.sql_onactivitys.R

class MyAdapter(listMain: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listArray = listMain

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tView_name_card)
        val tvPhone = itemView.findViewById<TextView>(R.id.tView_phone_card)
        val ivImage = itemView.findViewById<ImageView>(R.id.imView_card)
        fun setData(name: String, phone: String, uri: String) {
            tvName.text = name
            tvPhone.text = phone
//            ivImage.setImageURI(uri.toUri())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.rc_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(listArray.get(position), listArray.get(position), listArray.get(position))

    }

    override fun getItemCount() = listArray.size

    fun updateAdapter(listItems: List<String>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }


}