package com.example.finalproject

import android.app.AlarmManager
import android.app.AlarmManager.*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit


@Composable
fun ScheduleScreen(
    schedules: List<Schedule>,
    navController: NavController,
    viewModel: MainViewModel
) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.Background))) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Your Schedules", modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(horizontal = 15.dp)
                , fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {navController.navigate(route = Screens.Edit.route)}, modifier = Modifier.width(150.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))) {
                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
            }
        }

        for(i in schedules.indices){
            Column {
                Button(
                    onClick = { navController.navigate(route = "${Screens.Checklist.route}/${schedules[i].id}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
                ) {
                    Text(
                        text = schedules[i].name,
                        Modifier
                            .padding(all = 20.dp)
                            .padding(top = 20.dp)
                    )
                }
                Row {
                    if (schedules[i].startTime == "N/A"){
                        Text("Alarm: Not applied", modifier = Modifier.padding(all = 10.dp))
                    }
                    else{
                        var ap = if (schedules[i].startTime.split(":")[0].toInt() > 12){
                            "pm"
                        }else{
                            "am"
                        }
                        val h = schedules[i].startTime.split(":")[0].toInt()%12
                        val m = schedules[i].startTime.split(":")[1]
                        Text("Alarm: $h:$m $ap", modifier = Modifier.padding(all = 10.dp))
                    }
                    Spacer(modifier = Modifier.width( 20.dp))
                    Switch(
                        enabled = schedules[i].startTime != "N/A",
                        checked = schedules[i].alarmEnabled,
                        onCheckedChange = {
                            schedules[i].alarmEnabled =
                                !schedules[i].alarmEnabled;viewModel.updateSchedule(schedules[i])

                            if (schedules[i].alarmEnabled){
                                val workTag = "notificationWork"
                                val data = Data.Builder()

                                val h = schedules[i].startTime.split(":")[0].toInt()
                                val m = schedules[i].startTime.split(":")[1].toInt()

                                val x = Calendar.getInstance().apply{
                                    set(Calendar.MILLISECOND, 0)
                                    set(Calendar.SECOND, 0)
                                    set(Calendar.MINUTE, m)
                                    set(Calendar.HOUR, h%12)
                                    set(Calendar.DAY_OF_YEAR, Calendar.getInstance().get(Calendar.DAY_OF_YEAR))
                                }
                                val delay = x.timeInMillis - Calendar.getInstance().timeInMillis
                                data.putString("Schedule", "ExampleA")

                                val notificationWork =
                                    PeriodicWorkRequestBuilder<NotifyWork>(1, TimeUnit.DAYS)
                                        .setInitialDelay(
                                            delay,
                                            TimeUnit.MILLISECONDS
                                        )

                                        .setInputData(data.build())
                                        .addTag(workTag)
                                        .build()
                                WorkManager.getInstance(context).enqueue(notificationWork)
                            }
                            else{
                            }
                        })

                }
            }
        }
    }
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create the NotificationChannel
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("5", name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FinalProjectTheme {
//        val list = ArrayList<Schedule>()
//        list.add(Schedule("Hi"))
//        list.add(Schedule("Middle"))
//        list.add(Schedule("Bye"))
//        list.add(Schedule("After End"))
//        list.add(Schedule("Later"))
//        var navController = rememberNavController()
//        SetupNavGraph(navController = navController)
//        ScheduleScreen(list, navController = navController)
//    }
//}