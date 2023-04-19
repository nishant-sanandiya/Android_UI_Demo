package com.example.ui_demo.Services

import android.Manifest
import android.app.*
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.ui_demo.Activities.MainActivity5
import com.example.ui_demo.R
import java.util.*

class BindService : Service() {

    // Binder given to clients.
    private val binder = LocalBinder()

    // Random number generator.
    private val mGenerator = Random()

    /** Method for clients.  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("Lifecycle", "onBind Called")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Lifecycle", "onCreate Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy Called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Lifecycle", "onStartCommand Called")
        startNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent): Boolean {
        // All clients have unbound with unbindService()
        Log.d("Lifecycle", "onUnbind Called")
        return false
    }

    /**
     * Class used for the client Binder. Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods.
        fun getService(): BindService = this@BindService
    }

    private fun startNotification() {

        val channelId: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                getString(R.string.message_lock_running),
                getString(R.string.message_lock_running)
            )
        } else {
            getString(R.string.message_lock_running)
        }
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity5::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }
        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.drawable.floating_bg)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
//                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true) // for force display in android 13 and above (otherwise is swipe able)
                .build()
        } else {
            Notification.Builder(this)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.drawable.floating_bg)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
//                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true) // for force display in android 13 and above (otherwise is swipe able)
                .build()
        }
        startForeground(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

}