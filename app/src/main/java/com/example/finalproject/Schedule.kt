package com.example.finalproject

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
class Schedule constructor(name: String, items: ScheduleItems){

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "scheduleName")
    var name: String = name

    @ColumnInfo(name = "items")
    var items: ScheduleItems = items
}


