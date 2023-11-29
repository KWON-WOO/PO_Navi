package com.example.po_navigation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.skt.Tmap.TMapData
import org.json.JSONObject
import com.google.gson.Gson
import java.net.URL


//키보드 비활성화를 위한 함수
fun hideKeyboard(context: Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

interface NetworkListener {
    // 네트워크 작업이 성공적으로 완료되었을 때 호출되는 메서드입니다.
    // 반환 타입을 MutableList<POI>로 지정합니다.
    fun onNetworkSuccess(data: String?): ArrayList<MapData>
}

class SearchPOI(AppKey:String, Activity:FragmentActivity) : OnNetworkListener {
    val activity = Activity
    val appKey = AppKey
    fun searchPOI(searchKeyword: String, appKey: String){
        val url = "https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback" +
                "=result&appKey=${appKey}&searchKeyword=${searchKeyword}"

        val networktask = NetworkTask(this)
        networktask.execute(url)
    }

    override fun onNetworkSuccess(url: String?) {
        val json = URL(url).readText(Charsets.UTF_8)
//        val jsonObject = JSONObject(json)
//        val poiList = ArrayList<MapData>()
//
//        val poiArray = jsonObject.getJSONObject("searchPoiInfo")
//            .getJSONObject("pois").getJSONArray("poi")
//        val gson = Gson()
//
//        for (i in 0 until poiArray.length()) {
//            val poiObject = poiArray.getJSONObject(i)
//            val tmpPoiObject = JSONObject()
//            tmpPoiObject.put("name", poiObject.getString("name"))
//            tmpPoiObject.put(
//                "fullAddressRoad", poiObject
//                    .getJSONArray("newAddressList")
//                    .getJSONObject(0).getString("fullAddressRoad")
//            )
//            tmpPoiObject.put("noorLat", poiObject.getString("name"))
//            tmpPoiObject.put("noorLon", poiObject.getString("name"))
//
//            val poiData = gson.fromJson(tmpPoiObject.toString(), MapData::class.java)
//            tmpPoiObject.put("name", poiObject.getString("name"))
//            poiList.add(poiData)
//            showList(poiList,appKey, activity)
//        }
    }
}

fun showList(poiList:ArrayList<MapData>, appKey: String, activity: FragmentActivity) {
    val tmapdata = TMapData()
    val recyclerView = activity.findViewById<RecyclerView>(R.id.path_recycler_view)

    val adapter = RecyclerViewAdapter(poiList)
    val lFragment = BSDListFragment(adapter)
    lFragment.show(activity.supportFragmentManager, "Tag")

}