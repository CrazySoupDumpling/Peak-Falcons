package com.example.finalproject
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.collections.ArrayList
 
@Composable
fun ItemByItemSched(
    scheduleTesting: Schedule,
    navController: NavController

) {

    val schedule = scheduleTesting
    var IBIList = remember { mutableStateListOf<Boolean>()}

    Column(Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {

        Row(Modifier.fillMaxWidth()) {
            Text(

                text = schedule?.name.toString(),
                modifier = Modifier
                    .padding(all = 20.dp)
                    .padding(top = 15.dp)
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

        for (i in 0..toSize) {
            if (i >= IBIList.size){
                IBIList.add(false)
            }
            Button(
                onClick = { IBIList[i] = !IBIList[i] },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                    .padding(vertical = 20.dp).height(100.dp)
            ) {
                if (IBIList[i]) {

                    Image(
                        painterResource(R.drawable.completedcheck),
                        contentDescription = "Item Completed",
                        modifier = Modifier.padding(horizontal = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                } else {
                    Image(
                        painterResource(R.drawable.uncompletedcheck),
                        contentDescription = "Item Not Completed",
                        modifier = Modifier.padding(horizontal = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
//                Spacer(modifier = Modifier.weight(1f))
                if (schedule != null) {
                    Text(
                        text = schedule.items.scheduleItems[i],
                        modifier = Modifier.padding(all = 20.dp).weight(1f)
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        val list = ArrayList<String>()
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
    list.add("Later")

        var navController = rememberNavController()
        //SetupNavGraph(navController = navController, viewModel = null)
        ItemByItemSched(schedTest, navController = navController)

}

