package com.example.ui_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity2 : AppCompatActivity() {

    private var listView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        uiLinking()
        setListData()
    }

    private fun setListData() {
        var list: List<DummyData> = ArrayList()
        list = getData()

        var adapter = HorizontalCardAdapter(list,this)
        listView?.adapter = adapter
        listView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun uiLinking() {
        listView = findViewById(R.id.horizontalList)
    }


    private fun getData(): List<DummyData> {
        val list: MutableList<DummyData> = ArrayList()
        list.add(
            DummyData(
                "First Exam",
                "May 23, 2015",
                "Best Of Luck",
                2
            )
        )
        list.add(
            DummyData(
                "Second Exam",
                "June 09, 2015",
                "b of l",
                5
            )
        )
        list.add(
            DummyData(
                "My Test Exam",
                "April 27, 2017",
                "This is testing exam ..",
                3
            )
        )
        list.add(
            DummyData(
                "First Exam",
                "May 23, 2015",
                "Luck",
                2
            )
        )
        list.add(
            DummyData(
                "First Exam",
                "My 23, 205",
                "Best Of Luck",
                3
            )
        )
        list.add(
            DummyData(
                "First Exam",
                "May 2, 2015",
                "Best Luck",
                2
            )
        )
        return list
    }


}