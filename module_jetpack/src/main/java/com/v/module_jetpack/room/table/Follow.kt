package com.v.module_jetpack.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * ClassName: Follow
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.room.table
 * @date 2023/8/6 21:11
 */
@Entity(tableName = "follow")
class Follow {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "user_id")
    var userId: Int = 0

    @ColumnInfo(name = "status")
    var status: Int = 0

    @ColumnInfo(name = "type")
    var type: String = ""

    constructor(){}

    @Ignore
    constructor(userId: Int, status: Int) {
        this.userId = userId
        this.status = status
    }

    @Ignore
    constructor(userId: Int, status: Int, type: String) {
        this.userId = userId
        this.status = status
        this.type = type
    }
}