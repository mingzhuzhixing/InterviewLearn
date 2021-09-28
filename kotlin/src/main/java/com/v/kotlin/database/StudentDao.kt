package com.v.kotlin.database

import androidx.room.*
import com.v.kotlin.bean.Student

/**
 * ClassName: StudentDao
 * Description:
 * @package_name com.v.kotlin.database
 * @author zhuming
 * @date 2021/9/28 4:44 下午
 */
@Dao
interface StudentDao {
    /**
     * 增加
     */
    @Insert
    fun insertStudent(vararg student: Student)

    /**
     * 删除指定用户
     */
    @Delete
    fun deleteStudent(vararg student: Student)

    /**
     * 删除所有用户
     */
    @Query("DELETE FROM student")
    fun deleteAllStudent()

    /**
     * 修改
     */
    @Update
    fun updateStudent(vararg student: Student)

    /**
     * 查询所有
     */
    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>

    /**
     * 根据用户名查询
     */
    @Query("SELECT * FROM student WHERE stuName IN (:stuName)")
    fun getFindName(stuName: String): Student
}