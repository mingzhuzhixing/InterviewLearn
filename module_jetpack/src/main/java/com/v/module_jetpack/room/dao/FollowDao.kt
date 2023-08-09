package com.v.module_jetpack.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.v.module_jetpack.room.table.Follow

/**
 * ClassName: FollowDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.room.dao
 * @date 2023/8/6 21:14
 */
@Dao
interface FollowDao {
    /**
     * 增加
     */
    @Insert
    fun insertFollow(vararg follow: Follow)

    /**
     * 删除指定Follow
     */
    @Delete
    fun deleteFollow(vararg follow: Follow)

    /**
     * 删除所有Follow
     */
    @Query("DELETE FROM follow")
    fun deleteAllFollow()

    /**
     * 查询所有
     */
    @Query("SELECT * FROM follow")
    fun getAllFollow(): List<Follow>
}