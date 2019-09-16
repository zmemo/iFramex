package com.memo.test.ui.api

import com.memo.base.config.config.Config
import com.memo.tool.helper.GsonHelper
import com.memo.tool.http.interceptor.HttpLogInterceptor
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 18:09
 */
class RetrofitClient private constructor() {

    var zhiHuApi: ZhiHuApi

    var wanApi: WanApi

    var downApi: DownApi

    companion object {
        private val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
        val mZhiHuApi = instance.zhiHuApi
        val mWanApi = instance.wanApi
        val mDownApi = instance.downApi
    }

    init {
        val mOkHttpClient = RetrofitUrlManager.getInstance()
            .with(OkHttpClient.Builder())
            .addInterceptor(HttpLogInterceptor(Config.isOpenLog, "HTTP"))
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        val mRetrofit = Retrofit.Builder()
            .baseUrl(Api.ZhiHuUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
            .client(mOkHttpClient)
            .build()

        zhiHuApi = mRetrofit.create(ZhiHuApi::class.java)
        wanApi = mRetrofit.create(WanApi::class.java)
        downApi = mRetrofit.create(DownApi::class.java)
    }
}