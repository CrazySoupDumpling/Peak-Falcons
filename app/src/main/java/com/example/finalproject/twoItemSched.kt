package com.example.finalproject
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties



@Composable
fun popUpCongrat(navigate: () -> Unit ) {
    Column(modifier = Modifier.background(colorResource(R.color.Background))) {
        Spacer(modifier = Modifier.height(100.dp))
        Box {

            AlertDialog(

                onDismissRequest = navigate,
                title = {
                    Text(text = "Congrats")
                },
                text = {
                    Text("You have no more items on your schedule")
                },
                buttons = {

                }

            )
        }
    }
}



@Composable
fun twoItemSched(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel

) {

    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? = viewModel.searchResults.observeAsState().value?.get(0)

    var popUpYN by remember{ mutableStateOf(false) }

    var tIList = remember { mutableStateListOf<Boolean>()}
    var itemNum by remember { mutableStateOf(0) }

    if(popUpYN){
        popUpCongrat {
            navController.navigate(Screens.Schedule.route){
                popUpTo(Screens.Schedule.route) {
                    inclusive = true
                }
            }
        }
    }
//    popUpCongrat(YesNo = true)
    Column(Modifier.fillMaxHeight().background(colorResource(R.color.Background)),
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
            Button(onClick = {navController.navigate(route = Screens.Schedule.route)}, modifier = Modifier.width(150.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))) {
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

        val imageModifier = Modifier
            .size(100.dp)
            .border(BorderStroke(0.dp, Color.Black))
            .align(Alignment.CenterHorizontally)


        if(itemNum<toSize) {
            Column() {

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
                Spacer(modifier = Modifier.height(30.dp))

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
                    if (schedule != null) {
                        Text(
                            text = schedule.items.scheduleItems[itemNum + 1],
                            modifier = Modifier.padding(all = 30.dp),
                            fontSize = 50.sp
                        )
                    }
                }

            }
        }else {
//            Row() {
//                Spacer(modifier = Modifier.weight(0.5f).height(50.dp))
//                Text(text = "You completed your Schedule", modifier = Modifier.height(50.dp), fontSize = 25.sp)
//                Spacer(modifier = Modifier.weight(0.5f).height(50.dp))
//            }
//            Button(onClick = {navController.navigate(Screens.Schedule.route)}, modifier = Modifier.fillMaxWidth().height(100.dp)){
//                Text(text = "Return to Schedule Screen")
//            }
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
                onClick = {
                    if(itemNum<toSize)itemNum++;
                    if(itemNum==toSize){

                        popUpYN = true;

//                        navController.navigate(route = Screens.Schedule.route)
                    }
                }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen)),
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
//    twoItemSched(schedTest, navController = navController)

}
