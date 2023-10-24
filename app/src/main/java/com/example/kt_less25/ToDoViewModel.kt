package com.example.kt_less25

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class ToDoViewModel:ViewModel() {
    private val repo = MyApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList)
    val listState:LiveData<ListState> = _listState
    private val observer = Observer<List<ToDo>> {
        _listState.postValue(ListState.UpdatedList(list = it))
    }

    init {
        repo.getAll().observeForever(observer)
    }

    fun add(task:String) {
        repo.add(ToDo(task = task))
    }

    fun remove(task: ToDo) {
        repo.remove(task)
    }

    override fun onCleared() {
        super.onCleared()
        repo.getAll().removeObserver(observer)
    }


    sealed class ListState {
        object EmptyList:ListState()
        class UpdatedList(val list: List<ToDo>):ListState()
    }
}