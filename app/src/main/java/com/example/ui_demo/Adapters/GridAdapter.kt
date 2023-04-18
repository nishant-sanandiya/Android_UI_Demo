package com.example.ui_demo.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ui_demo.Models.GridDataModel
import com.example.ui_demo.R

class GridAdapter(context: Context, list: ArrayList<GridDataModel>) :
    ArrayAdapter<GridDataModel?>(context, 0, list as List<GridDataModel?>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView: View? = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.grid_card, parent, false)
        }

        val courseModel: GridDataModel? = getItem(position)
        val gridTextView = listItemView?.findViewById<TextView>(R.id.gridText)

        gridTextView?.text = courseModel?.getName()
        if( courseModel?.getName()?.length!! >  3){
            gridTextView?.setTextColor(R.color.Turquoise.toInt())
        }
        return listItemView!!
    }
}