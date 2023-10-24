package com.example.kt_less25

import android.app.Application
import com.google.firebase.database.FirebaseDatabase


class MyApplication: Application() {
    lateinit var repo: ToDoRepository

    override fun onCreate() {
        super.onCreate()
        instance = this
        val db = FirebaseDatabase.getInstance()
        repo = ToDoRepository(db)

    }

    companion object {
        private lateinit var instance: MyApplication
        fun getApp() = instance
    }

}