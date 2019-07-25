package com.memo.base.application

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.memo.base.R
import com.memo.crashhunter.CrashHunter
import com.memo.base.config.config.Config
import com.memo.tool.preview.PreviewImageLoader
import com.previewlibrary.ZoomMediaLoader

/**
 * title:Application
 * tip:
 *
 * @author zhou
 * @date 2018-11-15 下午6:03
 */
open class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // 初始化崩溃界面
        CrashHunter.init(this).isDebug(Config.isShowCrash)

        // 哆啦A梦插件初始化
        DoraemonKit.install(this)

        // 初始化AndroidUtilCode
        Utils.init(this)

        // 初始化日志打印
        LogUtils.getConfig()
            .setLogSwitch(Config.isOpenLog)
            .globalTag = "iFrame"

        // 大图预览
        ZoomMediaLoader.getInstance().init(PreviewImageLoader(R.drawable.ic_pic_error))

        // 初始化ARouter
        if (Config.isDebug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}
