package com.memo.base.api

import androidx.annotation.Keep


/**
 * title:默认的实体类 直接加keep注解放置混淆
 * describe:
 *
 * @author zhou
 * @date 2019-07-09 10:17
 */
@Keep
class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String
) {
    /**
     * 判断是否是请求成功
     */
    fun isSuccess() = code == ApiCode.Success
}