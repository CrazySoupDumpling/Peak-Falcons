package com.example.finalproject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EditScheduleScreen(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel
) {
    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? =  viewModel.searchResults.observeAsState().value?.get(0)
    lateinit var itemList: SnapshotStateList<String>
    if (schedule != null) {
        itemList = remember {schedule.items.scheduleItems }
    }else{
        itemList = remember {SnapshotStateList() }
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f)) {
            if (schedule != null) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = schedule.name, modifier = Modifier
                            .padding(all = 20.dp)
                            .padding(top = 15.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { navController.navigate(route = Screens.Edit.route) },
                        modifier = Modifier.width(150.dp)
                    ) {
                        Text(text = "Exit", modifier = Modifier.padding(all = 20.dp))
                    }
                }
                for (i in 0 until itemList.size) {
                    Text("Item #${i + 1}", modifier = Modifier.padding(all = 10.dp))
                    Row() {
                        TextField(
                            value = itemList[i],
                            onValueChange = { itemList[i] = it }
                        )
                        Button(onClick = { itemList.removeAt(i) }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                            Text("Delete")
                        }
                    }
                }
            }
            Button(onClick = {itemList.add("")}){
                Text("Add Item")
            }
        }
        Column(){
            Row() {
                Button(onClick = {
                    if (schedule != null) {
                        schedule.items = ScheduleItems(itemList)
                        viewModel.updateSchedule(schedule)
                        navController.navigate(Screens.Edit.route)
                    }

                }) {
                    Text("Save")
                }

            }
        }
    }

}
