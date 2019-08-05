package com.memo.base.manager.data

import com.memo.base.config.config.Config
import com.memo.tool.ext.put
import com.memo.tool.ext.sp

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-25 14:59
 */
class SpManager {

    private val GUIDE_VERSION_CODE = "GUIDE_VERSION_CODE"
    private val TOKEN = "TOKEN"

    private object Holder {
        val instance = SpManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 设置导航版本
     * @param version Int 版本
     */
    fun putGuideVersion() {
        sp().put(GUIDE_VERSION_CODE, Config.guideVersionCode)
    }

    /**
     * 获取导航版本
     * @return Int
     */
    fun getGuideVersion(): Int {
        return sp().getInt(GUIDE_VERSION_CODE, 0)
    }

    /**
     * 设置token
     * @param token String
     */
    fun putToken(token: String) {
        sp().put(TOKEN, token)
    }

    /**
     * 获取token
     * @return String
     */
    fun getToken(): String {
        return sp().getString(TOKEN, "") ?: ""
    }
}