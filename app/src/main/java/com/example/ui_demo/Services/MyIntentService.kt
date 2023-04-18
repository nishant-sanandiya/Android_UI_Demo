@file:Suppress("DEPRECATION")

package com.example.ui_demo.Services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyIntentService : IntentService("MyIntentServiceName") {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MyIntentService
        var isRunning: Boolean = false

        fun stopService() {
            isRunning = false
            instance.stopSelf()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        try {
            isRunning = true;
            while (isRunning) {
                Log.i("MyIntentService", "Intent Service is running")
                Thread.sleep(4000)
            }
        } catch (e: Throwable) {
            Toast.makeText(this@MyIntentService, "Error in MyIntent Service", Toast.LENGTH_LONG).show()
        }
    }
}