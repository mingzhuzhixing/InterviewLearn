package com.v.module_jetpack.sqlite.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.v.module_jetpack.sqlite.config.DBConfig
import com.v.module_jetpack.sqlite.database.DatabaseHelper
import com.v.module_jetpack.sqlite.bean.StudentBean
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * ClassName: StudentDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.sqlite.dao
 * @date 2023/8/8 22:45
 */
class StudentDao private constructor(context: Context) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault())
    private val schoolDbHelper: DatabaseHelper = DatabaseHelper.getInstance(context)

    companion object {
        private var studentDao: StudentDao? = null

        fun getInstance(context: Context): StudentDao? {
            if (studentDao == null) {
                synchronized(StudentDao::class.java) {
                    if (studentDao == null) {
                        studentDao = StudentDao(context.applicationContext)
                    }
                }
            }
            return studentDao
        }
    }

    @Synchronized
    fun insert(studentBean: StudentBean) {
        val timeStamp: Long = studentBean.timestamp
        val date = dateFormat.format(Date(timeStamp))

        val values = ContentValues()
        values.put("date", date)
        values.put("timestamp", timeStamp)
        values.put("name", studentBean.name)

        schoolDbHelper.getDB().insert(DBConfig.stuTableName, null, values)
        schoolDbHelper.closeDB()
    }

    val count: Int
        get() {
            var count = 0
            var cursor: Cursor? = null
            try {
                val sql = "select count(*) from ${DBConfig.stuTableName}"
                cursor = schoolDbHelper.getDB().rawQuery(sql, null)
                if (cursor.moveToFirst()) {
                    count = cursor.getInt(0)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
                schoolDbHelper.closeDB()
            }
            return count
        }

    @SuppressLint("Range")
    fun getAllStudent(count: Int): List<StudentBean> {
        val results: ArrayList<StudentBean> = ArrayList()
        var cursor: Cursor? = null

        try {
            val database: SQLiteDatabase = schoolDbHelper.getDB()
            val limits = arrayOfNulls<String>(1)
            limits[0] = count.toString()
            val sql = "select * from ${DBConfig.stuTableName} order by id asc limit ? "
            cursor = database.rawQuery(sql, limits)
            while (cursor.moveToNext()) {
                val bean = StudentBean.Builder()
                    .setName(cursor.getString(cursor.getColumnIndex("name")))
                    .setDate(cursor.getString(cursor.getColumnIndex("date")))
                    .setTimeStamp(cursor.getLong(cursor.getColumnIndex("timestamp")))
                    .build()
                results.add(bean)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            schoolDbHelper.closeDB()
        }
        return results
    }

    @Synchronized
    fun delete(count: Int) {
        try {
            val sql = StringBuilder()
                .append("delete from ${DBConfig.stuTableName} where id in (select id from ${DBConfig.stuTableName} order by id asc limit ")
                .append(count)
                .append(")")
                .toString()
            schoolDbHelper.getDB().execSQL(sql)
            schoolDbHelper.closeDB()
            return
        } catch (e: Exception) {
            schoolDbHelper.closeDB()
        }
    }
}