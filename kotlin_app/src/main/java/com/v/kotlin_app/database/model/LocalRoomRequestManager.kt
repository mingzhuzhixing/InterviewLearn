package com.v.kotlin_app.database.model

import com.v.kotlin_app.bean.Student
import com.v.kotlin_app.database.AppDatabase
import com.v.kotlin_app.database.StudentDao

/**
 * ClassName: LocalRoomRequestManager
 * Description:
 * @package_name com.v.kotlin_app.database.model
 * @author zhuming
 * @date 2021/9/28 6:28 下午
 */
class LocalRoomRequestManager : ILocalRequest, IDatabaseRequest {

    var studentDao: StudentDao? = null

    init {
        val appDatabase = AppDatabase.getInstance()
        studentDao = appDatabase?.getStudentDao()
    }

    companion object {
        private var INSTANCE: LocalRoomRequestManager? = null

        fun getInstance(): LocalRoomRequestManager {
            if (INSTANCE == null) {
                synchronized(LocalRoomRequestManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = LocalRoomRequestManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun insertStudents(vararg student: Student) {
        studentDao?.insertStudent(*student)
    }

    override fun updateStudents(vararg student: Student) {
        studentDao?.updateStudent(*student)
    }

    override fun deleteStudents(vararg student: Student) {
        studentDao?.deleteStudent(*student)
    }

    override fun deleteAllStudents() {
        studentDao?.deleteAllStudent()
    }

    override fun queryAllStudents(): List<Student>? {
        return studentDao?.getAllStudent()
    }
}