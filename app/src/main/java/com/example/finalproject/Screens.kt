package com.example.finalproject

sealed class Screens(val route: String) {
    object Schedule: Screens(route = "ScheduleScreen")
    object Edit: Screens(route = "EditScreen")
    object Checklist: Screens(route = "ChecklistScreen")
    object ItembyItem: Screens(route = "ItemByItemSched")
    object ThisThen: Screens(route = "TwoItemSched")
    object Create: Screens(route = "CreateScheduleScreen")
    object EditSchedule: Screens(route = "EditScheduleScreen")

}