package com.memo.umeng.listener

import com.blankj.utilcode.util.LogUtils
import com.memo.base.tool.ext.toast
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-14 19:26
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
open class SimpleShareListener : UMShareListener {
    override fun onResult(platform: SHARE_MEDIA?) {
	    toast("分享成功")
    }

    override fun onCancel(platform: SHARE_MEDIA?) {
    }

    override fun onError(platform: SHARE_MEDIA?, error: Throwable?) {
	    toast("分享失败")
        LogUtils.eTag("UMeng-Share", error)
    }

    override fun onStart(platform: SHARE_MEDIA?) {
    }
}
