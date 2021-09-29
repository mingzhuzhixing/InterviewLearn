package com.v.kotlin.uitls

import android.content.Context
import android.content.SharedPreferences
import com.v.kotlin.MyApplication

/**
 * 本地数据存储
 */
class SpUtils {

    object Holder {
        val instance = SpUtils()
    }

    companion object {
        fun getInstance() = Holder.instance
    }

    private val sp: SharedPreferences =
        MyApplication.getContext().getSharedPreferences("sp_name", Context.MODE_PRIVATE);

    /**
     * 存储本地数据
     */
    fun put(key: String, value: Any) {
        val edit = sp.edit()
        when (value) {
            is String -> {
                edit.putString(key, value)
            }
            is Int -> {
                edit.putInt(key, value)
            }
            is Boolean -> {
                edit.putBoolean(key, value)
            }
            is Float -> {
                edit.putFloat(key, value)
            }
        }
        edit.apply()
    }

    fun getStringValue(key: String): String {
        val value = sp.getString(key, "")
        return value ?: ""
    }

    fun getBooleanValue(key: String): Boolean {
        return sp.getBoolean(key, false)
    }

    fun getIntValue(key: String): Int {
        return sp.getInt(key, 0)
    }

    fun getFloatValue(key: String): Float {
        return sp.getFloat(key, 0f)
    }
}