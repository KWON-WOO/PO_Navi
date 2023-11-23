package com.example.po_navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.service.autofill.UserData
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.skt.Tmap.TMapData
import org.json.JSONObject


//키보드 비활성화를 위한 함수
fun hideKeyboard(context:Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showList(data:JSONObject, activity: FragmentActivity){
    val tmapdata = TMapData()
    val recyclerView = activity.findViewById<RecyclerView>(R.id.path_recycler_view)

    val adapter = RecyclerViewAdapter(listInfo)
    val lFragment = BSDListFragment(adapter)
    lFragment.show(activity.supportFragmentManager, "Tag")

}

fun searchPOI(searchKeyword:String,appKey:String):JSONObject{
    val url = "https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback" +
            "=result&appKey=$appKey&searchKeyword=$searchKeyword"
    val data =  JSONObject(url).getJSONObject("pois")
    return data
}