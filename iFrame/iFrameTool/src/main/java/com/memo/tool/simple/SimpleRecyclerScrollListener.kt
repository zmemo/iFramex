package com.memo.tool.simple

import androidx.recyclerview.widget.RecyclerView

/**
 * title:用于判断RecyclerView是否触顶和触底 快速滑动 慢速滑动
 * describe:
 *
 * @author zhou
 * @date 2019-03-12 14:08
 */
open class SimpleRecyclerScrollListener() : RecyclerView.OnScrollListener() {

    /**
     * 默认速度限制
     */
    private var speedLimit: Int = 80

    constructor(limit: Int) : this() {
        speedLimit = limit
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val canScrollBottom = recyclerView.canScrollVertically(1)
        val canScrollTop = recyclerView.canScrollVertically(-1)
        if (!canScrollBottom) {
            // 滑动到底部
            onReachTheBottom()
        }
        if (!canScrollTop) {
            // 滑动到顶部
            onReachTheTop()
        }
        if (Math.abs(dy) < speedLimit) {
            onScrollSlow()
        } else {
            // 就以纵向滑动为例子  到底部了么 到顶部了么
            if (!canScrollBottom || !canScrollTop) {
                onScrollSlow()
            } else {
                onScrollFast()
            }
        }
    }

    /**
     * 触顶
     */
    fun onReachTheTop() {}

    /**
     * 触底
     */
    fun onReachTheBottom() {}

    /**
     * 缓慢滑动
     */
    fun onScrollSlow() {}

    /**
     * 快速滑动
     */
    fun onScrollFast() {}
}