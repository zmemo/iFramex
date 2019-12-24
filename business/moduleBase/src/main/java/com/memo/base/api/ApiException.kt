package com.memo.base.api

/**
 * title: 网络请求错误
 * describe:
 *
 * @author zhou
 * @date 2019-07-09 10:30
 */
class ApiException(val code: Int, override val message: String) : Exception(message)