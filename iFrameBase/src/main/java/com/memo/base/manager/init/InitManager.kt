package com.memo.base.manager.init

import android.app.Activity
import android.app.Application
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.memo.base.BuildConfig
import com.memo.base.R
import com.memo.base.config.config.Config
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mmkv.MMKV

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-30 09:59
 */
class InitManager {

    private var isInitInApp = false

    private var isInitInSplash = false

    private object Holder {
        val instance = InitManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 在App中进行初始化
     * @param app Application
     */
    fun initInApp(app: Application) {
        if (!isInitInApp) {
            // 哆啦A梦插件初始化
            DoraemonKit.install(app)

            // 初始化MMKV
            MMKV.initialize(app)

            // 初始化AndroidUtilCode
            Utils.init(app)

            // 初始化日志打印
            LogUtils.getConfig()
                .setLogSwitch(Config.isOpenLog)
                .globalTag = "iFrame"


            // 初始化ARouter
            if (BuildConfig.DEBUG) {
                ARouter.openLog()
                ARouter.openDebug()
            }
            ARouter.init(app)

            //初始化刷新框架
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                ClassicsHeader(context)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                BallPulseFooter(context)
                    .setNormalColor(ContextCompat.getColor(context, R.color.color_666666))
                    .setAnimatingColor(ContextCompat.getColor(context, R.color.color_666666))
            }


            isInitInApp = true
        }
    }

    /**
     * 放在闪屏页面进行初始化 这个时间一般都在1秒以上
     * @param activity Activity
     */
    fun initInSplash(activity: Activity) {
        if (!isInitInSplash) {

            isInitInSplash = true
        }
    }

}