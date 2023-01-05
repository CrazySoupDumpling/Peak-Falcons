//package com.example.finalproject
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import com.example.finalproject.Schedule
//import kotlinx.coroutines.flow.map
//
//const val SCHEDULE_DATASTORE ="schedule_datastore"
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SCHEDULE_DATASTORE)
//
//class DataStoreManager (val context: Context) {
//
//    companion object {
//        val NAME = stringPreferencesKey("NAME")
//        val ITEMS = stringPreferencesKey("ITEMS")
//    }
//
//    suspend fun saveToDataStore(context: Context, schedule: Schedule) {
//        context.dataStore.edit {
//            it[NAME] = schedule.name
//            it[ITEMS] = schedule.items
//
//        }
//    }
//
//    fun getFromDataStore() = context.dataStore.data.map {
//        Schedule(
//            name = it[NAME]?:"",
//            items = it[ITEMS]?:"",
//        )
//    }
//
//    suspend fun clearDataStore() = context.dataStore.edit {
//        it.clear()
//    }
//
//}