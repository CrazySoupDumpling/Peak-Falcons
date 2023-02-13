package com.example.finalproject

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotifyWork(val context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        val id = inputData.getLong("10", 0).toInt()
        val name = inputData.getString("Schedule")
        if (name != null) {
            sendNotification(context, id, name)
        }
        return success()
    }
}

private fun sendNotification(context: Context, id: Int, name: String){

    val notificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val builder = NotificationCompat.Builder(context, "5")
    var notification = builder
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Alarm activated")
        .setContentText("It is time for your schedule: $name")
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

    createNotificationChannel(context)

    notificationManager?.notify(id, notification)
}