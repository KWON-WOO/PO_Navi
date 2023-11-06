package com.example.po_navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//수정예정
@SuppressLint("ResourceType")
class BSDListFragment : BottomSheetDialogFragment(R.style.AppBottomSheetDialogTheme) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val listView = view. findViewById<ListView>(R.id.listView)

        val list = arguments?.getStringArrayList("list")

        val adapter = list?.let { ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it) }
        listView.adapter = adapter
    }
}