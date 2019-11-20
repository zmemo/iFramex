package com.memo.test.ui.api

import com.memo.test.entity.ZhiHuNews
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 16:54
 */
interface ZhiHuApi {
    // https://news-at.zhihu.com/api/4/news/latest
    @GET("api/4/news/latest")
    fun getZhiHuNews(): Observable<ZhiHuNews>
}