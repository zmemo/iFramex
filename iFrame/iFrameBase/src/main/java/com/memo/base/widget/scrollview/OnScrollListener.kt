package com.memo.base.widget.scrollview

/**
 * title:滑动监听
 * tip:
 *
 * @author zhou
 * @date 2018-08-28 上午11:08
 */
interface OnScrollListener {

	/**
	 * 滑动状态改变了
	 *
	 * @param scrollView ScrollView
	 * @param scrollState 滑动状态
	 */
	fun onScrollStateChanged(scrollView: StateScrollView, scrollState: ScrollStatus)

	/**
	 * 滑动中
	 *
	 * @param scrollView ScrollView
	 * @param isTouchScroll 是否点击滑动
	 * @param curLeft L
	 * @param curTop T
	 * @param oldLeft oldL
	 * @param oldTop oldT
	 */
	fun onScroll(scrollView: StateScrollView, isTouchScroll: Boolean, curLeft: Int, curTop: Int, oldLeft: Int, oldTop: Int)
}