package com.memo.base.manager.router

import android.app.Activity
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.alibaba.android.arouter.launcher.ARouter

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-20 11:42
 */
class RouterManager {

    private object Holder {
        val instance = RouterManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 获取二维码扫描界面
     * @param activity Activity
     * @param requestCode Int
     */
    fun startQrCodeActivityForResult(activity: Activity, requestCode: Int) {
        ARouter.getInstance().build(RouterPath.Ui.QrcodeScanActivity).navigation(activity, requestCode)
    }

    /**
     * 跳转到一个网页
     * @param url String 网页地址
     * @param title String 标题
     */
    fun startWebActivity(@NonNull url: String, @Nullable title: String) {
        ARouter.getInstance().build(RouterPath.Ui.WebActivity)
            .withString("url", url)
            .withString("title", title)
            .navigation()
    }

}