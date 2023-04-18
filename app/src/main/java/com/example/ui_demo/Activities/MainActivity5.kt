package com.example.ui_demo.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ui_demo.Adapters.GridAdapter
import com.example.ui_demo.Models.GridDataModel
import com.example.ui_demo.R


class MainActivity5 : AppCompatActivity(), BaseActivity {

    private lateinit var gridListView: GridView
    private lateinit var  goToFragmentScreenButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        uiBinding()
        attachListners()
        setUp()
    }

    private fun setUp() {
        val headerText = intent.extras?.getString("headerTitle") ?: "No message found"
        title = headerText

        val dummyDataList = getData()
        val gridListAdapter = GridAdapter(this, dummyDataList)
        gridListView.adapter = gridListAdapter
        gridListView.onItemClickListener = OnItemClickListener { parent, v, position, id ->
            Toast.makeText(
                this,
                "" + position,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun uiBinding() {
        gridListView = findViewById(R.id.gridLayout)
        goToFragmentScreenButton = findViewById(R.id.goToFragmentScreen)
    }

    override fun attachListners() {
        goToFragmentScreenButton.setOnClickListener {
            val intentActivity6 = Intent(this, MainActivity6::class.java)
            intentActivity6.putExtra("headerTitle","Fragment Demo")
            startActivity(intentActivity6);
        }
    }

    private fun getData(): ArrayList<GridDataModel> {
        val list: ArrayList<GridDataModel> = ArrayList()
        list.add(GridDataModel("DSA"))
        list.add(GridDataModel("JAVA"))
        list.add(GridDataModel("C++"))
        list.add(GridDataModel("Python"))
        list.add(GridDataModel("Javascript"))
        list.add(GridDataModel("DSA"))
        return list
    }

}
