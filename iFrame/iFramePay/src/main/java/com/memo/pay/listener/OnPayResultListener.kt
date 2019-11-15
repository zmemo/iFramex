package com.memo.pay.listener

/**
 * title:支付结果回调
 * describe:
 *
 * @author memo
 * @date 2019-11-13 10:24
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface OnPayResultListener {
    fun onSuccess()
    fun onFailure()
}