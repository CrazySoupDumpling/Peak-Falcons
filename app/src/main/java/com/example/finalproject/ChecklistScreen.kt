package com.example.finalproject

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChecklistScreen(
    schedule: Schedule,
    navController: NavController
) {
    Column(Modifier.fillMaxHeight()) {

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = schedule.name, modifier = Modifier
                    .padding(all = 20.dp)
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(140.dp))
            Button(onClick = { navController.navigate(route = Screens.Schedule.route) }) {
                Text(text = "Exit ", modifier = Modifier.padding(all = 20.dp))
            }
        }
    }
}
