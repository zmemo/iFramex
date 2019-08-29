package com.memo.base.application

import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.memo.base.R
import com.memo.base.config.config.Config
import com.memo.tool.app.BaseApp
import com.memo.tool.preview.PreviewImageLoader
import com.previewlibrary.ZoomMediaLoader
import com.tencent.mmkv.MMKV

/**
 * title:Application
 * tip:
 *
 * @author zhou
 * @date 2018-11-15 下午6:03
 */
open class BaseApplication : BaseApp() {


    override fun onCreate() {
        super.onCreate()

        // 哆啦A梦插件初始化
        DoraemonKit.install(this)

        // 初始化MMKV
        MMKV.initialize(this)

        // 初始化AndroidUtilCode
        Utils.init(this)

        // 初始化日志打印
        LogUtils.getConfig()
            .setLogSwitch(Config.isOpenLog)
            .globalTag = "iFrame"

        // 大图预览
        ZoomMediaLoader.getInstance().init(PreviewImageLoader(R.mipmap.ic_pic_error))

        // 初始化ARouter
        if (Config.isDebug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}
