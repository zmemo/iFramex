package com.memo.tool.handler

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * title:带有生命周期控制的Handler
 * describe: 如果只是使用延时功能 建议查看RxExt中的delay
 *
 * @author memo
 * @date 2019-11-20 14:18
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class WeakHandler(private val lifecycleOwner : LifecycleOwner, callback : Callback)
	: Handler(callback), LifecycleObserver {
	
	init {
		lifecycleOwner.lifecycle.addObserver(this)
	}
	
	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	private fun onDestroy() {
		removeCallbacksAndMessages(null)
		lifecycleOwner.lifecycle.removeObserver(this)
	}
}