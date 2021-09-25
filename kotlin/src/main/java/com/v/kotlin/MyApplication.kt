package com.v.kotlin

import android.app.Application
import android.content.Context

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
    }
}