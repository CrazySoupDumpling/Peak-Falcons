package com.example.finalproject

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
class Schedule constructor(name: String, items: ScheduleItems, startTime: String ){

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "scheduleName")
    @NonNull
    var name: String = name

    @ColumnInfo(name = "items")
    @NonNull
    var items: ScheduleItems = items

    @ColumnInfo(name = "alarmTime")
    @NonNull
    var startTime: String = startTime
}


