package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ScheduleRepository(private val scheduleDao: ScheduleDao) {

    val allSchedules: LiveData<List<Schedule>> = scheduleDao.getAllSchedules()
    val searchResults = MutableLiveData<List<Schedule>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertSchedule(newschedule: Schedule) {
        coroutineScope.launch(Dispatchers.IO) {
            scheduleDao.insertSchedule(newschedule)
        }
    }
    fun updateSchedule(updatedSchedule: Schedule) {
        coroutineScope.launch(Dispatchers.IO) {
            scheduleDao.updateSchedule(updatedSchedule)
        }
    }

    fun deleteSchedule(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            scheduleDao.deleteSchedule(id)
        }
    }


    fun findSchedule(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(id).await()
        }
    }

    private fun asyncFind(id: Int): Deferred<List<Schedule>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async scheduleDao.findSchedule(id)
        }
}