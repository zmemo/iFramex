package com.memo.tool.helper

import java.io.Closeable
import java.io.IOException

/**
 * title:IO流工具
 * describe:
 *
 * @author zhou
 * @date 2019-07-17 17:17
 */
object IOHelper {
	
	/**
	 * 关闭io流
	 */
	@JvmStatic
	fun close(vararg ios : Closeable?) {
		try {
			ios.forEach {
				it?.close()
			}
		} catch (e : IOException) {
			e.printStackTrace()
		}
	}
}