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
            list.add(Schedule("Hi"))
            list.add(Schedule("Middle"))
            list.add(Schedule("Bye"))
            list.add(Schedule("After End"))
            list.add(Schedule("Later"))
            ScheduleScreen(list, navController = navController)
        }
        composable(
            route = Screens.Edit.route
        ){
            val list = ArrayList<Schedule>()
            list.add(Schedule("Hi"))
            list.add(Schedule("Middle"))
            list.add(Schedule("Bye"))
            list.add(Schedule("After End"))
            list.add(Schedule("Later"))
            EditScreen(list, navController = navController)
        }
        composable(
            route = Screens.Checklist.route
        ){

            ChecklistScreen(schedule = Schedule("Test"), navController = navController)

        }
    }
}