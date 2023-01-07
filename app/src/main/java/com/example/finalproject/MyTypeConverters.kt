package com.example.finalproject

import androidx.room.TypeConverter
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun fromScheduleToJSON(scheduleItems: ScheduleItems): String {
        return Gson().toJson(scheduleItems)
    }
    @TypeConverter
    fun fromJSONToSchedule(json: String): ScheduleItems {
        return Gson().fromJson(json,ScheduleItems::class.java)
    }
}