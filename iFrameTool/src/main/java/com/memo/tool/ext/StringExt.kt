package com.memo.iframe.tools.ext

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

