package com.memo.test.ui.retrofit

import com.memo.base.base.mvp.IModel
import com.memo.test.ui.api.RetrofitClient

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 17:06
 */
class RetrofitModel : IModel {

    fun getZhiHu() = RetrofitClient.mZhiHuApi.getZhiHuNews()

    fun getWan() = RetrofitClient.mWanApi.getWanBanner()
}