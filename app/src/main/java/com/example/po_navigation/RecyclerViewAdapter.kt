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
class RecyclerViewAdapter() :
RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private var items : ArrayList<MapData> = ArrayList()
//    아이템 갯수를 가져옴
    override fun getItemCount(): Int = items.size

    fun setItem(POIList : ArrayList<MapData>){
        if (!items.isNullOrEmpty()) {
            items = POIList
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

//    viewHolder 생성하는 함수, 최소 생성 횟수만큼만 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.path_recyclerview,parent,false)
        return RecyclerViewAdapter.ViewHolder(view)
    }
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: MapData) {
            view.findViewById<TextView>(R.id.name_view).text = item.name
            view.findViewById<TextView>(R.id.address_view).text = item.fullAddressRoad
        }

    }

}