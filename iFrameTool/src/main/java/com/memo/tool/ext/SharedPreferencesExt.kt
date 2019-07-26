package com.memo.tool.ext

import android.content.Context
import android.content.SharedPreferences
import com.blankj.utilcode.util.AppUtils
import com.memo.tool.app.BaseApp

/**
 * title:对SharedPreferences的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 17:09
 */
fun sp(): SharedPreferences =
    BaseApp.app.applicationContext.getSharedPreferences(
        AppUtils.getAppName(),
        Context.MODE_PRIVATE
    )

/**
 * 批处理
 */
fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    edit().apply { action() }.apply()
}

/**
 * 传入基本类型
 */
fun SharedPreferences.put(key: String, value: Any) {
    edit {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
        }
    }
}

fun SharedPreferences.clear() {
    edit { clear() }
}