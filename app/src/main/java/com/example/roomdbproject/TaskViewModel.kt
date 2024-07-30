package com.example.roomdbproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val repository = TaskApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList)
    val listState: LiveData<ListState> = _listState

    private val observer = Observer<List<Data>> {
        if (it.isEmpty()) {
            _listState.value = ListState.EmptyList
        } else {
            _listState.value = ListState.UpdatedList(it)
        }
    }

    init {
        repository.getAll().observeForever(observer)
    }

    fun addData(data: Data) {
        repository.addData(data = data)
    }

    fun deleteData(data: Data) {
        repository.deleteData(data = data)
    }

    override fun onCleared() {
        repository.getAll().removeObserver(observer)
        super.onCleared()
    }

    sealed class ListState {
        object EmptyList : ListState()
        data class UpdatedList(val list: List<Data>) : ListState()
    }
}