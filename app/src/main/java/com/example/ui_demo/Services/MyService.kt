package com.example.ui_demo.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "MyService"

    init {
        Log.i(TAG, "init block is running of service class.....")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "Service is Bind.....")
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "Service is Destroyed.....")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.extras?.getString("EXTRA_DATA")
        dataString?.let {
            Log.i(TAG, it)
        }
        return START_REDELIVER_INTENT
    }
}