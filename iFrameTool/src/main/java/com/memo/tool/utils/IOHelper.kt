package com.memo.tool.utils

import java.io.Closeable
import java.io.IOException

/**
 * title:
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
    fun close(io: Closeable?) {
        try {
            io?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}