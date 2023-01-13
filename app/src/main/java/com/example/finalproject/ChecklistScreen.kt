package com.example.finalproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChecklistScreen(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel
) {


    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? = viewModel.searchResults.observeAsState().value?.get(0)
    val checkedList = remember { mutableStateListOf<Boolean>() }
    Column(Modifier.fillMaxHeight().background(colorResource(R.color.Background))) {
        Column(Modifier.verticalScroll(rememberScrollState()).weight(1f)) {

            Row(Modifier.fillMaxWidth()) {
                Text(

                    text = schedule?.name.toString(),
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .weight(1f),
                    fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center
                )
                Button(
                    onClick = { navController.navigate(route = Screens.Schedule.route) },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))
                ) {
                    Text(text = "exit", modifier = Modifier.padding(all = 20.dp))
                }
            }

            if (schedule != null) {
                for (i in 0 until schedule.items.scheduleItems.size) {
                    if (i >= checkedList.size) {
                        checkedList.add(false)
                    }
                    Button(
                        onClick = { checkedList[i] = !checkedList[i] },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                            .padding(vertical = 20.dp).height(100.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
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
                        Text(
                            text = schedule.items.scheduleItems[i],
                            modifier = Modifier.padding(all = 20.dp).weight(1f)
                        )
                    }
                }
            }
        }
        Column(Modifier.height(90.dp)) {
            Row {
                Button(onClick = {navController.navigate("${Screens.Checklist.route}/${scheduleID}")  }, enabled = false, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                    Text(text = "Checklist")
                }
                Spacer(modifier = Modifier.weight(0.5f))
                if (schedule != null){
                    Button(onClick = {navController.navigate("${Screens.ThisThen.route}/${scheduleID}")  }, enabled = schedule.items.scheduleItems.size>1, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))  ) {
                        Text(text = "This Then")
                    }
                }
                Spacer(modifier = Modifier.weight(0.5f))
                Button(onClick = {navController.navigate("${Screens.ItembyItem.route}/${scheduleID}")  }, enabled = true, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen)) ) {
                    Text(text = "One At a Time")
                }
            }
        }
    }
}

