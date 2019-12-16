package com.memo.base.manager.bus

import com.blankj.rxbus.RxBus
import com.memo.base.entity.event.PostEvent

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-25 15:00
 */
class BusManager private constructor() {
	
	private object Holder {
		val instance : BusManager = BusManager()
	}
	
	companion object {
		fun get() = Holder.instance
	}
	
	fun subscribeMain(subscriber : Any, onNext : (PostEvent) -> Unit) {
		RxBus.getDefault().subscribe(subscriber, object : RxBus.Callback<PostEvent>() {
			override fun onEvent(t : PostEvent?) {
				t?.let { onNext(it) }
			}
		})
	}
	
	fun postMain(event : PostEvent) {
		RxBus.getDefault().post(event)
	}
	
	fun unregister(subscriber : Any) {
		RxBus.getDefault().unregister(subscriber)
	}
}