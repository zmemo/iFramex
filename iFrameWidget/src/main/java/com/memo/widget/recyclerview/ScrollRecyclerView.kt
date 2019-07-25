package com.memo.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * title:检测滑动快慢的RecyclerView
 * tip:
 *
 * @author zhou
 * @date 2018-08-15 下午2:35
 */
class ScrollRecyclerView : RecyclerView {

    private var listener: ScrollListener? = null

    /**
     * 按照提出的需求 看多少合适
     */
    private val limit = 100

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onScrolled(dx: Int, dy: Int) {
        // 判断是否超出限制
        if (abs(dy) < limit) {
            listener?.onScrollSlow()
        } else {
            listener?.onScrollFast(dy)
        }
        super.onScrolled(dx, dy)
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        // 快速滑动突然停止 dy仍然会保留之前数值 所以还需要判断是否停止滑动
        if (state == SCROLL_STATE_IDLE) {
            listener?.onScrollSlow()
        }
    }

    fun setOnScrollListener(listener: ScrollListener) {
        this.listener = listener
    }

    interface ScrollListener {
        /**
         * 慢速滑动
         */
        fun onScrollSlow()

        /**
         * 快速滑动
         */
        fun onScrollFast(dy: Int)
    }
}
