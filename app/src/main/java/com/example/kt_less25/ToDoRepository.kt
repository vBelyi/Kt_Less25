package com.example.kt_less25

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ToDoRepository(private val database: FirebaseDatabase) {

    private val tasksRef = database.getReference("tasks")

    fun getAll(): LiveData<List<ToDo>> {
        val liveData = MutableLiveData<List<ToDo>>()
        tasksRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val taskList = mutableListOf<ToDo>()
                for (childSnapshot in snapshot.children) {
                    val task = childSnapshot.getValue(ToDo::class.java)
                    task?.let { taskList.add(it) }
                }
                liveData.postValue(taskList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return liveData

    }

    fun add(task: ToDo) {
        val key = tasksRef.push().key
        key?.let {
            tasksRef.child(it).setValue(task)
        }
    }
    fun remove(task: ToDo) {
        tasksRef.child((task.id ?: "").toString()).removeValue()
    }
}