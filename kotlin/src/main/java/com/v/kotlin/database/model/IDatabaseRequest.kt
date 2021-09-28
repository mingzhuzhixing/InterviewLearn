package com.v.kotlin.database.model

import com.v.kotlin.bean.Student

/**
 * ClassName: IDatabaseRequest
 * Description:
 * @package_name com.v.kotlin.database.model
 * @author zhuming
 * @date 2021/9/28 6:26 下午
 */
interface IDatabaseRequest {

    fun insertStudents(vararg student: Student)

    fun updateStudents(vararg student: Student)

    fun deleteStudents(vararg student: Student)

    fun deleteAllStudents()

    fun queryAllStudents():List<Student>?
}