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

private val sSharedPreferences: SharedPreferences by lazy {
    BaseApp.app.applicationContext.getSharedPreferences(AppUtils.getAppName(), Context.MODE_PRIVATE)
}

fun sp(): SharedPreferences = sSharedPreferences

/**
 * 批处理
 */
fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    edit().apply { action() }.apply()
}