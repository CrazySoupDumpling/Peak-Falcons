package com.example.finalproject

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext

@Composable
fun ItemByItemSched(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel

) {
    val mContext = LocalContext.current

    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.timerbeep)
    //mMediaPlayer.start()
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
            Button(onClick = {navController.navigate(route = Screens.Schedule.route)}, modifier = Modifier.width(150.dp),
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
    pressing next cancels timer and moves to next timer




     */
    if(schedule!= null && schedule.items.scheduleTimers[itemNum].toInt()>70){
        mMediaPlayer.start()
    }

    Row(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {if(itemNum>0)itemNum--},
            modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {
            Text(text = "Previous", modifier = Modifier.padding(all = 40.dp), fontSize = 15.sp)
        }
        //Spacer(modifier = Modifier.weight(.25f))
        Button(
            onClick = {
                if(itemNum<toSize)itemNum++
                else{congrats = true}
                      },
            modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {
            Text(text = "Next", modifier = Modifier.padding(all = 40.dp), fontSize = 15.sp)
        }
    }
    Column(Modifier.height(90.dp)) {
        Row {
            Button(onClick = {navController.navigate("${Screens.Checklist.route}/${scheduleID}")  }, enabled = true, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                Text(text = "Checklist")
            }
            Spacer(modifier = Modifier.weight(0.5f))

            if (schedule != null){
                Button(onClick = {navController.navigate("${Screens.ThisThen.route}/${scheduleID}")  }, enabled = schedule.items.scheduleItems.size>1, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))  ) {
                    Text(text = "This Then")
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Button(onClick = {navController.navigate("${Screens.ItembyItem.route}/${scheduleID}")  }, enabled = false, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen)) ) {
                Text(text = "One At a Time")
            }
        }
    }
    //Spacer(modifier = Modifier.weight(.1f))
}

}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//        val list = SnapshotStateList<String>()
//        val items = ScheduleItems(list)
//        val schedTest = Schedule("Test1",items)
//
//        list.add("Hi")
//        list.add("Middle")
//        list.add("Bye")
//        list.add("After End")
//        list.add("Later")
//    list.add("Later")
//    list.add("Later")
//    list.add("Later")
//    list.add("Later")
//    list.add("Later")
//    list.add("Last")
//
//        var navController = rememberNavController()
//        //SetupNavGraph(navController = navController, viewModel = null)
////        ItemByItemSched(schedTest, navController = navController)
//
//}
//
