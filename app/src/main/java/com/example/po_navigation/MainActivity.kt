package com.example.po_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.skt.Tmap.TMapView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutTmap = findViewById<View>(R.id.linearLayoutTmap) as LinearLayout
        val tMapView = TMapView(this)

        tMapView.setSKTMapApiKey(getString(R.string.app_key1))
        linearLayoutTmap.addView(tMapView)
    }
}
