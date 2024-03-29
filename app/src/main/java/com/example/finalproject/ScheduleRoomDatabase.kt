package com.example.finalproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [(Schedule::class)], version = 8, exportSchema = true)
abstract class ScheduleRoomDatabase: RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {

        private var INSTANCE: ScheduleRoomDatabase? = null

        fun getInstance(context: Context): ScheduleRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScheduleRoomDatabase::class.java,
                        "schedule_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}