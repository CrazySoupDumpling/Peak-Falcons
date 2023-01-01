package com.example.finalproject

sealed class Screens(val route: String) {
    object Schedule: Screens(route = "ScheduleScreen")
    object Edit: Screens(route = "EditScreen")
    object Checklist: Screens(route = "ChecklistScreen")

}