package com.memo.pay.ali

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import com.alipay.sdk.app.PayTask
import com.memo.pay.listener.OnPayResultListener

/**
 * title:支付宝支付
 * describe:
 *
 * @author memo
 * @date 2019-11-13 09:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
internal class AliPay {

    private object Holder {
        val instance = AliPay()
    }

    companion object {
        private val ALI_PAY_WHAT = 1
        fun get() = Holder.instance
    }

    private var mListener: OnPayResultListener? = null

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == ALI_PAY_WHAT) {
                @Suppress("UNCHECKED_CAST")
                val result = PayResult(msg.obj as Map<String, String>)
                if (result.isSuccess) {
                    mListener?.onSuccess()
                } else {
                    mListener?.onFailure()
                }
            }
        }
    }

    fun pay(activity: Activity, orderInfo: String) {
        val payRunnable = Runnable {
            val payTask = PayTask(activity)
            val result = payTask.payV2(orderInfo, true)

            val msg = Message.obtain()
            msg.what = ALI_PAY_WHAT
            msg.obj = result
            mHandler.sendMessage(msg)
        }
        Thread(payRunnable).start()
    }


    class PayResult(rawResult: Map<String, String>) {
        private var resultStatus: String? = null
        private var result: String? = null
        private var memo: String? = null

        val isSuccess: Boolean get() = TextUtils.equals("9000", resultStatus)

        init {
            for (key in rawResult.keys) {
                if (TextUtils.equals(key, "resultStatus")) {
                    resultStatus = rawResult[key]
                } else if (TextUtils.equals(key, "result")) {
                    result = rawResult[key]
                } else if (TextUtils.equals(key, "memo")) {
                    memo = rawResult[key]
                }
            }
        }
    }

}