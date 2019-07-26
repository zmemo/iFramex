package com.memo.base.manager.bus

import com.blankj.rxbus.RxBus

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-25 15:00
 */
class BusManager private constructor() {

    private object Holder {
        val instance: BusManager = BusManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    fun subscribe(subscriber: Any, onNext: (String) -> Unit) {
        RxBus.getDefault()
            .subscribe(subscriber, BusTag.TAG_MAIN, object : RxBus.Callback<String>() {
                override fun onEvent(t: String?) {
                    t?.let { onNext(it) }
                }
            })
    }

    fun post(message: String) {
        RxBus.getDefault().post(message, BusTag.TAG_MAIN)
    }

    fun unregister(subscriber: Any) {
        RxBus.getDefault().unregister(subscriber)
    }
}