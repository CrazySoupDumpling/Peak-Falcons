package com.example.finalproject

sealed class Screens(val route: String) {
    object Schedule: Screens(route = "ScheduleScreen")
    object Edit: Screens(route = "EditScreen")
    object Checklist: Screens(route = "ChecklistScreen")
    object Create: Screens(route = "CreateScheduleScreen")
    object EditSchedule: Screens(route = "EditScheduleScreen")

}