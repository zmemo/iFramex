package com.memo.base.manager.data

import com.blankj.utilcode.util.AppUtils
import com.memo.base.config.config.Config
import com.memo.base.config.constant.Constant
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
        sp().put(Constant.SharedPreference.GUIDE_VERSION_CODE, Config.guideVersionCode)
    }

    /**
     * 获取导航版本
     * @return Int
     */
    fun getGuideVersion(): Int {
        return sp().getInt(Constant.SharedPreference.GUIDE_VERSION_CODE, 0)
    }
}