package com.example.finalproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController,
            startDestination = Screens.Schedule.route){
        composable(
            route = Screens.Schedule.route
        ){
            val list = ArrayList<Schedule>()
            list.add(Schedule("Hi", "ExampleItems"))
            list.add(Schedule("Middle", "ExampleItems"))
            list.add(Schedule("Bye", "ExampleItems"))
            list.add(Schedule("After End", "ExampleItems"))
            list.add(Schedule("Later", "ExampleItems"))
            ScheduleScreen(list, navController = navController)
        }
        composable(
            route = Screens.Edit.route
        ){
            val list = ArrayList<Schedule>()
            list.add(Schedule("Hi", "ExampleItems"))
            list.add(Schedule("Middle", "ExampleItems"))
            list.add(Schedule("Bye", "ExampleItems"))
            list.add(Schedule("After End", "ExampleItems"))
            list.add(Schedule("Later", "ExampleItems"))
            EditScreen(list, navController = navController)
        }
        composable(
            route = Screens.Checklist.route
        ){

            ChecklistScreen(schedule = Schedule("Test", "ExampleItems"), navController = navController)

        }
    }
}