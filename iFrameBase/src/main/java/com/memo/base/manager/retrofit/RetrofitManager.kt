package com.memo.base.manager.retrofit

import com.memo.base.config.config.Config
import com.memo.base.config.constant.Constant
import com.memo.tool.http.interceptor.HttpLogInterceptor
import com.memo.tool.utils.GsonHelper
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * title:网络请求管理
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 18:09
 */
class RetrofitManager private constructor() {

    //var api:ApiService

    companion object {
        private val instance: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
        //val mApi = instance.api
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
            .baseUrl(Constant.Api.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
            .client(mOkHttpClient)
            .build()

        //api = mRetrofit.create(ApiService::class.java)

    }
}