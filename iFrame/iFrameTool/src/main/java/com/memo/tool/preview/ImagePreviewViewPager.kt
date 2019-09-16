package com.memo.tool.preview

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

    var isCanScrollable = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isCanScrollable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return try {
            isCanScrollable && super.onInterceptTouchEvent(ev)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun setEnabled(enabled: Boolean) {
        isCanScrollable = enabled
        super.setEnabled(enabled)
    }

}