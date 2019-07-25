package com.memo.base.api

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-10 17:47
 */
object ApiCode {

    /*** 未知错误 ***/
    const val UnknownErrorCode = 1001

    /*** 网络错误 ***/
    const val NetworkErrorCode = 1002

    /*** 解析错误 ***/
    const val ParseErrorCode = 1003

    /*** 无法连接服务器 ***/
    const val ServerErrorCode = 1004


    //Example：后台返回的ErrorCode
    /*** 请求成功 ***/
    const val Success = 10000

    /*** 数据不存在 ***/
    const val DataNotFound = 10001

    /*** 内容已删除 ***/
    const val ContentDeleted = 10002


}