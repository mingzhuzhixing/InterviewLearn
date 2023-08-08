package com.v.module_jetpack.sqlite.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.v.module_jetpack.sqlite.bean.StudentBean
import com.v.module_jetpack.sqlite.config.DBConfig
import com.v.module_jetpack.sqlite.database.DBHelper
import java.text.SimpleDateFormat
import java.util.*


/**
 * ClassName: StudentDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.sqlite.dao
 * @date 2023/8/8 22:45
 *
 * https://zhuanlan.zhihu.com/p/29472803
 */
class StudentDao private constructor(context: Context) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault())
    private val schoolDbHelper: DBHelper = DBHelper.getInstance(context)

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
        try {
            val timeStamp: Long = studentBean.timestamp
            val date = dateFormat.format(Date(timeStamp))

            val values = ContentValues()
            values.put("date", date)
            values.put("timestamp", timeStamp)
            values.put("name", studentBean.name)

            schoolDbHelper.getDB().insert(DBConfig.stuTableName, null, values)
            schoolDbHelper.closeDB()
            Log.i("zm1234", "insert: 插入数据")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("zm1234", "insert: error:" + e.message)
        } finally {
            Log.i("zm1234", "insert: closeDB")
            //schoolDbHelper.closeDB()
        }
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

    /**
     * 插入新数据，如果已经存在就替换
     */
    fun insertOrReplace(helper: SQLiteOpenHelper, student: StudentBean?) {
        try {
            schoolDbHelper.getDB().insertWithOnConflict(
                DBConfig.stuTableName,
                null,
                toContentValues(student),
                SQLiteDatabase.CONFLICT_REPLACE
            )
        } finally {
            schoolDbHelper.closeDB()
        }
    }

    /**
     * 记录是否存在
     */
    /**
     * 所有的字段
     */
    private val AVAILABLE_PROJECTION = arrayOf(
        "sid",
        "name",
        "age"
    )

    /**
     * 记录是否存在
     */
    fun isExist(helper: SQLiteOpenHelper, studentId: String): Boolean {
        val cursor = schoolDbHelper.getDB().query(
            DBConfig.stuTableName,
            AVAILABLE_PROJECTION,
            "sid =? ",
            arrayOf(studentId),
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            Log.d("zm12234", "缓存存在")
            cursor.close()
            true
        } else {
            Log.d("zm1234", "缓存不存在")
            cursor.close()
            false
        }
    }

    /**
     * 将对象保证成ContentValues
     */
    private fun toContentValues(student: StudentBean?): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("sid", student?.id)
        contentValues.put("name", student?.name)
        contentValues.put("age", student?.age)
        return contentValues
    }


    /**
     * 清空学生表
     * ctrl+alt+t
     */
    fun clear() {
        try {
            schoolDbHelper.getDB().delete(DBConfig.stuTableName, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            schoolDbHelper.closeDB()
        }
    }
}