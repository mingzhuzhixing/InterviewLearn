package com.v.kotlin_app

import android.app.Application
import android.content.Context
import com.v.kotlin_app.database.AppDatabase

class MyApplication : Application() {
    companion object{
        private lateinit var mApplication: Application
        fun getContext(): Context {
            return mApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        AppDatabase.initApplication(this)
    }
}