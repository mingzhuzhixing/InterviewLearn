package com.v.module_jetpack

import android.content.Intent
import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_jetpack.paging.PagingActivity
import com.v.module_jetpack.room.RoomActivity
import com.v.module_jetpack.room.database.AppDatabase

class JetpackMainActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "JetpackMain"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_jetpack_main
    }

    override fun initData() {
        super.initData()
        AppDatabase.initApplication(this)
    }

    fun pagingClick(view: View) {
        startActivity(Intent(this, PagingActivity::class.java))
    }

    fun roomClick(view: View) {
        startActivity(Intent(this, RoomActivity::class.java))
    }
}