package com.example.finalproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel(application: Application) : ViewModel() {

    val allSchedules: LiveData<List<Schedule>>
    private val repository: ScheduleRepository
    val searchResults: MutableLiveData<List<Schedule>>

    init {
        val scheduleDb = ScheduleRoomDatabase.getInstance(application)
        val scheduleDao = scheduleDb.scheduleDao()
        repository = ScheduleRepository(scheduleDao)

        allSchedules = repository.allSchedules
        searchResults = repository.searchResults
    }
    fun insertSchedule(schedule: Schedule) {
        repository.insertSchedule(schedule)
    }

    fun findSchedule(name: String) {
        repository.findSchedule(name)
    }
    fun findSchedule(id: Int) {
        repository.findSchedule(id)
    }

    fun deleteSchedule(name: String) {
        repository.deleteSchedule(name)
    }
}