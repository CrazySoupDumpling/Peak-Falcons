package com.example.finalproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController,
            startDestination = Screens.Schedule.route){
        composable(
            route = Screens.Schedule.route
        ){
            val allSchedules by viewModel.allSchedules.observeAsState(listOf())
            ScheduleScreen(schedules = allSchedules, navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screens.Edit.route
        ){
            val allSchedules by viewModel.allSchedules.observeAsState(listOf())
            EditScreen(allSchedules, navController = navController)
        }
        composable(
            route = Screens.Checklist.route
        ){

            ChecklistScreen(schedule = Schedule("Test", ScheduleItems(listOf("ExampleItems"))), navController = navController)

        }
    }
}