package com.example.ui_demo.Activities

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.ui_demo.Models.DummyData
import com.example.ui_demo.R
import com.example.ui_demo.Adapters.SimpleListAdapter


class MainActivity4 : AppCompatActivity(), BaseActivity {

    private lateinit var checkPermissionButtonView: Button
    private lateinit var ratingView: RatingBar
    private lateinit var simpleListView: ListView
    private lateinit var goToNextButtonView: Button
    private val permissionRequestCode = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        uiBinding()
        attachListners()
        setListAdapter()
    }

    override fun uiBinding() {
        checkPermissionButtonView = findViewById(R.id.checkPermissionButton)
        ratingView = findViewById(R.id.ratingBar)
        simpleListView = findViewById(R.id.simpleList)
        goToNextButtonView = findViewById(R.id.goToNextScreen)
    }

    override fun attachListners() {
        checkPermissionButtonView.setOnClickListener {
            requestPermission()
        }

        ratingView.setOnRatingBarChangeListener { _, rating, _ ->
            Log.d("Rating", "rating :- ${rating.toInt()}")
        }

        goToNextButtonView.setOnClickListener {
            val nextIntent = Intent(this, MainActivity5::class.java)
            nextIntent.putExtra("headerTitle", "Grid List View")
            startActivity(nextIntent)
        }
    }

    private fun setListAdapter() {
        val list: MutableList<DummyData> = ArrayList()
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))
        list.add(DummyData("hji", "j", 5))

        val adapter = SimpleListAdapter(this, list as ArrayList<DummyData>)
        simpleListView.adapter = adapter
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(ACCESS_FINE_LOCATION, CAMERA), permissionRequestCode
        )
    }
}