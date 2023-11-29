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
class RecyclerViewAdapter(private val items: List<MapData>) :
RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
//    아이템 갯수를 가져옴
    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        //    클릭시 주소와 좌표 출력
        val listener = View.OnClickListener{ it ->
            Toast.makeText(it.context,"Clicked -> ID: ${item.name}, Address: ${item.fullAddressRoad}\t" +
                    "Location: (${item.noorLat},${item.noorLon}) ", Toast.LENGTH_SHORT).show()
        }
        holder.apply{
            bind(listener, item)
            itemView.tag = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.path_recyclerview,parent,false)
        return RecyclerViewAdapter.ViewHolder(view)
    }
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: MapData) {
            view.name_view.text = item.name
            view.address_view.text = item.fullAddressRoad
            view.setOnClickListener(listener)
        }

    }
}