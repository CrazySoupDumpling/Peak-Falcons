package com.example.finalproject

import android.content.Context
import android.os.Bundle
import android.util.Log
//import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import com.example.finalproject.ui.theme.FinalProjectTheme
import kotlinx.coroutines.launch

@Composable
fun EditScreen(
    schedules: List<Schedule>,
    navController: NavController
) {
    var context = LocalContext.current
    var scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Edit Your Schedules", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp))
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {navController.navigate(route = Screens.Schedule.route)}, modifier = Modifier.width(150.dp)) {
                Text(text = "Done", modifier = Modifier.padding(all = 20.dp))
            }
        }
        for(schedule in schedules){
            Button(onClick = {navController.navigate(Screens.EditSchedule.route) },modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
                Text(
                    text = schedule.name,
                    Modifier
                        .padding(all = 20.dp)
                        .padding(top = 20.dp)
                )
            }
        }



        Button(onClick = {

                         navController.navigate(route = Screens.Create.route)

        },modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {

                Text(
                    text = "Create Schedule",
                    Modifier
                        .padding(all = 20.dp)
                        .padding(top = 20.dp)
                )
            }
    }
}
