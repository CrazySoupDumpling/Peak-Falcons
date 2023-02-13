package com.example.finalproject


import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.AlertDialog
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun ChecklistScreen(
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

    if(schedule == null)Log.d("TAG", "nothin was created")

    val checkedList = remember { mutableStateListOf<Boolean>() }
    Column(
        Modifier
            .fillMaxHeight()
            .background(colorResource(R.color.Background))) {
        var timer1 by remember { mutableStateOf(if(schedule != null){createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )}else{Log.d("TAG", "nothin was created")
            createTimer(45,mediaPlayer )})}
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)) {

            Row(Modifier.fillMaxWidth()) {
                Text(

                    text = schedule?.name.toString(),
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .weight(1f),
                    fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center
                )
                Button(
                    onClick = {timer1.cancel()
                        navController.navigate(route = Screens.Schedule.route) },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))
                ) {
                    Text(text = "exit", modifier = Modifier.padding(all = 20.dp))
                }
            }

            var GobackPOP by remember { mutableStateOf(false) }
            if(GobackPOP) GoBack(GobackPOP)
            if (schedule != null) {


                for (i in 0 until schedule.items.scheduleItems.size) {
                    if (i >= checkedList.size) {
                        checkedList.add(false)
                    }
                    Button(
                        onClick = {if(i == itemNum){
                            Log.d("TAG", "itemNum: $itemNum")
                            if(checkedList[i]){
                                itemNum--
                                checkedList[i] = !checkedList[i]
                                timer1.cancel()
                                if (schedule != null) {
                                    timer1 = createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )
                                }
                            }
                            if(!checkedList[i]){
                                itemNum++
                                checkedList[i] = !checkedList[i]
                                timer1.cancel()
                                if (schedule != null) {
                                    timer1 = createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )
                                }
                            }
                            Log.d("TAG", "itemNum: $itemNum")
                        }else if(i == itemNum-1 && checkedList[i]){
                            itemNum--
                            checkedList[i] = !checkedList[i]
                            timer1.cancel()
                            if (schedule != null) {
                                timer1 = createTimer(schedule.items.scheduleTimers[itemNum].toLong(),mediaPlayer )
                            }
                        }
                        else{
                            GobackPOP = true
                        } },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 20.dp)
                            .height(100.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
                    ) {
                        if (checkedList[i]) {

                            Image(
                                painterResource(R.drawable.completedcheck),
                                contentDescription = "Item Completed",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        } else {
                            Image(
                                painterResource(R.drawable.uncompletedcheck),
                                contentDescription = "Item Not Completed",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
//                Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = schedule.items.scheduleItems[i],
                            modifier = Modifier
                                .padding(all = 20.dp)
                                .weight(1f)
                        )
                    }
                }
            }
        }
        Column(Modifier.height(90.dp)) {
            Row {
                Button(onClick = {timer1.cancel()
                    navController.navigate("${Screens.Checklist.route}/${scheduleID}")  }, enabled = false, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
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
                    navController.navigate("${Screens.ItembyItem.route}/${scheduleID}")  }, enabled = true, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen)) ) {
                    Text(text = "One At a Time")
                }
            }
        }
    }
}

@Composable
fun GoBack(Pop: Boolean) {
    Column(modifier = Modifier.background(colorResource(R.color.Background))) {
        Spacer(modifier = Modifier.height(100.dp))
        Box {
            var popFr by remember { mutableStateOf(Pop) }
            if(popFr)
            AlertDialog(

                onDismissRequest = {popFr = false },
                title = {
                    Text(text = "Finish Your Previous Tasks First")
                },
                text = {
                    Text("You tried to skip a task, finish those first before moving on! You got this!")
                },
                buttons = {}
            )
        }
    }
}

fun createTimer1(time: Long, mediaPlayer: MediaPlayer): CountDownTimer {
    return object : CountDownTimer(time * 60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d("TAG", "onTick: $millisUntilFinished")
        }

        override fun onFinish() {

        }
    }.start()
}