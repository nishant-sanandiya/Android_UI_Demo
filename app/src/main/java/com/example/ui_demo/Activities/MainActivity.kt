package com.example.ui_demo.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ui_demo.R

class MainActivity : AppCompatActivity(), View.OnClickListener, BaseActivity {

    private var btn: Button? = null
    private var nextApiActivityButtonView: Button? = null
    private var serviceActivityBtnView: Button? = null
    private lateinit var nextBroadcastActivityBtnView : Button
    private lateinit var nextDialogActivityBtnView : Button

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
            nextApiActivityButtonView -> {
                val navigatorIntent = Intent(this, ApiActivity::class.java)
                startActivity(navigatorIntent)
            }
            serviceActivityBtnView -> {
                val navigatorIntent = Intent(this, ServiceDemo::class.java)
                startActivity(navigatorIntent)
            }
            nextBroadcastActivityBtnView ->{
                Intent(this,BroadcastReceiverDemo::class.java).let {
                    startActivity(it)
                }
            }
            nextDialogActivityBtnView ->{
                Intent(this,Dialogs::class.java).let {
                    startActivity(it)
                }
            }
        }
    }

    override fun uiBinding() {
        btn = findViewById(R.id.btn)
        nextApiActivityButtonView = findViewById(R.id.buttonx)
        serviceActivityBtnView = findViewById(R.id.serviceActivityBtn)
        nextBroadcastActivityBtnView = findViewById(R.id.nextBroadcastActivityBtn)
        nextDialogActivityBtnView = findViewById(R.id.nextDialogActivityBtn)
    }

    override fun attachListners() {
        btn?.setOnClickListener(this)
        nextApiActivityButtonView?.setOnClickListener(this)
        serviceActivityBtnView?.setOnClickListener(this)
        nextBroadcastActivityBtnView.setOnClickListener(this)
        nextDialogActivityBtnView.setOnClickListener(this)
    }
}