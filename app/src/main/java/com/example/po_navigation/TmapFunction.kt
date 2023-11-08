package com.example.po_navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import com.skt.Tmap.TMapData
import com.skt.Tmap.poi_item.TMapPOIItem

//키보드 비활성화를 위한 함수
fun hideKeyboard(context:Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showList(list:ArrayList<String>, activity: FragmentActivity){
    val lFragment = BSDListFragment()
    val bundle = Bundle()
    list?.let {bundle.putStringArrayList("list", it)}
    lFragment.arguments = bundle
    for (i in 0 until list.size) {
        Log.d(
            "POI Name: ", list[i] + "++++++++++++"
        )
    }
    lFragment.show(activity.supportFragmentManager, lFragment.tag)
}

fun POISearch(query: String){
    val itemName = ArrayList<String>()
    val itemAddress = ArrayList<String>()
    val itemLat = ArrayList<Double>()
    val itemLon = ArrayList<Double>()
    val tmapdata = TMapData()
    tmapdata.findAllPOI(query,
        TMapData.FindAllPOIListenerCallback { poiItem ->
            for (i in 0 until poiItem.size) {
                val item = poiItem[i] as TMapPOIItem
                val Address = tmapdata.convertGpsToAddress(
                    item.noorLat.toDouble(),
                    item.noorLon.toDouble()
                )
                itemName.add(item.poiName.toString())
                itemAddress.add(Address.toString())
                itemLat.add(item.noorLat.toDouble())
                itemLon.add(item.noorLon.toDouble())
                Log.d("POI Name: ", itemName.size.toString())
            }
//             여기서 데이터 사용
            Log.d("POI Size: ", itemLat.size.toString())
        })
}

fun showBSDList(query: String, list:ArrayList<String>, activity: FragmentActivity){
    val itemName = ArrayList<String>()
    val itemAddress = ArrayList<String>()
    val itemLat = ArrayList<Double>()
    val itemLon = ArrayList<Double>()
    val tmapdata = TMapData()
    tmapdata.findAllPOI(query,
        TMapData.FindAllPOIListenerCallback { poiItem ->
            for (i in 0 until poiItem.size) {
                val item = poiItem[i] as TMapPOIItem
                val Address = tmapdata.convertGpsToAddress(
                    item.noorLat.toDouble(),
                    item.noorLon.toDouble()
                )
                itemName.add(item.poiName.toString())
                itemAddress.add(Address.toString())
                itemLat.add(item.noorLat.toDouble())
                itemLon.add(item.noorLon.toDouble())
                Log.d("POI Name: ", itemName.size.toString())
            }
//             여기서 데이터 사용
            showList(list,activity)
            Log.d("POI Size: ", itemLat.size.toString())
        })
}