package com.example.ui_demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity5 : AppCompatActivity() ,BaseActivity {

    private lateinit var gridListView : GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        uiBinding()
        setUp()
    }

    private fun setUp() {
        val headerText = intent.extras?.getString("headerTitle") ?: "No message found"
        title = headerText

        val dummyDataList = getData()
        val gridListAdapter = GridAdapter(this,dummyDataList)
        gridListView.adapter = gridListAdapter
    }

    override fun uiBinding() {
        gridListView = findViewById(R.id.gridLayout)
    }

    override fun attachListners() {
    }

    private fun getData (): ArrayList<GridDataModel> {
        val list : ArrayList<GridDataModel> = ArrayList()
        list.add(GridDataModel("DSA"))
        list.add(GridDataModel("JAVA"))
        list.add(GridDataModel("C++"))
        list.add(GridDataModel("Python"))
        list.add(GridDataModel("Javascript"))
        list.add(GridDataModel("DSA"))
        return list
    }

}
