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
object RouterManager {
    
    /**
     * 获取二维码扫描界面
     * @param activity Activity
     * @param requestCode Int 请求码
     */
    fun startQrCodeActivityForResult(activity: Activity, requestCode: Int) {
	    ARouter.getInstance().build(RouterPath.Ui.QrCodeScanActivity)
            .navigation(activity, requestCode)
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

    /**
     * 跳转测试界面
     */
    fun startLauncherTest() {
        ARouter.getInstance().build(RouterPath.Launcher.TestActivity).navigation()
    }

    /**
     * 跳转ui界面
     */
    fun startLauncherUi() {
        ARouter.getInstance().build(RouterPath.Launcher.UiActivity).navigation()
    }

}