package com.example.ui_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity2 : AppCompatActivity(), BaseActivity {

    private var listView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        uiBinding()
        attachListners()
        setListData()
    }

    private fun setListData() {
        val tempList = getData()
        val adapter = HorizontalCardAdapter(dataSet = tempList, context = this)
        listView?.adapter = adapter
        listView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getData(): List<DummyData> {
        val list: MutableList<DummyData> = ArrayList()
        list.add(
            DummyData(
                "May 23, 2015",
                "Best Of Luck",
                2
            )
        )
        list.add(
            DummyData(
                "June 09, 2015",
                "b of l",
                5
            )
        )
        list.add(
            DummyData(
                "April 27, 2017",
                "This is testing exam ..",
                3
            )
        )
        list.add(
            DummyData(
                "May 23, 2015",
                "Luck",
                2
            )
        )
        list.add(
            DummyData(
                "My 23, 205",
                "Best Of Luck",
                3
            )
        )
        list.add(
            DummyData(
                "May 2, 2015",
                "Best Luck",
                2
            )
        )
        return list
    }

    override fun uiBinding() {
        listView = findViewById(R.id.horizontalList)
    }

    override fun attachListners() {
    }
}