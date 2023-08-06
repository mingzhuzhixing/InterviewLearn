package com.v.module_jetpack.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * ClassName: User
 * Description:
 *
 * @PrimaryKey(autoGenerate = true)表示主键自增，参数autoGenerate表示主键可以由数据库自动生成
 * @ColumnInfo(name = "id"）表示列的名字为id
 * @Ignore表示会忽略这个字段，不进行记录
 *
 */
@Entity(tableName = "user")
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "user_id")
    var userId: Int = 0

    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @ColumnInfo(name = "user_pwd")
    var userPwd: String? = null

    @ColumnInfo(name = "sex")
    var sex: String? = null

    @ColumnInfo(name = "age")
    var age: Int = 0

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "school")
    var school: String? = null

    @ColumnInfo(name = "phone")
    var phone: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null

    @Ignore
    constructor(){}

    constructor(userId: Int, userName: String?, age: Int, sex: String?) {
        this.userId = userId
        this.userName = userName
        this.sex = sex
        this.age = age
    }
}