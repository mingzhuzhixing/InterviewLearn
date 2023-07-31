package com.v.module_jetpack.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.v.module_jetpack.room.table.Address

/**
 * ClassName: AddressDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.room.dao
 * @date 2023/7/31 22:43
 */
@Dao
interface AddressDao {

    /**
     * 插入地址信息
     */
    @Insert
    fun insetAddress(vararg address: Address)
}