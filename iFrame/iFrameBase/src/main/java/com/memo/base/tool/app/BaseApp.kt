package com.memo.base.tool.app

import android.app.Application
import androidx.multidex.MultiDexApplication

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-26 14:47
 */
open class BaseApp : MultiDexApplication() {

    companion object {
        lateinit var app: Application
    }

    init {
        app = this
    }

}