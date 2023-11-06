package com.example.po_navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment

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
    lFragment.show(activity.supportFragmentManager, lFragment.tag)
}