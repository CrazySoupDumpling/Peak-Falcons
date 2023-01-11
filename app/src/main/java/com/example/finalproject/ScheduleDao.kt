package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ScheduleDao {

    @Insert
    fun insertSchedule(schedule: Schedule)

    @Update
    fun updateSchedule(schedule: Schedule)

    @Query("SELECT * FROM schedules WHERE scheduleName = :name")
    fun findSchedule(name: String): List<Schedule>

    @Query("SELECT * FROM schedules WHERE id = :id")
    fun findSchedule(id: Int): List<Schedule>

    @Query("DELETE FROM schedules WHERE id = :id")
    fun deleteSchedule(id: Int)

    @Query("SELECT * FROM schedules")
    fun getAllSchedules(): LiveData<List<Schedule>>

//    @Update(entity = Schedule::class)
//    fun updateCategory(varargs category: Category)

//    @Query("UPDATE schedules SET items=:schedule.items WHERE order_id = :id")
//    fun update(newItems: Sav, id: Int)
}

