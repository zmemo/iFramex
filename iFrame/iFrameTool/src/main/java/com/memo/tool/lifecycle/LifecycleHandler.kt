package com.memo.tool.lifecycle

import android.os.Handler
import android.os.Handler.Callback
import android.os.Looper
import android.os.Message
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * title: 带有生命周期控制的Handler
 * describe:
 *
 * @author memo
 * @date 2020-01-02 14:33
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LifecycleHandler : Handler, LifecycleObserver {

    private var lifecycleOwner: LifecycleOwner? = null

    constructor(lifecycleOwner: LifecycleOwner) {
        addObserver(lifecycleOwner)
    }

    constructor(lifecycleOwner: LifecycleOwner, callback: Callback)
            : super(callback) {
        addObserver(lifecycleOwner)
    }

    constructor(lifecycleOwner: LifecycleOwner, callback: (Message) -> Unit) : super(Callback {
        callback(it)
        false
    }) {
        addObserver(lifecycleOwner)
    }

    constructor(lifecycleOwner: LifecycleOwner, looper: Looper)
            : super(looper) {
        addObserver(lifecycleOwner)
    }

    constructor(lifecycleOwner: LifecycleOwner, looper: Looper, callback: Callback)
            : super(looper, callback) {
        addObserver(lifecycleOwner)
    }

    constructor(lifecycleOwner: LifecycleOwner, looper: Looper, callback: (Message) -> Unit)
            : super(looper, Callback {
        callback(it)
        false
    }) {
        addObserver(lifecycleOwner)
    }

    private fun addObserver(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        this.lifecycleOwner?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        lifecycleOwner?.lifecycle?.removeObserver(this)
    }
}