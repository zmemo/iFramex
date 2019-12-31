package com.memo.pay.wechat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.memo.pay.PayManager
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * title:微信支付回调
 * describe:
 *
 * @author memo
 * @date 2019-11-13 11:30
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class WXPayEntryActivity : Activity(), IWXAPIEventHandler {

    private val PAY_SUCCESS = 0
    private val PAY_FAILURE = -1

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        WeChatPay.get().getWxApi()?.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeChatPay.get().getWxApi()?.handleIntent(intent, this)
    }

    override fun onResp(resp: BaseResp?) {
        resp?.let {
            if (it.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
                if (it.errCode == PAY_SUCCESS) {
                    PayManager.get().getListener()?.onSuccess()
                } else if (it.errCode == PAY_FAILURE) {
                    PayManager.get().getListener()?.onFailure()
                }
            }
        }
        finish()
    }

    override fun onReq(req: BaseReq?) {
    }


}
