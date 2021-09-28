package com.v.kotlin.ivew

import com.v.kotlin.bean.Student

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