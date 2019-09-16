package com.memo.tool.helper

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.memo.tool.ext.inflaterView

/**
 * title: BaseQuickAdapter 配置
 * describe:
 *
 * @author zhou
 * @date 2019-09-16 09:24
 *
 * Talk is cheap. Show me the code.
 */
object Adapter {

    /**
     * 设置空布局
     * @param adapter           适配器
     * @param emptyLayoutRes    空布局
     * @param rv                载体RecyclerView
     */
    @JvmStatic
    fun setEmptyView(
        adapter: BaseQuickAdapter<*, *>,
        @LayoutRes emptyLayoutRes: Int,
        rv: RecyclerView
    ) {
        if (adapter.emptyViewCount == 0) {
            adapter.setEmptyView(emptyLayoutRes, rv)
        }
    }

    /**
     * 设置空布局和高度
     * @param context           上下文
     * @param adapter           适配器
     * @param emptyLayoutRes    空布局
     * @param height            高度
     */
    @JvmStatic
    fun setEmptyView(
        context: Context,
        adapter: BaseQuickAdapter<*, *>,
        @LayoutRes emptyLayoutRes: Int,
        height: Int
    ) {
        if (adapter.emptyViewCount == 0) {
            val emptyView = context.inflaterView(emptyLayoutRes)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height)
            emptyView.layoutParams = params
            adapter.emptyView = emptyView
        }
    }
}