package com.example.ui_demo.Broadcaster

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    val TAG = "BROADCAST"
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

//        val binding: ActivityNameBinding = ActivityNameBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)

        val dataString = intent.extras?.getString("data")
        Log.d(TAG, "onReceive Called ${dataString}");
        Toast.makeText(context,"$dataString",Toast.LENGTH_LONG).show()
//        Snackbar.make(context, "onReceive Called", Snackbar.LENGTH_LONG).show();
    }
}