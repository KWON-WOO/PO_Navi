package com.example.po_navigation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.path_recyclerview.view.*
class RecyclerViewAdapter(private var items : ArrayList<MapData>) :
RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
//    아이템 갯수를 가져옴
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name_view.text = item.name
        holder.address_view.text = item.fullAddressRoad
    }

//    viewHolder 생성하는 함수, 최소 생성 횟수만큼만 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.path_recyclerview,parent,false)
        return ViewHolder(view)
    }
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        val name_view: TextView = view.findViewById(R.id.name_view)
        val address_view: TextView = view.findViewById(R.id.address_view)


    }

}