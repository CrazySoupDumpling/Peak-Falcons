package com.example.finalproject

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import readSchedule
import writeSchedule

class viewModel(val appContext: Application) : AndroidViewModel(appContext) {

    companion object{
        const val NAME_USER_KEY = "name"
    }

    fun saveNameUser(schedule: Schedule) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeSchedule(NAME_USER_KEY, schedule)
        }
    }

    //Getting the name of saved user
    val getUserName = appContext.readSchedule(NAME_USER_KEY)
}