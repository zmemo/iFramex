package com.memo.tool.lifecycle

import android.os.Handler
import android.os.Looper
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

    private var lifecycleOwner: LifecycleOwner

    constructor(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(lifecycleOwner: LifecycleOwner, callback: Callback)
            : super(callback) {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(lifecycleOwner: LifecycleOwner, looper: Looper)
            : super(looper) {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(lifecycleOwner: LifecycleOwner, looper: Looper, callback: Callback)
            : super(looper, callback) {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    private fun addObserver() {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }
}