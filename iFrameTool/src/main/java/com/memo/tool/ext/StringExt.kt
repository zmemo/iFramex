package com.memo.iframe.tools.ext

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ScaleXSpan
import com.blankj.utilcode.util.EncryptUtils
import java.util.*

/**
 * title:对于String的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:32
 */

/**
 * 是否是手机号
 * 1+后面10位
 */
fun String.isPhone(): Boolean = "1\\d{10}$".toRegex().matches(this)

/**
 * 是否是邮箱地址
 */
fun String.isEmail(): Boolean = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?".toRegex().matches(this)

/**
 * 是否是身份证号码
 */
fun String.isIDCard(): Boolean = "[1-9]\\d{16}[a-zA-Z0-9]".toRegex().matches(this)

/**
 * 是否是中文字符
 */
fun String.isChinese(): Boolean = "^[\u4E00-\u9FA5]+$".toRegex().matches(this)

/**
 * 获取当前字符串的md5
 */
fun String.md5(): String = EncryptUtils.encryptMD5ToString(this)

/**
 * 判断字符串是否为空或者是null的任意变化
 * 曾经出现过后台返回"Null" 然后判断isNullOrEmpty()通过 显示在界面上的时候悲剧了
 */
fun String?.isNull() = isNullOrEmpty() || this!!.toLowerCase(Locale.getDefault()).trim() == "null"

/**
 * 在最大长度的字符里面进行平均分布代码行数
 * @param maxSize 最大长度不包括结尾的冒号
 */
fun String.justifyAlign(maxSize: Int): String {
    val spannableStringBuilder = SpannableStringBuilder()
    if (TextUtils.isEmpty(this)) {
        return spannableStringBuilder.toString()
    }
    var chars: CharArray = this.toCharArray()
    if (chars.size >= maxSize || chars.size == 1) {
        return spannableStringBuilder.append(this).toString()
    }

    val lastChar: Char = chars.last()
    var isContainColon = false
    if (lastChar == ':' || lastChar == '：') {
        isContainColon = true
        chars = chars.copyOfRange(0, chars.size - 1)
    }
    val l: Int = chars.size
    val scale: Float = (maxSize - l).toFloat() / (l - 1)
    for (i: Int in 0 until l) {
        spannableStringBuilder.append(chars[i])
        if (i != l - 1) {
            // 全角空格
            val s = SpannableString("　")
            s.setSpan(ScaleXSpan(scale), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableStringBuilder.append(s)
        }
    }
    return if (isContainColon) {
        spannableStringBuilder.append(lastChar).toString()
    } else {
        spannableStringBuilder.toString()
    }
}