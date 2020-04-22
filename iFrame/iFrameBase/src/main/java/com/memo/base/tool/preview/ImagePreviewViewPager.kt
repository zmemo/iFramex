package com.memo.base.tool.preview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.previewlibrary.wight.PhotoViewPager

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-10 16:57
 */
class ImagePreviewViewPager : PhotoViewPager {

    private var isScrollable = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isScrollable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return try {
            isScrollable && super.onInterceptTouchEvent(ev)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun setEnabled(enabled: Boolean) {
        isScrollable = enabled
        super.setEnabled(enabled)
    }

}