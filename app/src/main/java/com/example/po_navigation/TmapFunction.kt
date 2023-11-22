package com.example.po_navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.skt.Tmap.TMapData


//키보드 비활성화를 위한 함수
fun hideKeyboard(context:Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showList(query:String, activity: FragmentActivity){
    val tmapdata = TMapData()
    val lFragment = BSDListFragment()
    val recyclerView = activity.findViewById<RecyclerView>(R.id.path_recycler_view)
    val bundle = Bundle()
    val listInfo = ArrayList<MapData>()
    val Address = ArrayList<String>()

    tmapdata.findAllPOI(query) { poiItem ->
        for (i in 0 until poiItem.size) {
            val item = poiItem[i]
            Address.add(
                tmapdata.convertGpsToAddress(
                    poiItem[i].noorLat.toDouble(),
                    poiItem[i].noorLon.toDouble()
                ).toString())
                        listInfo.add(MapData(poiItem[i].poiName,
                            Address[i],
                            poiItem[i].noorLat.toDouble(),
                            poiItem[i].noorLon.toDouble(), 1)
                        )
        }
        val intent = Intent(activity, BSDListFragment::class.java)
        intent.putExtra("listInfo", listInfo)

        lFragment.show(activity.supportFragmentManager, "list")
    }
//    POIItem?.let {
//        for (i in 0 until it.size) {
//            Address.add(
//                tmapdata.convertGpsToAddress(
//                    it[i].noorLat.toDouble(),
//                    it[i].noorLon.toDouble()
//                ).toString()
//            )
//
//            listInfo.add(
//                "Name : " + it[i].poiName
////                        "\n Address : " + Address +
////                        "\n Lat : " + it[i].noorLat.toString() +
////                        "\n Lon : " + it[i].noorLon.toString() + "\n"
//            )
//        }
//    }
//
//    listInfo.add("널인건가")
//    bundle.putStringArrayList("list", listInfo)
//    lFragment.arguments = bundle
//
//    lFragment.show(activity.supportFragmentManager, lFragment.tag)
}