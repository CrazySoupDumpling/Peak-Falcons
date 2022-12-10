package com.example.finalproject

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainPage") {
                        composable("MainPage") {
                            val list = ArrayList<Schedule>()
                            list.add(Schedule("Hi"))
                            list.add(Schedule("Middle"))
                            list.add(Schedule("Bye"))
                            MainPage(list, navController)
                        }
                        composable("TestPage") {
                            TestComposable(navController)
                        }
                    }
                    val list = ArrayList<Schedule>()
                    list.add(Schedule("Hi"))
                    list.add(Schedule("Middle"))
                    list.add(Schedule("Bye"))
                    MainPage(list, navController)
                }
            }
        }
    }
}

@Composable
fun MainPage(schedules: List<Schedule>, navController: NavHostController) {

    Column(Modifier.fillMaxHeight()) {
        Row(Modifier.fillMaxWidth()) {
            Text(text = "Your Schedules", modifier = Modifier.padding(all = 20.dp).padding(top=15.dp))
            Spacer(modifier = Modifier.width(140.dp))
            Button(onClick = {navController.navigate("TestPage")}) {
                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
            }
        }
        schedules.forEach{ scheduleName ->
            Button(onClick = {/*placeholder*/ },modifier = Modifier.fillMaxWidth().padding(all=10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
                Text(text = scheduleName.firstProperty, Modifier.padding(all = 20.dp))
            }
        }
    }
}


@Composable
fun TestComposable(navController: NavHostController) {
    Column(Modifier.fillMaxHeight().background(Color.White)) {
        Row(Modifier.fillMaxWidth()) {
            Text(text = "Test", modifier = Modifier.padding(all = 40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinalProjectTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "MainPage") {
            composable("MainPage") {
                val list = ArrayList<Schedule>()
                list.add(Schedule("Hi"))
                list.add(Schedule("Middle"))
                list.add(Schedule("Bye"))
                MainPage(list, navController)
            }
            composable("TestPage") {
                TestComposable(navController)
            }
        }
        val list = ArrayList<Schedule>()
        list.add(Schedule("Hi"))
        list.add(Schedule("Middle"))
        list.add(Schedule("Bye"))
        MainPage(list, navController)
    }
}