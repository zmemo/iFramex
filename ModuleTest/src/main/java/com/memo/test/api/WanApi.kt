package com.memo.test.api

import com.memo.test.api.Api.DomainWan
import com.memo.test.retrofit.Wan
import io.reactivex.Observable
import me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 16:53
 */
interface WanApi {
    /**
     * 动态更改BaseUrl
     */
    @Headers(DOMAIN_NAME_HEADER + DomainWan)
    @GET("banner/json")
    fun getWanBanner(): Observable<Wan>
}