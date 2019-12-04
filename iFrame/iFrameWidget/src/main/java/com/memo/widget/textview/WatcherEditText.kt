package com.memo.widget.textview

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import com.ruffian.library.widget.REditText

/**
 * title:和EditText一样 只是在退出的时候可以方便的清除所有的TextWatcher
 * describe:
 *
 * @author zhou
 * @date 2019-09-04 09:30
 */
class WatcherEditText : REditText {
	
	private val list = arrayListOf<TextWatcher>()
	
	constructor(context : Context) : super(context)
	
	constructor(context : Context, attrs : AttributeSet?) : super(context, attrs)
	
	override fun addTextChangedListener(watcher : TextWatcher) {
		super.addTextChangedListener(watcher)
		list.add(watcher)
	}
	
	override fun removeTextChangedListener(watcher : TextWatcher) {
		super.removeTextChangedListener(watcher)
		list.remove(watcher)
	}
	
	override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		list.forEach {
			super.removeTextChangedListener(it)
		}
		list.clear()
	}
}