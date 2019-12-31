package com.memo.base.application

import com.memo.base.manager.init.InitManager
import com.memo.tool.app.BaseApp

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
        InitManager.get().initInApp()
    }
}
