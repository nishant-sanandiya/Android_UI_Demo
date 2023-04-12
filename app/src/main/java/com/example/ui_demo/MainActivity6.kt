package com.example.ui_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity6 : AppCompatActivity(), BaseActivity, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        setHeaderTitle()
        uiBinding()
        attachListners()
    }

    private fun setHeaderTitle() {
        val title = intent.extras?.getString("headerTitle")
        if (title != null) {
            setTitle(title)
        }
    }

    override fun uiBinding() {
    }

    override fun attachListners() {
    }

    override fun onClick(v: View?) {
    }
}