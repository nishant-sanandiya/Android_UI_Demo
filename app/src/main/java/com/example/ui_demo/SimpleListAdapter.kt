package com.example.ui_demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SimpleListAdapter(context: Context, items: ArrayList<DummyData>) : BaseAdapter() {
    private val context: Context
    private val items: ArrayList<DummyData>

    override fun getCount(): Int {
        return items.size //returns total of items in the list
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
            View {
        var tempConvertView: View? = convertView
        if (tempConvertView == null) {
            tempConvertView = LayoutInflater.from(context)
                .inflate(R.layout.mylist, parent, false)
        }
        getItem(position) as DummyData
        val textViewItemName = tempConvertView
            ?.findViewById(R.id.myListText) as TextView
        textViewItemName.text = position.toString()
        return tempConvertView
    }

    init {
        this.context = context
        this.items = items
    }
}