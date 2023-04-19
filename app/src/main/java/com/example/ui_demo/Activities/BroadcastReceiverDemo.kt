package com.example.ui_demo.Activities

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ui_demo.Broadcaster.MyReceiver
import com.example.ui_demo.R

class BroadcastReceiverDemo : AppCompatActivity(), BaseActivity, View.OnClickListener {

    private val myReceiverInstance: BroadcastReceiver = MyReceiver()
    private lateinit var sendBroadcastBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver_demo)
        title = "Broadcast Receiver Demo"
        uiBinding()
        attachListners()
        registerBroadcasterHandler()
    }

    override fun uiBinding() {
        sendBroadcastBtn = findViewById(R.id.sendBroadcastBtn)
    }

    override fun attachListners() {
        sendBroadcastBtn.setOnClickListener(this)
    }

    private fun registerBroadcasterHandler() {
        val intentFilter = IntentFilter(Intent.ACTION_DEFAULT)
        registerReceiver(myReceiverInstance, intentFilter)
    }

    private fun unRegisterBroadcasterHandler() {
        unregisterReceiver(myReceiverInstance);
    }

    private fun triggerBroadcaster() {
        Intent().also { intent ->
            intent.action = Intent.ACTION_DEFAULT
            intent.putExtra("data", "Nothing to see here, move along.")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterBroadcasterHandler();
    }

    override fun onClick(v: View?) {
        when (v) {
            sendBroadcastBtn -> {
                triggerBroadcaster()
            }
        }
    }
}