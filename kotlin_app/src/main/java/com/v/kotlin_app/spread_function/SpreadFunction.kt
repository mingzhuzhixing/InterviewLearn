package com.v.kotlin_app.spread_function

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * ClassName: SpreadFunction
 * Description: 扩展函数
 *
 * @author zhuming
 * @package_name com.v.kotlin_app.spread_function
 * @date 2023/7/30 23:20
 */

@SuppressLint("SimpleDateFormat")
fun Date.format(format: String = "EEE,yyyy-MM-dd"): String {
    val simFormat = SimpleDateFormat(format)
    return simFormat.format(this.time)
}
