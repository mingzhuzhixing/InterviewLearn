package com.v.kotlin.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ClassName: Student
 * Description:
 * @package_name com.v.kotlin.bean
 * @author zhuming
 * @date 2021/9/28 4:38 下午
 */
@Entity
class Student {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    @ColumnInfo(name = "stuName")
    val stuName: String = ""

    @ColumnInfo(name = "age")
    val age: Int = 0
}