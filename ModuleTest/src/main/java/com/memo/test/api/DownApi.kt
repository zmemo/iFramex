package com.memo.test.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 15:46
 */
interface DownApi {
    @Streaming
    @GET
    fun download(@Url url: String): Observable<ResponseBody>
}