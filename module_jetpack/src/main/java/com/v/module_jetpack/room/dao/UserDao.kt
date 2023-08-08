package com.v.module_jetpack.room.dao

import androidx.room.*
import com.v.module_jetpack.room.table.User

/**
 * ClassName: UserDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.room.database
 * @date 2023/7/31 22:24
 */
@Dao
interface UserDao {
    /**
     * 增加
     */
    @Insert
    fun insertUser(vararg user: User)

    /**
     * 删除指定用户
     */
    @Delete
    fun deleteUser(vararg user: User)

    /**
     * 删除所有用户
     */
    @Query("DELETE FROM user")
    fun deleteAllUser()

    /**
     * 修改
     */
    @Update
    fun updateUser(vararg user: User)

    /**
     * 修改
     */
    @Query("UPDATE user SET user_name= :userName WHERE user_id = :userId")
    fun updateUserByUserId(userId: Int, userName: String)

    /**
     * 根据用户id获取count
     */
    @Query("SELECT COUNT(*) FROM user WHERE user_id = :userId")
    fun getCountByUserId(userId: Int):Int

    /**
     * 查询所有
     */
    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    /**
     * 根据用户名查询
     */
    @Query("SELECT * FROM user WHERE user_name IN (:userName)")
    fun getFindName(userName: String): User
}