package com.memo.widget.scrollview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

/**
 * title:检测滑动状态的ScrollView
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:55
 */
class StateScrollView : NestedScrollView {

    private var mIsTouched = false
    private var mScrollState = ScrollStatus.SCROLL_STATE_IDLE

    private var mOnScrollListener: OnScrollListener? = null

    private val mHandler = Handler(Looper.getMainLooper(), object : Handler.Callback {

        private var mLastY = Integer.MIN_VALUE

        override fun handleMessage(msg: Message): Boolean {
            if (msg.what == MSG_SCROLL) {
                val scrollY = scrollY

                if (!mIsTouched && mLastY == scrollY) {
                    mLastY = Integer.MIN_VALUE
                    setScrollState(ScrollStatus.SCROLL_STATE_IDLE)
                } else {
                    mLastY = scrollY
                    restartCheckStopTiming()
                }
                return true
            }
            return false
        }
    })

    private fun restartCheckStopTiming() {
        mHandler.removeMessages(MSG_SCROLL)
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL, CHECK_SCROLL_STOP_DELAY_MILLIS.toLong())
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun setOnScrollListener(onScrollListener: OnScrollListener) {
        mOnScrollListener = onScrollListener
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        handleDownEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        handleUpEvent(ev)
        return super.onTouchEvent(ev)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (mIsTouched) {
            setScrollState(ScrollStatus.SCROLL_STATE_TOUCH_SCROLL)
        } else {
            setScrollState(ScrollStatus.SCROLL_STATE_FLING)
            restartCheckStopTiming()
        }
        if (mOnScrollListener != null) {
            mOnScrollListener!!.onScroll(this, mIsTouched, l, t, oldl, oldt)
        }
    }

    private fun handleDownEvent(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> mIsTouched = true
            else -> {
            }
        }
    }

    private fun handleUpEvent(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mIsTouched = false
                restartCheckStopTiming()
            }
        }
    }

    private fun setScrollState(state: ScrollStatus) {
        if (mScrollState != state) {
            mScrollState = state
            if (mOnScrollListener != null) {
                mOnScrollListener!!.onScrollStateChanged(this, state)
            }
        }
    }

    companion object {

        private val CHECK_SCROLL_STOP_DELAY_MILLIS = 80
        private val MSG_SCROLL = 1
    }
}
