package com.example.roomdbproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Data(@PrimaryKey(autoGenerate = true) val id: Int?=null, val taskName: String?)

@Dao
interface DataDao {

    @androidx.room.Insert
    fun insertAll(data: Data)

    @Query("SELECT * FROM data")
    fun getAll():LiveData<List<Data>>

    @androidx.room.Delete
    fun delete(data: Data)
}