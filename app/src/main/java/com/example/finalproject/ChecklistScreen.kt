package com.example.finalproject

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChecklistScreen(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel
) {
    viewModel.findSchedule(scheduleID)
    val schedule: Schedule? = viewModel.searchResults.observeAsState().value?.get(0)
    Column(Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {

        Row(Modifier.fillMaxWidth()) {
            Text(

                text = schedule?.name.toString(),
                modifier = Modifier
                    .padding(all = 20.dp)
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(140.dp))
            Button(onClick = { navController.navigate(route = Screens.Schedule.route) }) {
                Text(text = "Exit", modifier = Modifier.padding(all = 20.dp))
            }
        }

        schedule?.items?.scheduleItems?.forEach{ item ->
            Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(vertical = 20.dp)) {
                Text(text = item, modifier = Modifier.padding(all = 20.dp))
            }

        }
    }
}
