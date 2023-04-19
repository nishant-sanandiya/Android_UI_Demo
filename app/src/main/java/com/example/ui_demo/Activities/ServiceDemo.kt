package com.example.ui_demo.Activities

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ui_demo.R
import com.example.ui_demo.Services.BindService
import com.example.ui_demo.Services.MyIntentService
import com.example.ui_demo.Services.MyService
import com.example.ui_demo.Utils.AsyncTaskDemo

@Suppress("DEPRECATION")
class ServiceDemo : AppCompatActivity(), BaseActivity {

    private val asyncTaskDemoInstance = AsyncTaskDemo(5)
    private lateinit var startIntentServiceBtnView: Button
    private lateinit var stopIntentServiceBtnView: Button
    private lateinit var intentServiceTextView: TextView
    private lateinit var startServiceBtnView: Button
    private lateinit var stopServiceBtnView: Button
    private lateinit var serviceTextView: TextView
    private lateinit var editTextView: EditText
    private lateinit var sendDataBtnView: Button
    private lateinit var getRandomNumberBtnView: Button
    private lateinit var mService: BindService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService().  */
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance.
            val binder = service as BindService.LocalBinder
            mService = binder.getService()
            mBound = true
            Log.d("Lifecycle", "onServiceConnected Called")
        }
        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
            Log.d("Lifecycle", "onServiceDisconnected Called")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)
        uiBinding()
        attachListners()
        runAsyncTask()
        requestPermissions()
        Log.d("Lifecycle", "onCreate Activity Called")
    }

    private fun requestPermissions () {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100
        )
    }

    override fun onStart() {
        super.onStart()
        // Bind to BindService.
        Log.d("Lifecycle", "onStart Activity Called")
        Intent(this, BindService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop Activity Called")
        unbindService(connection)
        mBound = false
    }

    override fun uiBinding() {
        startIntentServiceBtnView = findViewById(R.id.startIntentServiceBtn)
        stopIntentServiceBtnView = findViewById(R.id.stopIntentServiceBtn)
        intentServiceTextView = findViewById(R.id.intentServiceText)
        startServiceBtnView = findViewById(R.id.startServiceBtn)
        stopServiceBtnView = findViewById(R.id.stopServiceBtn)
        serviceTextView = findViewById(R.id.serviceText)
        editTextView = findViewById(R.id.serviceTextInput)
        sendDataBtnView = findViewById(R.id.sendDataBtn)
        getRandomNumberBtnView = findViewById(R.id.getRandomNumber)
    }

    override fun attachListners() {
        startIntentServiceBtnView.setOnClickListener {
            startMyIntentService()
        }
        stopIntentServiceBtnView.setOnClickListener {
            stopMyIntentService()
        }
        startServiceBtnView.setOnClickListener {
            startMyService()
        }
        stopServiceBtnView.setOnClickListener {
            stopMyService()
        }
        sendDataBtnView.setOnClickListener {
            passDataToMyService()
        }
        getRandomNumberBtnView.setOnClickListener {
            getRandomNumber()
        }
    }

    private fun startMyIntentService() {
        Intent(this, MyIntentService().javaClass).also {
            startService(it)
            intentServiceTextView.text = "Intent Service is Running"
        }
    }

    private fun stopMyIntentService() {
        MyIntentService.stopService()
        intentServiceTextView.text = "Intent Service is Stopped"
    }

    private fun startMyService() {
        Intent(this, MyService().javaClass).also {
            startService(it)
            serviceTextView.text = "Service is Running"
        }
    }

    private fun stopMyService() {
        Intent(this, MyService().javaClass).also {
            stopService(it)
            serviceTextView.text = "Service is Stopped"
        }
    }

    private fun passDataToMyService() {
        Intent(this, MyService().javaClass).also {
            val text = editTextView.text.toString();
            it.putExtra("EXTRA_DATA", text)
            startService(it)
        }
    }

    private fun getRandomNumber() {
        Intent(this,BindService::class.java).also {
            startService(it)
        }
        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call is something that might hang, then put this request
            // in a separate thread to avoid slowing down the activity performance.
            val num: Int = mService.randomNumber
            Toast.makeText(this, "Random Number: $num", Toast.LENGTH_SHORT).show()
        }
    }

    private fun runAsyncTask() {
        asyncTaskDemoInstance.execute()
    }
}