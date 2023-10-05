package com.example.po_navigation

import android.content.Context
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapView
import com.skt.Tmap.poi_item.TMapPOIItem


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Tmapapi 변수 지정
        val tmaptapi: TMapTapi = TMapTapi(this)
        // Tmap 설치여부 확인
        val isTmapApp: Boolean = tmaptapi.isTmapApplicationInstalled()

//티맵이 설치된 상태라면 정상작동. 아니라면 별도의 레이아웃 호출(현재는 테스트 중이라 반대로 해둠)
        if (!isTmapApp) {
            setContentView(R.layout.activity_main)
            val searchBar = findViewById<AutoCompleteTextView>(R.id.search_bar)

//            추천어 저장해주는 StringArray
            val countires = resources.getStringArray(R.array.recommended_search_terms)
            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, countires)
            val searchButton = findViewById<Button>(R.id.search_button)

            val linearLayoutTmap = findViewById<View>(R.id.linearLayoutTmap) as LinearLayout
            val tMapView = TMapView(this)
            searchBar.setAdapter(adapter)

            tMapView.setSKTMapApiKey(getString(R.string.app_key1))
            linearLayoutTmap.addView(tMapView)

//            검색버튼 이벤트. 테스트 중이라 네이버로 넘어가게 설정. 이후에 T맵 연동되도록 수정 예정
            searchButton.setOnClickListener {
                val webView = WebView(this)
                webView.webViewClient = WebViewClient()

                val webSettings = webView.settings
                webSettings.javaScriptEnabled = true

                val query = searchBar.text.toString()

                webView.loadUrl("https://www.naver.com/searh?q=$query")
                setContentView(webView)

            }

            tMapView.setOnClickListenerCallBack(object : TMapView.OnClickListenerCallback {
//                맵을 눌렀을 때 키보드 비활성화되도록 맵에 클릭이벤트 적용
                override fun onPressEvent(
                    arrayList1: ArrayList<TMapMarkerItem?>?,
                    arrayList2: ArrayList<TMapPOIItem?>?,
                    tMapPoint: TMapPoint?,
                    pointF: PointF?
                ): Boolean {
                    Toast.makeText(this@MainActivity, "키보드 숨김", Toast.LENGTH_SHORT).show()
                    hideKeyboard(tMapView)
                    return false
                }
//                위의 이벤트 작동여부 확인을 위한 함수. 삭제 예정
                override fun onPressUpEvent(
                    arrayList1: ArrayList<TMapMarkerItem?>?,
                    arrayList2: ArrayList<TMapPOIItem?>?,
                    tMapPoint: TMapPoint?,
                    pointF: PointF?
                ): Boolean {
                    Toast.makeText(this@MainActivity, "손 뗌", Toast.LENGTH_SHORT).show()
                    return false
                }
            })
        }else{
//            티맵 없는 환경에서 띄워줄 레이아웃. 차후 수정 예정
            setContentView(R.layout.tmap_not_installed)
            Toast.makeText(this@MainActivity, "티맵 설치 안됨", Toast.LENGTH_SHORT)
//            티맵 없을 시 종료버튼과 설치페이지로 넘어가는 버튼 생성 현재 종료버튼은 미구현
            val tmapInstallButton = findViewById<Button>(R.id.tmap_install_rink_button)
            val exitButton = findViewById<Button>(R.id.exit_button)

            val mapDownloadURL: ArrayList<String>? = tmaptapi.tMapDownUrl
//            Tmap 설치 버튼 클릭 이벤트
            tmapInstallButton.setOnClickListener{
                val webView = WebView(this)
                webView.webViewClient = WebViewClient()

                val webSettings = webView.settings
                webSettings.javaScriptEnabled = true
//개통 여부 확인 후 통신사에 맞는 URL 제공. 미개통 기종은 플레이스토어로 넘어가도록 하려고 했으나 현재 버그 발생.
                val query = when {
                    mapDownloadURL.toString() == null -> tmaptapi.getTMapDownUrl();
                    else -> "https://play.google.com/store/apps/details?id=com.skt.tmap.ku"
                }
                webView.loadUrl("$query")
                setContentView(webView)
                }
            }
        }
//키보드 비활성화를 위한 함수
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}

