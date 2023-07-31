package com.v.module_jetpack.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * ClassName: Address
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.room.table
 * @date 2023/7/31 22:39
 */
@Entity(tableName = "address")
class Address {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "province")
    var province: String? = null

    @ColumnInfo(name = "city")
    var city: String? = null

    @ColumnInfo(name = "district")
    var district: String? = null
}