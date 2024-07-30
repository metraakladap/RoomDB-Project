package com.example.roomdbproject

import java.util.concurrent.Executors

class TaskRepository(private val database: TaskDatabase) {

    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = database.dataDao().getAll()

    fun addData(data: Data) {
        executor.execute {
            database.dataDao().insertAll(data)
        }
    }

    fun deleteData(data: Data) {
        executor.execute {
            database.dataDao().delete(data)
        }
    }
}