package com.example.finalproject

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    private var notificationManager: NotificationManager? = null

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            notificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            val intent = Intent(p0, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(p0, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val builder = NotificationCompat.Builder(p0, "5")
            var notification = builder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm activated")
                .setContentText("It is time for one of your schedules")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
            createNotificationChannel(p0)

            notificationManager?.notify(341, notification)
        }
    }
}

