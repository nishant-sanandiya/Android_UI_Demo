package com.example.ui_demo.Activities

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
        }
        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)
        uiBinding()
        attachListners()
        runAsyncTask()
    }

    override fun onStart() {
        super.onStart()
        // Bind to BindService.
        Intent(this, BindService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
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

    private fun startNotification() {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity5::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }
        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(
                this,
                R.string.CHANNEL_DEFAULT_IMPORTANCE.toString()
            )
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.drawable.floating_bg)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true) // for force display in android 13 and above (otherwise is swipe able)
                .build()
        } else {
            Notification.Builder(this)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.drawable.floating_bg)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true) // for force display in android 13 and above (otherwise is swipe able)
                .build()
        }
//        startForeground(1, notification)
    }

    private fun getRandomNumber() {
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