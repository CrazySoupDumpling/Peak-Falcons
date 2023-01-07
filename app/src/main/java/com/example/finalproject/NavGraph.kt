package com.example.finalproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
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
            route = "${Screens.Checklist.route}/{scheduleId}",
            arguments = listOf(navArgument("scheduleId") { defaultValue = "user1234" })
        ){
//            val index = backStackEntry.arguments?.getInt("index") ?: 0
//            val planet = Planet.data[index]
                backStackEntry ->
            val x: String? = backStackEntry.arguments?.getString("scheduleId")



            if (x != null) {
                ChecklistScreen(
                    scheduleID = x.toInt(),
                    navController = navController,
                    viewModel =  viewModel
                )
            }else{
                ChecklistScreen(
                    scheduleID = 7,
                    navController = navController,
                    viewModel = viewModel
                )
            }

        }
    }
}