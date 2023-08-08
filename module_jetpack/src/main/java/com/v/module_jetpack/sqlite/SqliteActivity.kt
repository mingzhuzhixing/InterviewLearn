package com.v.module_jetpack.sqlite

import android.annotation.SuppressLint
import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_jetpack.R
import com.v.module_jetpack.sqlite.bean.StudentBean
import com.v.module_jetpack.sqlite.dao.StudentDao
import kotlinx.android.synthetic.main.activity_sqlite.*

/**
 * Sqlite数据库
 */
class SqliteActivity : BaseTitleBarActivity() {

    override fun setTitle() = "SQLiteDatabase数据库"

    override fun getLayoutId() = R.layout.activity_sqlite

    private var studentDao: StudentDao? = null

    override fun initData() {
        super.initData()
        studentDao = StudentDao.getInstance(this)
    }

    /**
     * 插入数据
     */
    fun insertClick(view: View) {
        val student1 = StudentBean.Builder()
            .setName("XiaoMing")
            .setTimeStamp(System.currentTimeMillis())
            .build()

        val student2 = StudentBean.Builder()
            .setName("XiaoHuang")
            .setTimeStamp(System.currentTimeMillis())
            .build()

        val student3 = StudentBean.Builder()
            .setName("XiaoGou")
            .setTimeStamp(System.currentTimeMillis())
            .build()

        studentDao?.insert(student1)
//        studentDao?.insert(student2)
//        studentDao?.insert(student3)
    }

    /**
     * 获取所有数据
     */
    fun getAllClick(view: View) {
        val students = studentDao?.getAllStudent(20)
        var str = ""
        if (students == null || students.isEmpty()) {
            tv_all_student.text = str
        } else {
            students.forEach {
                str = "$str $it"
                tv_all_student.text = str
            }
        }
    }

    /**
     * 获取count
     */
    @SuppressLint("SetTextI18n")
    fun getCountClick(view: View) {
        val count = studentDao?.count
        tv_count.text = "学生count:$count"
    }

    /**
     * 清空数据库表数据
     */
    fun cleanClick(view: View) {
        studentDao?.clear()
    }
}