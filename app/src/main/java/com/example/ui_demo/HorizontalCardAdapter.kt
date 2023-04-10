package com.example.ui_demo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HorizontalCardAdapter(private val dataSet: List<DummyData>, private val context:Context) :
    RecyclerView.Adapter<HorizontalCardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleText)
        val subTitleTextView: TextView = view.findViewById(R.id.subTitleText)
        val taskTextView: TextView = view.findViewById(R.id.taskText)
        val listConstraintView: ConstraintLayout = view.findViewById(R.id.horizontalListCardID)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.horizontal_list_card, viewGroup, false)
        view.setOnClickListener {
            val intentInstance = Intent(context,MainActivity3::class.java)
            context.startActivity(intentInstance)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.titleTextView.text = dataSet[position].title
        viewHolder.subTitleTextView.text = dataSet[position].subTitle
        viewHolder.taskTextView.text = dataSet[position].taskNumber.toString()
        if (dataSet[position].taskNumber > 2) {
            viewHolder.taskTextView.setTextColor(Color.RED)
            viewHolder.taskTextView.textSize = 20F
            viewHolder.listConstraintView.setBackgroundResource(R.drawable.horizontal_card_2)
        }
    }

    override fun getItemCount() = dataSet.size
}
