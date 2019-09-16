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

fun SharedPreferences.put(key: String, value: String) {
    edit().putString(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Int) {
    edit().putInt(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Float) {
    edit().putFloat(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Long) {
    edit().putLong(key, value).apply()
}

fun SharedPreferences.put(key: String, value: MutableSet<String>) {
    edit().putStringSet(key, value).apply()
}

fun SharedPreferences.clear() {
    edit { clear() }
}