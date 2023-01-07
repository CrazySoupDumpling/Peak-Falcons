package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Insert
    fun insertSchedule(schedule: Schedule)

    @Query("SELECT * FROM schedules WHERE scheduleName = :name")
    fun findSchedule(name: String): List<Schedule>

    @Query("SELECT * FROM schedules WHERE id = :id")
    fun findSchedule(id: Int): List<Schedule>

    @Query("DELETE FROM schedules WHERE scheduleName = :name")
    fun deleteSchedule(name: String)

    @Query("SELECT * FROM schedules")
    fun getAllSchedules(): LiveData<List<Schedule>>
}