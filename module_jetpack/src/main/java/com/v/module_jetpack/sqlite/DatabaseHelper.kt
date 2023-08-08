package com.v.module_jetpack.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

/**
 * https://www.zhihu.com/column/c_1562486573995831296
 */
class DatabaseHelper(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name,
        factory, version) {

    constructor(context: Context,name: String = dbName, version: Int=1):this(context, name,
            null, version){
    }

    companion object {
        const val dbName = "school.db";
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                synchronized(DatabaseHelper::class) {
                    if (instance == null) {
                        instance = DatabaseHelper(context.applicationContext)
                    }
                }
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}