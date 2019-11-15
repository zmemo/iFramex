package com.memo.pay.wechat

import com.memo.tool.app.BaseApp
import com.memo.tool.helper.toast
import com.tencent.mm.opensdk.constants.Build
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * title:微信支付
 * describe:
 *
 * @author memo
 * @date 2019-11-13 10:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
internal class WeChatPay {

    private object Holder {
        val instance = WeChatPay()
    }

    companion object {
        fun get() = Holder.instance
    }

    private var mWxApi: IWXAPI? = null

    fun pay(
        appId: String, partnerId: String, prepayId: String,
        nonceStr: String, timeStamp: String, sign: String
    ) {
        if (mWxApi == null) {
            mWxApi = WXAPIFactory.createWXAPI(BaseApp.app.applicationContext, null)
            mWxApi!!.registerApp(appId)
        }
        if (mWxApi!!.isWXAppInstalled && mWxApi!!.wxAppSupportAPI >= Build.PAY_SUPPORTED_SDK_INT) {
            val request = PayReq()
            request.appId = appId
            request.partnerId = partnerId
            request.prepayId = prepayId
            request.packageValue = "Sign=WXPay"
            request.nonceStr = nonceStr
            request.timeStamp = timeStamp
            request.sign = sign
            mWxApi!!.sendReq(request)
        } else {
            toast("请安装微信或升级微信版本")
        }
    }

    fun getWxApi() = mWxApi

}