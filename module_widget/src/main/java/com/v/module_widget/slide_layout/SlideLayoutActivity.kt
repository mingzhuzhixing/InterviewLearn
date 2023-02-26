package com.v.module_widget.slide_layout

import android.content.Intent
import com.v.module_base.BaseTitleBarActivity
import com.v.module_widget.R
import kotlinx.android.synthetic.main.activity_slide_layout.*

/**
 * SlideLayout 双列表页面
 * https://zhuanlan.zhihu.com/p/74182897
 * https://github.com/iftechio/iftech-android-slide-layout/blob/master/library/build.gradle
 */
class SlideLayoutActivity : BaseTitleBarActivity() {
    override fun setTitle(): String {
        return "SlideLayout 双列表页面"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_slide_layout
    }

    override fun initData() {
        btn_debug_scroll.setOnClickListener {
            startDebug(DebugSlideActivity.DEBUG_TYPE_SCROLL)
        }

        btn_debug_slide.setOnClickListener {
            startDebug(DebugSlideActivity.DEBUG_TYPE_SLIDE)
        }
    }

    override fun processLogical() {
    }

    private fun startDebug(debugType: String) {
        startActivity(Intent(this, DebugSlideActivity::class.java).apply {
            putExtra(DebugSlideActivity.DEBUG_TYPE, debugType)
        })
    }
}