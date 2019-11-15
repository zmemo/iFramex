package com.memo.pay.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.memo.pay.PayManager
import com.memo.pay.wechat.WeChatPay
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
class WXPayEntryActivity : AppCompatActivity(), IWXAPIEventHandler {

    private val PaySuccess = 0
    private val PayError = -1

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
                if (it.errCode == PaySuccess) {
                    PayManager.get().getListener()?.onSuccess()
                } else if (it.errCode == PayError) {
                    PayManager.get().getListener()?.onFailure()
                }
            }
        }
        finish()
    }

    override fun onReq(req: BaseReq?) {
    }


}
