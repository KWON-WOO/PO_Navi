package com.example.po_navigation

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import com.skt.Tmap.poi_item.TMapPOIItem


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        val linearLayoutTmap = findViewById<View>(R.id.linearLayoutTmap) as LinearLayout
        val tMapView = TMapView(this)

        tMapView.setSKTMapApiKey(getString(R.string.app_key1))
        linearLayoutTmap.addView(tMapView)

        tMapView.setOnClickListenerCallBack(object : TMapView.OnClickListenerCallback {
            override fun onPressEvent(
                arrayList1: ArrayList<TMapMarkerItem?>?,
                arrayList2: ArrayList<TMapPOIItem?>?,
                tMapPoint: TMapPoint?,
                pointF: PointF?
            ): Boolean {
                Toast.makeText(this@MainActivity, "클릭", Toast.LENGTH_SHORT).show()

                return false
            }

            override fun onPressUpEvent(
                arrayList1: ArrayList<TMapMarkerItem?>?,
                arrayList2: ArrayList<TMapPOIItem?>?,
                tMapPoint: TMapPoint?,
                pointF: PointF?
            ): Boolean {
                //Toast.makeText(this@MapEvent, "onPressUp~!", Toast.LENGTH_SHORT).show()
                return false
            }
        })
    }
//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//            menuInflater.inflate(R.menu.location_search, menu) // 메뉴 리소스 파일명 변경
//
//            val searchItem = menu.findItem(R.id.action_search)
//            val searchView = searchItem.actionView as SearchView
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String): Boolean {
//                    // 검색 버튼이 눌렸을 때의 동작을 여기에 추가합니다.
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String): Boolean {
//                    // 검색어가 변경될 때의 동작을 여기에 추가합니다.
//                    return true
//                }
//            })
//
//            return true
//        }









}

