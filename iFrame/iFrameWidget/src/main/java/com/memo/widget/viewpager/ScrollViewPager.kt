package com.memo.widget.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * title:可以设置是否滑动的ViewPager
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:50
 */
class ScrollViewPager : ViewPager {
	
	/**
	 * 是否可以滑动
	 *
	 * @return true 可以 false 不可以
	 */
	private var isCanScrollable = false
	
	constructor(context : Context) : super(context)
	
	constructor(context : Context, attrs : AttributeSet) : super(context, attrs)
	
	fun scrollEnable(enable : Boolean) {
		this.isCanScrollable = enable
	}
	
	@SuppressLint("ClickableViewAccessibility")
	override fun onTouchEvent(ev : MotionEvent) : Boolean {
		return try {
			isCanScrollable && super.onTouchEvent(ev)
		} catch (e : Exception) {
			e.printStackTrace()
			false
		}
	}
	
	override fun onInterceptTouchEvent(ev : MotionEvent) : Boolean {
		return try {
			isCanScrollable && super.onInterceptTouchEvent(ev)
		} catch (e : Exception) {
			e.printStackTrace()
			false
		}
	}
}