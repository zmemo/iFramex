package com.memo.tool.ext

import android.annotation.SuppressLint
import com.blankj.utilcode.constant.TimeConstants
import java.text.SimpleDateFormat
import java.util.*

/**
 * title:时间转换
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 15:18
 */

/**
 *  2018-4-6 11:11:11转为毫秒
 *  @param format 时间的格式，默认是按照yyyy-MM-dd HH:mm:ss来转换
 */
fun String.toMills(format: String = "yyyy-MM-dd HH:mm:ss"): Long =
    SimpleDateFormat(format, Locale.getDefault()).parse(this).time

/**
 * Long类型时间戳转为字符串的日期格式
 * @param format 时间的格式，默认是按照yyyy-MM-dd HH:mm:ss来转换
 */
fun Long.toTime(format : String = "yyyy-MM-dd HH:mm:ss") : String =
    SimpleDateFormat(format, Locale.getDefault()).format(Date(this))

/**
 * yyyy-MM-dd HH:mm:ss 到 yyyy-MM-dd HH:mm
 */
fun String.toTimeMin() : String = this.substring(0, this.lastIndexOf(":"))

/**
 * 到分
 */
fun Long.toTimeMin() : String = toTime("yyyy-MM-dd HH:mm")

/**
 * 到日
 */
fun Long.toTimeDay() : String = toTime("yyyy-MM-dd")

/**
 * 获取友好时间描述
 */
@SuppressLint("SimpleDateFormat")
fun Long.toTimeDescribe(): String {
    val now = System.currentTimeMillis()
    val span = now - this
    return when {
        span < TimeConstants.MIN -> "刚刚"
        span < TimeConstants.HOUR -> String.format(Locale.getDefault(), "%d分钟前", span / TimeConstants.MIN)
        span < TimeConstants.DAY -> String.format(Locale.getDefault(), "%d小时前", span / TimeConstants.HOUR)
        span < 2 * TimeConstants.DAY -> String.format("昨天 %tR", this)
        span < 3 * TimeConstants.DAY -> String.format("前天 %tR", this)
        else -> SimpleDateFormat("MM - dd").format(Date(this))
    }
}

/**
 * 获取友好时间描述
 */
@SuppressLint("SimpleDateFormat")
fun String.toTimeDescribe(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    return this.toMills(format).toTimeDescribe()
}
