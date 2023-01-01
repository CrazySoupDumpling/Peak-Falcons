package com.example.finalproject

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.theme.FinalProjectTheme

@Composable
fun ScheduleScreen(
    schedules: List<Schedule>,
    navController: NavController
) {
    Column(Modifier.fillMaxHeight()) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Your Schedules", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp))
            Spacer(modifier = Modifier.width(140.dp))
            Button(onClick = {navController.navigate(route = Screens.Edit.route)}) {
                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
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
                    text = schedules[i].firstProperty,
                    Modifier
                        .padding(all = 20.dp)
                        .padding(top = 20.dp)
                )
            }
        }
//        schedules.forEach{ scheduleName ->
//            Button(onClick = {/*placeholder*/ },modifier = Modifier.fillMaxWidth().padding(all=10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
//                Text(text = scheduleName.firstProperty, Modifier.padding(all = 20.dp).padding(top = 20.dp))
//            }
//        }
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

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FinalProjectTheme {
//        val list = ArrayList<Schedule>()
//        list.add(Schedule("Hi"))
//        list.add(Schedule("Middle"))
//        list.add(Schedule("Bye"))
//        list.add(Schedule("After End"))
//        list.add(Schedule("Later"))
//        var navController = rememberNavController()
//        SetupNavGraph(navController = navController)
//        ScheduleScreen(list, navController = navController)
//    }
//}