package com.example.finalproject

import androidx.compose.runtime.snapshots.SnapshotStateList

data class ScheduleItems(
     var scheduleItems_: SnapshotStateList<String>,
     var scheduleTimers_: SnapshotStateList<String>,

){
    val scheduleItems: SnapshotStateList<String> = scheduleItems_
    val scheduleTimers: SnapshotStateList<String> = scheduleTimers_


}