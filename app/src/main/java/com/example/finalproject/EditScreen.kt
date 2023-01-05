package com.example.finalproject

import android.content.Context
import android.os.Bundle
import android.util.Log
//import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import readSchedule
import writeSchedule

@Composable
fun EditScreen(
    schedules: List<Schedule>,
    navController: NavController
) {
    var context = LocalContext.current
    var scope = rememberCoroutineScope()

    Column(Modifier.fillMaxHeight()) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Edit Your Schedules", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp))
            Spacer(modifier = Modifier.width(70.dp))
            Button(onClick = {navController.navigate(route = Screens.Schedule.route)}) {
                Text(text = "Done ", modifier = Modifier.padding(all = 20.dp))
            }
        }
        val EntriesPerPage = 3
        var schedNumStart by remember{
            mutableStateOf(0)
        }
        var schedNumEnd by remember{
            mutableStateOf(EntriesPerPage-1)
        }
        for(i in schedNumStart..minOf(schedNumEnd, schedules.size-1)){
            Button(onClick = {/*placeholder*/ },modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
                Text(
                    text = schedules[i].name,
                    Modifier
                        .padding(all = 20.dp)
                        .padding(top = 20.dp)
                )
            }
        }



        Button(onClick = {
                            scope.launch{
                                context.writeSchedule("ScheduleC", Schedule("x", "y"))
                            }
//                         SaveNameUser(Schedule("x", "y"))

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


        var pageNum by remember{
            mutableStateOf(1)
        }
        var pages by remember{
            mutableStateOf((schedules.size / EntriesPerPage))
        }
        val totalPageNum by remember{


            if (schedules.size % EntriesPerPage != 0 && schedules.size > EntriesPerPage) {
                pages = (schedules.size / EntriesPerPage) + 1
            }
            mutableStateOf(pages)
        }

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Page $pageNum out of $totalPageNum", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp))

        }
        Row(Modifier.fillMaxWidth()) {
            Button(onClick = {if(pageNum>1) {
                pageNum--
                schedNumEnd -= EntriesPerPage
                if(schedNumStart>0)schedNumStart -= EntriesPerPage
            }}) {
                Text(text = "Previous", modifier = Modifier.padding(all = 20.dp))
            }
            Button(onClick = {
                Log.e("myTag", "This is my message")
                if(pageNum<totalPageNum) {
                    pageNum++
                    schedNumStart += EntriesPerPage
                    if(schedNumEnd<schedules.size)schedNumEnd += EntriesPerPage
                }}) {
                Text(text = "Next", modifier = Modifier.padding(all = 20.dp))
            }
        }
    }
}
