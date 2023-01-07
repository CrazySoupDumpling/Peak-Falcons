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

    fun deleteSchedule(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            scheduleDao.deleteSchedule(name)
        }
    }

    fun findSchedule(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Schedule>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async scheduleDao.findSchedule(name)
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