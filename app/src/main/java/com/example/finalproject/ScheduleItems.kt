package com.example.finalproject

import androidx.compose.runtime.snapshots.SnapshotStateList

data class ScheduleItems(
     var scheduleItems_: SnapshotStateList<String>,
     var scheduleAlarms_: SnapshotStateList<String>
){
    val scheduleItems: SnapshotStateList<String> = scheduleItems_
    val scheduleAlarms: SnapshotStateList<String> = scheduleAlarms_


}