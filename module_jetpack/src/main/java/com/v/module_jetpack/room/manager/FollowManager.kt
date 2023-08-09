package com.v.module_jetpack.room.manager

import com.v.module_jetpack.room.dao.FollowDao
import com.v.module_jetpack.room.dao.UserDao
import com.v.module_jetpack.room.database.AppDatabase
import com.v.module_jetpack.room.table.Follow
import com.v.module_jetpack.room.table.User

/**
 * ClassName: LocalRoomRequestManager
 * Description:
 * @package_name com.v.kotlin_app.database.model
 * @author zhuming
 * @date 2021/9/28 6:28 下午
 */
class FollowManager {

    var followDao: FollowDao? = null

    init {
        val appDatabase = AppDatabase.getInstance()
        followDao = appDatabase?.getFollowDao()
    }

    companion object {
        private var INSTANCE: FollowManager? = null

        fun getInstance(): FollowManager {
            if (INSTANCE == null) {
                synchronized(FollowManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = FollowManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    fun insertFollow(vararg follow: Follow) {
        followDao?.insertFollow(*follow)
    }

    fun deleteFollow(vararg follow: Follow) {
        followDao?.deleteFollow(*follow)
    }

    fun deleteAllFollow() {
        followDao?.deleteAllFollow()
    }

    fun getAllFollow(): List<Follow>? {
        return followDao?.getAllFollow()
    }
}