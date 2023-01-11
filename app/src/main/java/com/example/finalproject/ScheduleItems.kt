package com.example.finalproject

import androidx.compose.runtime.snapshots.SnapshotStateList

data class ScheduleItems(
     var scheduleItems_: SnapshotStateList<String>
){
    val scheduleItems: SnapshotStateList<String> = scheduleItems_


}