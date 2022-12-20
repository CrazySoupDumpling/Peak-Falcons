package com.example.finalproject

import android.os.Bundle
import android.util.Log
//import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.theme.FinalProjectTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Log.e("myTag", "This is my message");
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)

//                    val list = ArrayList<Schedule>()
//                    list.add(Schedule("Hi"))
//                    list.add(Schedule("Middle"))
//                    list.add(Schedule("Bye"))
//                    list.add(Schedule("After End"))
//                    list.add(Schedule("Later"))
//                    ScheduleScreen(list, navController)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(schedules: List<Schedule>) {
//    Column(Modifier.fillMaxHeight()) {
//
//        Row(Modifier.fillMaxWidth()) {
//            Text(text = "Your Schedules", modifier = Modifier
//                .padding(all = 20.dp)
//                .padding(top = 15.dp))
//            Spacer(modifier = Modifier.width(140.dp))
//            Button(onClick = {/*placeholder*/ }) {
//                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
//            }
//        }
//        val EntriesPerPage = 2
//        var schedNumStart by remember{
//            mutableStateOf(0)
//        }
//        var schedNumEnd by remember{
//            mutableStateOf(1)
//        }
//        for(i in schedNumStart..minOf(schedNumEnd, schedules.size-1)){
//            Button(onClick = {/*placeholder*/ },modifier = Modifier
//                .fillMaxWidth()
//                .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
//                    Text(
//                        text = schedules[i].firstProperty,
//                        Modifier
//                            .padding(all = 20.dp)
//                            .padding(top = 20.dp)
//                    )
//                }
//        }
////        schedules.forEach{ scheduleName ->
////            Button(onClick = {/*placeholder*/ },modifier = Modifier.fillMaxWidth().padding(all=10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
////                Text(text = scheduleName.firstProperty, Modifier.padding(all = 20.dp).padding(top = 20.dp))
////            }
////        }
//        var pageNum by remember{
//            mutableStateOf(1)
//        }
//        var pages by remember{
//            mutableStateOf((schedules.size / 2))
//        }
//        val totalPageNum by remember{
//
//
//            if (schedules.size % EntriesPerPage != 0 && schedules.size > EntriesPerPage) {
//                pages = (schedules.size / 2) + 1
//            }
//            mutableStateOf(pages)
//        }
//
//        Row(Modifier.fillMaxWidth()) {
//            Text(text = "Page $pageNum out of $totalPageNum", modifier = Modifier
//                .padding(all = 20.dp)
//                .padding(top = 15.dp))
//
//        }
//        Row(Modifier.fillMaxWidth()) {
//            Button(onClick = {if(pageNum>1) {
//                pageNum--
//                schedNumEnd -= EntriesPerPage
//                if(schedNumStart>0)schedNumStart -= EntriesPerPage
//            }}) {
//                Text(text = "Previous", modifier = Modifier.padding(all = 20.dp))
//            }
//            Button(onClick = {if(pageNum<totalPageNum) {
//                pageNum++
//                schedNumStart += EntriesPerPage
//                if(schedNumEnd<schedules.size)schedNumEnd += EntriesPerPage
//            }}) {
//                Text(text = "Next", modifier = Modifier.padding(all = 20.dp))
//            }
//        }
//    }
//}
//
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
//        Greeting(list)
//    }
//}