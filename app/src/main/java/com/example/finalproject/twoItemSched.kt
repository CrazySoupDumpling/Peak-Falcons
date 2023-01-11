package com.example.finalproject
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties



@Composable
fun popUpCongrat(YesNo: Boolean){
    if(YesNo){
        Popup(
            alignment = Alignment.TopCenter,
            properties = PopupProperties()
        ) {
            Box(
                Modifier
                    .padding(top = 5.dp)

                    .border(1.dp, color = Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "You have no more items to complete!",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 5.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Composable
fun twoItemSched(
    scheduleTesting: Schedule,
    navController: NavController

) {

    var popUpYN by remember{ mutableStateOf(false) }
    val schedule = scheduleTesting
    var tIList = remember { mutableStateListOf<Boolean>()}
    var itemNum by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween){

        Row(Modifier.fillMaxWidth()) {
            Text(

                text = schedule?.name.toString(),
                modifier = Modifier
                    .padding(all = 20.dp)
                    .padding(top = 15.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {navController.navigate(route = Screens.Schedule.route)}, modifier = Modifier.width(150.dp)) {
                Text(text = "exit", modifier = Modifier.padding(all = 20.dp))
            }
        }
        var toSize: Int
        if (schedule != null) {
            toSize = schedule?.items?.scheduleItems?.size-1
        } else {
            toSize = 0
        }
        if(toSize == 0){
            popUpYN = true
            navController.navigate(route = Screens.Schedule.route)
        }
        if(popUpYN){
            popUpCongrat(YesNo = true)
        }
        val imageModifier = Modifier
            .size(150.dp)
            .border(BorderStroke(0.dp, Color.Black))
            //.background(Color.Yellow)
            .align(Alignment.CenterHorizontally)



        Column(){
            Box(
                modifier = Modifier
                    .border(20.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Text(
                    text = schedule.items.scheduleItems[itemNum],
                    modifier = Modifier.padding(all = 30.dp),
                    fontSize = 50.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            if(itemNum+1<toSize) {
                Image(
                    painterResource(R.drawable.downarrow),
                    contentDescription = "Item Completed",
                    modifier = imageModifier
                )
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .border(20.dp, Color.Black)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = schedule.items.scheduleItems[itemNum+1],
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
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Previous", modifier = Modifier.padding(all = 40.dp), fontSize = 20.sp)
            }
            //Spacer(modifier = Modifier.weight(.25f))
            Button(
                onClick = {
                    if(itemNum<toSize)itemNum++;
                    if(itemNum==toSize){
                        popUpYN = true;
                        navController.navigate(route = Screens.Schedule.route)}
                },
                    modifier = Modifier.width(200.dp)
                    ) {

                    Text(text = "Next", modifier = Modifier.padding(all = 40.dp), fontSize = 20.sp)
            }
        }
        //Spacer(modifier = Modifier.weight(.1f))
    }

}


@Preview(showBackground = true)
@Composable
fun twoPreview() {
    val list = SnapshotStateList<String>()
    val list1 = SnapshotStateList<String>()
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
    twoItemSched(schedTest, navController = navController)

}
