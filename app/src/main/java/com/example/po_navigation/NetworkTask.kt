package com.example.po_navigation

import android.os.AsyncTask
import java.net.URL

class NetworkTask(val listener: OnNetworkListener) : AsyncTask<String, Void, String>() {

//    네트워크 작업을 백그라운드 스레드에서 수행하는 메서드
    override fun doInBackground(vararg params: String?): String {
//        URL을 문자열로 저장
        val url = params[0]

        // URL에서 텍스트 데이터를 읽어옵니다.
        val data = URL(url).readText()

        // 텍스트 데이터를 반환합니다.
        return data
    }

//     네트워크 작업이 완료되면 UI 스레드에서 결과를 처리하는 메서드
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
//         리스너의 onNetworkSuccess 메서드를 호출하여 결과를 전달
        listener.onNetworkSuccess(result)
    }
}

// UI 업데이트를 위한 인터페이스를 정의
interface OnNetworkListener {
//     네트워크 작업이 성공적으로 완료되었을 때 호출되는 메서드
    fun onNetworkSuccess(data: String?)
}