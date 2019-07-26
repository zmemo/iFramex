package com.memo.test.retrofit

import android.annotation.SuppressLint
import com.memo.base.ui.mvp.BasePresenter
import com.memo.tool.helper.RxHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 17:05
 */
class RetrofitPresenter : BasePresenter<RetrofitModel, RetrofitContract.View>(),
    RetrofitContract.Presenter {

    override fun buildModel(): RetrofitModel = RetrofitModel()

    @SuppressLint("CheckResult")
    override fun requestZhiHu() {
        mModel?.getZhiHu()
            ?.compose(RxHelper.io2Main())
            ?.subscribe {
                mView?.getZhiHu(it)
            }
    }

    @SuppressLint("CheckResult")
    override fun requestWan() {
        mModel?.getWan()
            ?.compose(RxHelper.io2Main())
            ?.subscribe {
                mView?.getWan(it)
            }
    }
}