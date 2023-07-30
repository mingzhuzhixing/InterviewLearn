package com.v.kotlin_app.bean

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
class Student() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "stuName")
    var stuName: String = ""

    @ColumnInfo(name = "age")
    var age: Int = 0

    constructor(name: String):this() {
        this.stuName = name
    }

    constructor(name: String, age: Int):this() {
        this.stuName = name
        this.age = age
    }
}