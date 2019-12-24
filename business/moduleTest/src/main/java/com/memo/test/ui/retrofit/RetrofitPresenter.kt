package com.memo.test.ui.retrofit

import android.annotation.SuppressLint
import com.blankj.utilcode.util.LogUtils
import com.memo.base.base.mvp.BasePresenter
import com.memo.tool.ext.io2MainLifecycle

/**
 * title:MVP演示
 * describe: 在这里强制使用了M-V-P三层 其实如果只是简单调用接口可以把Model层去除 简化代码 灵活一点
 *
 * @author zhou
 * @date 2019-01-24 17:05
 */
class RetrofitPresenter : BasePresenter<RetrofitModel, RetrofitContract.View>(),
    RetrofitContract.Presenter {

    override fun buildModel(): RetrofitModel = RetrofitModel()

    @SuppressLint("CheckResult")
    override fun requestZhiHu() {
        mModel.getZhiHu()
            .io2MainLifecycle(mLifeOwner)
            .subscribe({
                mView.getZhiHu(it)
            }, {
                LogUtils.eTag("error", it.toString())
            })
    }

    @SuppressLint("CheckResult")
    override fun requestWan() {
        mModel.getWan()
            .io2MainLifecycle(mLifeOwner)
            .subscribe({
                mView.getWan(it)
            }, {
                LogUtils.eTag("error", it.toString())
            })
    }
}