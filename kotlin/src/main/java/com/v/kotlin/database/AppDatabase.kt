package com.v.kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.v.kotlin.bean.Student

/**
 * ClassName: AppDatabase
 * Description:
 * @package_name com.v.kotlin.database
 * @author zhuming
 * @date 2021/9/28 4:51 下午
 */
@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {
        var INSTANCE: AppDatabase? = null

        /**
         * 初始化数据库
         */
        fun initApplication(context: Context) {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "student_database.db"
                    ).build()
            }
        }

        fun getInstance(): AppDatabase? = INSTANCE
    }
}