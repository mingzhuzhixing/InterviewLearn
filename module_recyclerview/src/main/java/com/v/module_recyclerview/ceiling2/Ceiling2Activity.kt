package com.v.module_recyclerview.ceiling2

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_base.BaseTitleBarActivity
import com.v.module_recyclerview.R
import com.v.module_recyclerview.ceiling.Bean
import com.v.module_recyclerview.gridview.GridviewActivity.dp2px
import java.util.*

/**
 * 吸顶2效果
 * https://codeleading.com/article/52416240412/
 *         https://github.com/Mcflag/ExpandStickyRecyclerVIew
 *
 *         https://github.com/oubowu/stickyitemdecoration/
 */
class Ceiling2Activity : BaseTitleBarActivity() {

    private val beanList = mutableListOf<Bean>()
    private var recyclerView: RecyclerView? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_ceiling
    }

    override fun setTitle(): String {
        return "吸顶效果"
    }

    override fun processLogical() {
        super.processLogical()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        recyclerView?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
    }
}