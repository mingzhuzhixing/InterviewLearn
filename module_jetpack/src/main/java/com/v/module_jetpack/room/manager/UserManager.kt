package com.v.module_jetpack.room.manager

import com.v.module_jetpack.room.dao.UserDao
import com.v.module_jetpack.room.database.AppDatabase
import com.v.module_jetpack.room.table.User

/**
 * ClassName: LocalRoomRequestManager
 * Description:
 * @package_name com.v.kotlin_app.database.model
 * @author zhuming
 * @date 2021/9/28 6:28 下午
 */
class UserManager {

    var userDao: UserDao? = null

    init {
        val appDatabase = AppDatabase.getInstance()
        userDao = appDatabase?.getUserDao()
    }

    companion object {
        private var INSTANCE: UserManager? = null

        fun getInstance(): UserManager {
            if (INSTANCE == null) {
                synchronized(UserManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = UserManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    fun insertUser(vararg user: User) {
        userDao?.insertUser(*user)
    }

    fun updateUser(vararg user: User) {
        userDao?.updateUser(*user)
    }

    fun updateUserByUserId(userId: Int, userName: String) {
        userDao?.updateUserByUserId(userId, userName)
    }

    fun getCountByUserId(userId: Int): Int? {
        return userDao?.getCountByUserId(userId)
    }

    fun deleteUser(vararg user: User) {
        userDao?.deleteUser(*user)
    }

    fun deleteAllUser() {
        userDao?.deleteAllUser()
    }

    fun queryAllUser(): List<User>? {
        return userDao?.getAllUer()
    }
}