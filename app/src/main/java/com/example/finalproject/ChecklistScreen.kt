package com.example.finalproject

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.collections.ArrayList

@Composable
fun ChecklistScreen(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel
) {


    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? = viewModel.searchResults.observeAsState().value?.get(0)
    var checkedList = remember { mutableStateListOf<Boolean>() }
    Column(Modifier.fillMaxHeight()) {
        Column(Modifier.verticalScroll(rememberScrollState()).weight(1f)) {

            Row(Modifier.fillMaxWidth()) {
                Text(

                    text = schedule?.name.toString(),
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .padding(top = 15.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { navController.navigate(route = Screens.Schedule.route) },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "exit", modifier = Modifier.padding(all = 20.dp))
                }
            }

            if (schedule != null) {
                for (i in 0..schedule?.items?.scheduleItems?.size - 1) {
                    if (i >= checkedList.size) {
                        checkedList.add(false)
                    }
                    Button(
                        onClick = { checkedList[i] = !checkedList[i] },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                            .padding(vertical = 20.dp).height(100.dp)
                    ) {
                        if (checkedList[i]) {

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
        Column(Modifier.height(90.dp)) {
            Row() {
                Button(onClick = {navController.navigate("${Screens.Checklist.route}/${scheduleID}")  }, enabled = false) {
                    Text(text = "Checklist")
                }
                Spacer(modifier = Modifier.weight(0.5f))

                Button(onClick = {navController.navigate("${Screens.ThisThen.route}/${scheduleID}")  }, enabled = true  ) {
                    Text(text = "This Then")
                }
                Spacer(modifier = Modifier.weight(0.5f))
                Button(onClick = {navController.navigate("${Screens.ItembyItem.route}/${scheduleID}")  }, enabled = true ) {
                    Text(text = "One At a Time")
                }
            }
        }
    }
}

