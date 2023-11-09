package com.example.po_navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Item(
    val name: String,
    val address: String,
    val Latitiude: Double,
    val Longitude: Double,
    val icon: Int
)
class RCAdapter(private val items: List<Item>) :
RecyclerView.Adapter<RCAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView : TextView = itemView.findViewById(R.id.name_view)
        val addressView: TextView = itemView.findViewById(R.id.address_view)
        val iconView: ImageView = itemView.findViewById(R.id.icon_view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.path_recyclerview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.iconView.setImageResource(item.icon)
        holder.nameView.text = item.name
        holder.addressView.text = item.address
    }

    override fun getItemCount(): Int {
        return items.size
    }
}