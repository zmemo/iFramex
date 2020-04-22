package com.memo.base.tool.adapter.recyclerview

import android.content.Context
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * title: 每一个多布局item就是一个Provider
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:05
 */
abstract class BaseMultiProvider<T : MultiItemEntity>(val multiType: Int, @LayoutRes val layoutRes: Int) {

    /**
     * 数据处理
     */
    abstract fun converts(context: Context, helper: ViewHolder, item: T)


}