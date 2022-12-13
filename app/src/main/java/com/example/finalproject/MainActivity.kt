package com.example.finalproject

import android.os.Bundle
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
import com.example.finalproject.ui.theme.FinalProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val list = ArrayList<Schedule>()
                    list.add(Schedule("Hi"))
                    list.add(Schedule("Middle"))
                    list.add(Schedule("Bye"))
                    Greeting(list)
                }
            }
        }
    }
}

@Composable
fun Greeting(schedules: List<Schedule>) {
    Column(Modifier.fillMaxHeight()) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Your Schedules", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp))
            Spacer(modifier = Modifier.width(140.dp))
            Button(onClick = {/*placeholder*/ }) {
                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
            }
        }

        var schedNumStart by remember{
            mutableStateOf(0)
        }
        var schedNumEnd by remember{
            mutableStateOf(1)
        }
        for(i in schedNumStart..schedNumEnd){
            Button(onClick = {/*placeholder*/ },modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
                Text(text = schedules[i].firstProperty,
                    Modifier
                        .padding(all = 20.dp)
                        .padding(top = 20.dp))
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
        val totalPageNum by remember{
            mutableStateOf(7)
        }

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Page $pageNum out of $totalPageNum", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp))

        }
        Row(Modifier.fillMaxWidth()) {
            Button(onClick = {if(pageNum>1) {
                pageNum--
                schedNumEnd--
                if(schedNumStart>0)schedNumStart--
            }}) {
                Text(text = "Previous ", modifier = Modifier.padding(all = 20.dp))
            }
            Button(onClick = {if(pageNum<totalPageNum) {
                pageNum++
                schedNumStart++
                if(schedNumEnd<totalPageNum)schedNumEnd++
            }}) {
                Text(text = "Next ", modifier = Modifier.padding(all = 20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinalProjectTheme {
        val list = ArrayList<Schedule>()
        list.add(Schedule("Hi"))
        list.add(Schedule("Middle"))
        list.add(Schedule("Bye"))
        Greeting(list)
    }
}