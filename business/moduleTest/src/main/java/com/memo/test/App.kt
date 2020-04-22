package com.memo.test

import com.kingja.loadsir.core.LoadSir
import com.memo.base.application.BaseApplication
import com.memo.base.config.config.Config
import com.memo.test.ui.api.Api
import com.memo.test.ui.status.callback.EmptyDataCallback
import com.memo.test.ui.status.callback.LoadingCallback
import com.memo.test.ui.status.callback.NetErrorCallback
import com.memo.test.ui.status.callback.ServerErrorCallback
import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-26 10:24
 */
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        RetrofitUrlManager.getInstance().setDebug(Config.isOpenLog)
        RetrofitUrlManager.getInstance().putDomain(Api.DomainWan, Api.WanUrl)

        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())
            .addCallback(EmptyDataCallback())
            .addCallback(NetErrorCallback())
            .addCallback(ServerErrorCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .commit()
    }
}