package com.v.kotlin_app.ivew

import com.v.kotlin_app.bean.Student

interface ICollectView {
    /**
     * 显示所有学生信息
     */
    fun showAllStudent(data: List<Student>)

    /**
     * 删除所有学生信息
     */
    fun deleteAllStudent()
}