package com.example.ui_demo

import SimpleListAdapter
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat


class MainActivity4 : AppCompatActivity() {

    lateinit var checkPermissionButtonView : Button
    private lateinit var ratingView : RatingBar
    private lateinit var simpleListView : ListView
    val PERMISSION_REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        checkPermissionButtonView = findViewById(R.id.checkPermissionButton)
        ratingView = findViewById(R.id.ratingBar)
        simpleListView = findViewById(R.id.simpleList)
        checkPermissionButtonView.setOnClickListener {
            requestPermission()
        }
       ratingView.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
           Log.d("Rating","rating :- ${rating.toInt()}")
       }
        val list: MutableList<DummyData> = ArrayList()
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))
        list.add(DummyData("Hii", "hji", "j", 5))

        val adapter = SimpleListAdapter(this, list as ArrayList<DummyData>)
        simpleListView.adapter = adapter
    }


    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION, CAMERA),
            PERMISSION_REQUEST_CODE
        )
    }
}