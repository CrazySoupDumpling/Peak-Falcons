package com.example.finalproject
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.collections.ArrayList
 
@Composable
fun ItemByItemSched(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel

) {

    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? = viewModel.searchResults.observeAsState().value?.get(0)
    var IBIList = remember { mutableStateListOf<Boolean>()}
    var itemNum by remember { mutableStateOf(0) }
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
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {navController.navigate(route = Screens.Schedule.route)}, modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))) {
                Text(text = "exit", modifier = Modifier.padding(all = 20.dp))
            }
        }
        var toSize: Int
        if (schedule != null) {
            toSize = schedule.items.scheduleItems.size-1
        } else {
            toSize = 0
        }



    Row(){
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


    Row(
        modifier = Modifier
            .weight(1f, false)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {if(itemNum>0)itemNum--},
            modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {
            Text(text = "Previous", modifier = Modifier.padding(all = 40.dp), fontSize = 20.sp)
        }
        //Spacer(modifier = Modifier.weight(.25f))
        Button(
            onClick = {if(itemNum<toSize)itemNum++},
            modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {
            Text(text = "Next", modifier = Modifier.padding(all = 40.dp), fontSize = 20.sp)
        }
    }
    //Spacer(modifier = Modifier.weight(.1f))
}

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        val list = SnapshotStateList<String>()
        val items = ScheduleItems(list)
        val schedTest = Schedule("Test1",items)

        list.add("Hi")
        list.add("Middle")
        list.add("Bye")
        list.add("After End")
        list.add("Later")
    list.add("Later")
    list.add("Later")
    list.add("Later")
    list.add("Later")
    list.add("Later")
    list.add("Last")

        var navController = rememberNavController()
        //SetupNavGraph(navController = navController, viewModel = null)
//        ItemByItemSched(schedTest, navController = navController)

}

