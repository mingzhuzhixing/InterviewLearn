package com.v.kotlin_app.fragment.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.LayoutInflater
import com.v.kotlin_app.R

/**
 * ClassName: HomeCategoryLayout
 * Description:
 *
 * @author zhuming
 * @package_name com.v.kotlin_app.fragment.layout
 * @date 2021/9/30 4:01 下午
 */
class HomeCategoryLayout : LinearLayout {
    var llCategory1: LinearLayout? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_home_category, this, true)
        llCategory1 = view.findViewById(R.id.ll_category_1)
    }
}