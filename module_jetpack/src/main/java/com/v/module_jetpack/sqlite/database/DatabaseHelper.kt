package com.v.module_jetpack.sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.v.module_jetpack.sqlite.config.DBConfig
import java.util.concurrent.atomic.AtomicInteger

/**
 * ClassName: DatabaseHelper
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.sqlite
 * @date 2023/8/8 22:35
 */
class DatabaseHelper(context: Context, name: String, cursorFactory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, cursorFactory, version) {

    private constructor(
        context: Context,
        name: String = DBConfig.dbName,
        version: Int = DBConfig.dbVersion
    ) : this(
        context,
        name,
        null,
        version
    )

    companion object {
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                synchronized(DatabaseHelper::class.java) {
                    if (instance == null) {
                        instance = DatabaseHelper(context.applicationContext)
                    }
                }
            }
            return instance!!
        }
    }

    private val concurrentNumber: AtomicInteger = AtomicInteger()

    private var db: SQLiteDatabase? = null

    fun getDB(): SQLiteDatabase {
        val incrementAndGet = concurrentNumber.incrementAndGet()
        Log.i("zm1234", "getDB incrementAndGet:${incrementAndGet}")
        if (incrementAndGet == 1) {
            db = writableDatabase
        }
        return db!!
    }

    @Synchronized
    fun closeDB() {
        val decrementAndGet = concurrentNumber.decrementAndGet()
        Log.i("zm1234", "closeDB incrementAndGet:${concurrentNumber.incrementAndGet()}")
        if (decrementAndGet == 0) {
            db?.close()
        }
    }

    override fun onCreate(database: SQLiteDatabase) {
        Log.i("zm1234", "onCreate 创建数据库")
        database.execSQL("create table " + DBConfig.teaTableName + "(id  integer primary key autoincrement not null,date text,timestamp integer,name text, sex text)")
        database.execSQL("create table " + DBConfig.stuTableName + "(id  integer primary key autoincrement not null,date text,timestamp integer,name text)")
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("zm1234", "onUpgrade 数据库升级 oldVersion:$oldVersion newVersion:$newVersion")
    }
}