package com.example.ui_demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout

class MainActivity : AppCompatActivity(), View.OnClickListener, BaseActivity {

    private var btn: Button? = null
    private var nextApiActivityButtonView: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.MainActivityTitle)
        uiBinding()
        attachListners()
    }

    override fun onClick(v: View?) {
        when (v) {
            btn -> {
                val navigatorIntent = Intent(this, MainActivity2::class.java)
                startActivity(navigatorIntent)
            }
            nextApiActivityButtonView->{
                val navigatorIntent = Intent(this, ApiActivity::class.java)
                startActivity(navigatorIntent)
            }
        }
    }

    override fun uiBinding() {
        btn = findViewById(R.id.btn)
        nextApiActivityButtonView = findViewById(R.id.buttonx)
    }

    override fun attachListners() {
        btn?.setOnClickListener(this)
        nextApiActivityButtonView?.setOnClickListener(this)
    }
}