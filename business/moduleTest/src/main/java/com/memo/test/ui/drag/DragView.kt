package com.memo.test.ui.drag

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils


/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-05 11:51
 */
class DragView : AppCompatTextView,
    ViewTreeObserver.OnGlobalLayoutListener {

    private var startY = 0f
    private var startX = 0f

    private var startLeft = 0f
    private var startTop = 0f

    private var resetX = 0f
    private var resetY = 0f

    private var enableDrag = true

    private var dragInfo: DragInfo? = null

    private var mListener: OnDragListener? = null

    private var barHeight = BarUtils.getStatusBarHeight()

    constructor(context: Context)
            : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        this.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        startLeft = this.left.toFloat()
        startTop = this.top.toFloat()
        resetX = this.x
        resetY = this.y
        LogUtils.iTag(
            "drag",
            "onGlobalLayout",
            "startLeft = $startLeft",
            "startTop = $startTop",
            "resetX = $resetX",
            "resetY = $resetY",
            "width = $width",
            "height = $height",
            "bar = ${BarUtils.getStatusBarHeight()}"
        )
        this.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!enableDrag) {
            return super.onTouchEvent(event)
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                LogUtils.iTag("drag", "down", "startX = $startX", "startY = $startY")
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = x + (event.x - startX) - startLeft
                translationX = moveX
                val moveY = y + (event.y - startY) - startTop
                translationY = moveY
                val centerX = event.rawX - event.x + this.width / 2
                val centerY = event.rawY - event.y + this.height / 2
                mListener?.onDragMove(centerX, centerY - barHeight)
                LogUtils.iTag("drag", "move", "centerX = $centerX", "centerY = $centerY")
            }
            MotionEvent.ACTION_UP -> {
                val centerX = event.rawX - event.x + this.width / 2
                val centerY = event.rawY - event.y + this.height / 2
                mListener?.onDragUp(centerX, centerY - barHeight)
                LogUtils.iTag("drag", "up", "x = $centerX", "y = $centerY")
            }
            MotionEvent.ACTION_CANCEL -> {
            }
        }
        return true
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        //父控件不要拦截我的事件
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    fun enableDrag(enable: Boolean) {
        this.enableDrag = enable
    }

    fun setDragInfo(dragInfo: DragInfo) {
        this.dragInfo = dragInfo
    }

    fun getDragInfo() = this.dragInfo

    fun changeLocation() {
        startLeft = this.left.toFloat()
        startTop = this.top.toFloat()
        resetX = this.x
        resetY = this.y
        LogUtils.iTag(
            "drag",
            "changeLocation",
            "startLeft = $startLeft",
            "startTop = $startTop",
            "resetX = $resetX",
            "resetY = $resetY"
        )
    }

    fun reset() {
        translationX = resetX - startLeft
        translationY = resetY - startTop
        mListener?.onDragReset()
        LogUtils.iTag(
            "drag", "reset"
        )
    }

    fun setOnDragListener(listener: OnDragListener) {
        this.mListener = listener
    }

    fun setOnDragClickListener(onClick: (self: DragView, dragInfo: DragInfo) -> Unit) {
        this.setOnClickListener {
            this.dragInfo?.let { info ->
                onClick(this, info)
            }
        }
    }

    interface OnDragListener {
        fun onDragUp(x: Float, y: Float)
        fun onDragMove(x: Float, y: Float)
        fun onDragReset()
    }
}