package com.example.ui_demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var btn : Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.MainActivityTitle)
        uiLinking()
        initClickListeners()
    }

    private fun uiLinking () {
        btn = findViewById(R.id.btn)
    }

    private fun initClickListeners() {
        btn?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v){
            btn ->{
                val navigatorIntent = Intent(this,MainActivity2::class.java)
                startActivity(navigatorIntent)
            }
       }
    }


}