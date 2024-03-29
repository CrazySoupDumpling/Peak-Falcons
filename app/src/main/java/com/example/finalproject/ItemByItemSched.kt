package com.example.finalproject

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import android.os.CountDownTimer
import android.util.Log


@Composable
fun ItemByItemSched(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel

) {

    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? = viewModel.searchResults.observeAsState().value?.get(0)
    var itemNum by remember { mutableStateOf(0) }
    var congrats by remember { mutableStateOf(false)}

    if(congrats){
        PopUpCongrats {
            navController.navigate(Screens.Schedule.route){
                popUpTo(Screens.Schedule.route) {
                    inclusive = true
                }
            }
        }
    }


    val mContext = LocalContext.current
    var mediaPlayer by remember {mutableStateOf(MediaPlayer.create(mContext, R.raw.timerbeep) )}

    var timer1 by remember { mutableStateOf(if(schedule != null){createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )}else{Log.d("TAG", "nothin was created")
        createTimer(0,mediaPlayer )})}








Column(
    Modifier
        .fillMaxHeight()
        .background(colorResource(R.color.Background)),
    verticalArrangement = Arrangement.SpaceBetween){

        Row(Modifier.fillMaxWidth()) {
            Text(

                text = schedule?.name.toString(),
                modifier = Modifier
                    .padding(all = 20.dp)
                    .weight(1f),
                fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center
            )
            Button(onClick = {timer1.cancel()
                navController.navigate(route = Screens.Schedule.route)}, modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))) {
                Text(text = "exit", modifier = Modifier.padding(all = 20.dp))
            }
        }
        val toSize: Int = if (schedule != null) {
            schedule.items.scheduleItems.size-1
        } else {
            0
        }



    Row{
        Box(
            modifier = Modifier
                .border(20.dp, Color.Black)
                .fillMaxWidth()
        ) {
            if (schedule != null) {


                Text(
                    text = schedule.items.scheduleItems[itemNum],
                    modifier = Modifier.padding(all = 30.dp),
                    fontSize = 50.sp
                )
            }
        }
    }
    /*
    Plan:
    opening the page you create a timer
    pressing next cancels the timer and creates a new one
    previous does the same





     */
//    var millisInFuture by remember { mutableStateOf(schedule.items.scheduleTimers[itemNum].toLong()) }
//    val countDownTimer =
//        object : CountDownTimer(millisInFuture, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                Log.d("TAG", "onTick: ")
//            }
//
//            override fun onFinish() {
//                mediaPlayer.start()
//            }
//        }
//    if (schedule != null) {
//        if(schedule.items.scheduleTimers[itemNum] == "25"){
//            Log.d("TAG", "onTick: ")
//            createTimer(schedule.items.scheduleTimers[itemNum].toLong(), ,mediaPlayer )
//        }
//    }






    Row(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                if(itemNum>0){itemNum--}
                timer1.cancel()
                if (schedule != null) {
                    timer1 = createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )
                }
                      },
            modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {
            Text(text = "Previous", modifier = Modifier.padding(all = 40.dp), fontSize = 15.sp)
        }
        //Spacer(modifier = Modifier.weight(.25f))
        Button(
            onClick = {
                if(itemNum<toSize){
                    itemNum++
                    timer1.cancel()
                    if (schedule != null) {
                        timer1 = createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )
                    }
                }
                else{congrats = true}
                      },
            modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {
            Text(text = "Next", modifier = Modifier.padding(all = 40.dp), fontSize = 15.sp)
        }
    }
    Column(Modifier.height(90.dp)) {
        Row {
            Button(onClick = {timer1.cancel()
                navController.navigate("${Screens.Checklist.route}/${scheduleID}")  }, enabled = true, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                Text(text = "Checklist")
            }
            Spacer(modifier = Modifier.weight(0.5f))

            if (schedule != null){
                Button(onClick = {timer1.cancel()
                    navController.navigate("${Screens.ThisThen.route}/${scheduleID}")  }, enabled = schedule.items.scheduleItems.size>1, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))  ) {
                    Text(text = "This Then")
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Button(onClick = {timer1.cancel()
                navController.navigate("${Screens.ItembyItem.route}/${scheduleID}")  }, enabled = false, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen)) ) {
                Text(text = "One At a Time")
            }
        }
    }
    //Spacer(modifier = Modifier.weight(.1f))
}

}

fun createTimer(time: Long, mediaPlayer: MediaPlayer): CountDownTimer {
    return object : CountDownTimer(time * 60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d("TAG", "onTick: $millisUntilFinished")
        }

        override fun onFinish() {
            mediaPlayer.start()
        }
    }.start()
}

