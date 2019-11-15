package com.memo.umeng.listener

import com.blankj.utilcode.util.LogUtils
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.bean.SHARE_MEDIA

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-14 19:27
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
open class SimpleAuthListener : UMAuthListener {
    override fun onComplete(platform: SHARE_MEDIA, action: Int, map: MutableMap<String, String>) {
        val builder = StringBuilder()
        for ((key, value) in map) {
            builder.append("key = ").append(key).append(" value = ").append(value).append("\n")
        }
        LogUtils.iTag("UMeng-Login", builder)
    }

    override fun onCancel(platform: SHARE_MEDIA, action: Int) {}

    override fun onError(platform: SHARE_MEDIA, action: Int, error: Throwable) {
        LogUtils.eTag("UMent-Login", error)
    }

    override fun onStart(platform: SHARE_MEDIA) {}
}