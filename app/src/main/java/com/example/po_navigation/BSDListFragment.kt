package com.example.po_navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//수정예정
@SuppressLint("ResourceType")
class BSDListFragment : BottomSheetDialogFragment(R.style.AppBottomSheetDialogTheme) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.path_recyclerview, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.path_recycler_view)

        val listInfo = intent.getParcelableArrayListExtra<Item>("listInfo")

        val adapter = RCAdapter(listInfo)

        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val listView = view. findViewById<ListView>(R.id.listView)

        val list = arguments?.getStringArrayList("list")

        val adapter = list?.let { ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it) }
        listView.adapter = adapter
    }
}

