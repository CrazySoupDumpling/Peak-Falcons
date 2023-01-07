package com.example.finalproject

data class ScheduleItems(
     var scheduleItems_: List<String>
){
    val scheduleItems: List<String>
        get() = scheduleItems_

}