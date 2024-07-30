package com.example.roomdbproject

import android.app.Application
import androidx.room.Room

class TaskApplication : Application() {

    lateinit var repo: TaskRepository
    override fun onCreate() {
        super.onCreate()
        instance = this
        val db = Room
            .databaseBuilder(this, TaskDatabase::class.java, "task-db")
            .build()

        repo = TaskRepository(db)
    }

    companion object {
        lateinit var instance: TaskApplication
        fun getApp() = instance
    }
}