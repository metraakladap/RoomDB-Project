package com.example.roomdbproject

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version =1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}