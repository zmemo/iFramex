package com.memo.base.widget.scrollview

/**
 * title:滑动状态
 * tip:
 *
 * @author zhou
 * @date 2018-11-13 下午8:54
 */
enum class ScrollStatus(val status: Int) {

	/**
	 * 停滞状态
	 */
	SCROLL_STATE_IDLE(0),

	/**
	 * 触摸滑动状态
	 */
	SCROLL_STATE_TOUCH_SCROLL(1),

	/**
	 * 自由滑动状态
	 */
	SCROLL_STATE_FLING(2)
}
