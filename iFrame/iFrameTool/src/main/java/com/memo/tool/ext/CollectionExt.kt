package com.memo.tool.ext

/**
 * title:集合拓展
 * describe:
 *
 * @author memo
 * @date 2019-12-19 16:12
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

/**
 * 循环满足条件的时候退出
 */
fun <T> Iterable<T>.forEachReturnWhen(onAction : (T) -> Boolean) {
	run loop@{
		for (item in this) {
			if (onAction(item)) {
				return@loop
			}
		}
	}
}

/**
 * 转换成字符串输出
 */
fun <T> Iterable<T>.convert2String(split : String = ",") : String {
	val sb = StringBuilder()
	this.forEachIndexed { index, t ->
		if (index == 0) sb.append(t.toString()) else sb.append(split).append(t.toString())
	}
	return sb.toString()
}

/**
 * 循环满足条件的时候退出
 */
fun <T> Collection<T>.forEachReturnWhen(onAction : (T) -> Boolean) {
	run loop@{
		for (item in this) {
			if (onAction(item)) {
				return@loop
			}
		}
	}
}

/**
 * 转换成字符串输出
 */
fun <T> Collection<T>.convert2String(split : String = ",") : String {
	val sb = StringBuilder()
	this.forEachIndexed { index, t ->
		if (index == 0) sb.append(t.toString()) else sb.append(split).append(t.toString())
	}
	return sb.toString()
}

/**
 * 循环满足条件的时候退出
 */
fun <T> Array<T>.forEachReturnWhen(onAction : (T) -> Boolean) {
	run loop@{
		for (item in this) {
			if (onAction(item)) {
				return@loop
			}
		}
	}
}

/**
 * 转换成字符串输出
 */
fun <T> Array<T>.convert2String(split : String = ",") : String {
	val sb = StringBuilder()
	this.forEachIndexed { index, t ->
		if (index == 0) sb.append(t.toString()) else sb.append(split).append(t.toString())
	}
	return sb.toString()
}