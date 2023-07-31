package com.v.module_jetpack.room

import android.view.View
import android.widget.Button
import com.alibaba.fastjson.JSON
import com.v.module_base.BaseTitleBarActivity
import com.v.module_jetpack.R
import com.v.module_jetpack.room.manager.UserManager
import com.v.module_jetpack.room.table.User
import kotlinx.android.synthetic.main.activity_room.*

/**
 * room 数据库
 *
 * Kotlin-Version = 1.6.0 => Room-Version = 2.4.2
 * Kotlin-Version = 1.3.31 => Room-Version = 2.1.0
 * Kotlin-Version = 1.5.20 => Room-Version = 2.3.0
 */
class RoomActivity : BaseTitleBarActivity() {

    override fun setTitle() = "room 数据库"

    override fun getLayoutId() = R.layout.activity_room

    /**
     * 插入用户
     */
    fun insertUserClick(view: View) {
        UserManager.getInstance().insertUser(
            User(12, "张三", 20, "1"),
            User(13, "李四", 60, "1"),
            User(14, "王五", 70, "0"),
            User(15, "赵六", 30, "0")
        )
    }

    /**
     * update用户
     */
    fun updateUserClick(view: View) {
        UserManager.getInstance().updateUserByUserId(12, "张三111")
    }

    /**
     * 获取缩有用户数据
     */
    fun getAllUserClick(view: View) {
        val queryAllUser = UserManager.getInstance().queryAllUser()
        tv_room.text = JSON.toJSONString(queryAllUser)
    }

    /**
     * 通过用户id获取count
     */
    fun getUserCountClick(view: View) {
        val count = UserManager.getInstance().getCountByUserId(12)
        if(view is Button){
            view.text = "通过用户id获取count:$count"
        }
    }
}