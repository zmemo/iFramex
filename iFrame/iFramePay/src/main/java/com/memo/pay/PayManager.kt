package com.memo.pay

import android.app.Activity
import android.content.Context
import com.alipay.sdk.app.EnvUtils
import com.memo.pay.ali.AliPay
import com.memo.pay.listener.OnPayResultListener
import com.memo.pay.wechat.WeChatPay

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-13 10:45
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class PayManager {

    private var mListener: OnPayResultListener? = null

    private object Holder {
        val instance = PayManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    fun getListener(): OnPayResultListener? = mListener

    /**
     * 设置全局监听
     */
    fun setListener(listener: OnPayResultListener): PayManager {
        this.mListener = listener
        return this
    }

    fun startAliPaySandbox(activity: Activity, orderInfo: String) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        AliPay.get().pay(activity, orderInfo)
    }

    /**
     * 开启支付宝支付
     */
    fun startAliPay(activity: Activity, orderInfo: String) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.ONLINE)
        AliPay.get().pay(activity, orderInfo)
    }

    /**
     * 开启微信支付
     */
    fun startWeChatPay(
        context: Context,
        appId: String, partnerId: String, prepayId: String,
        nonceStr: String, timeStamp: String, sign: String
    ) {
        WeChatPay.get().pay(context, appId, partnerId, prepayId, nonceStr, timeStamp, sign)
    }
}