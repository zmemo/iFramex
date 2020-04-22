package com.memo.test.ui.rxjava

import com.blankj.utilcode.util.LogUtils
import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.ext.io2MainLifecycle
import com.memo.base.tool.ext.toObservable
import com.memo.test.R
import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * title:Rxjava练习
 * describe:
 *
 * @author memo
 * @date 2020-02-06 15:13
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RxJavaActivity : BaseActivity() {
    override fun bindLayoutRes(): Int = R.layout.activity_rxjava

    override fun initialize() {
        val rxJavaData = Observable.create<String> {
            RxJavaDataUtils.getData(object : RxJavaDataUtils.RxJavaListener {
                override fun onResponse(data: String) {
                    it.onNext(data)
                }
            })
        }
        val intData = 123.toObservable()
        val strData = " String 123".toObservable()

        Observable.zip(rxJavaData, intData, strData,
            Function3<String, Int, String, String> { rxJava, int, str ->
                rxJava + int + str
            }).io2MainLifecycle(mLifecycleOwner)
            .subscribe {
                LogUtils.iTag("data", it)
            }

    }

}
