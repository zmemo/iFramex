package com.memo.tool.adapter.recyclerview

import android.view.View
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.memo.tool.utils.ClickHelper

/**
 * title:对BaseQuickAdapter的改造
 * describe:
 *
 * @author zhou
 * @date 2019-07-04 14:55
 */
abstract class BaseRecyclerAdapter<T>(@LayoutRes layoutResInt: Int) :
    BaseQuickAdapter<T, ViewHolder>(layoutResInt) {
    /**
     * 是否允许过快点击
     * @return Boolean enable
     */
    open fun enableFastClick() = false

    override fun convert(helper: ViewHolder, item: T?) {
        item ?: return
        converts(helper, item)
    }

    /**
     * 转换 数据都不为空
     */
    protected abstract fun converts(helper: ViewHolder, item: T)

    /**
     * 防止过快点击
     */
    override fun setOnItemClick(v: View?, position: Int) {
        if (!enableFastClick() && ClickHelper.isNotFastClick) {
            super.setOnItemClick(v, position)
        } else {
            super.setOnItemClick(v, position)
        }
    }

    override fun setOnItemLongClick(v: View?, position: Int): Boolean {
        if (!enableFastClick() && ClickHelper.isNotFastClick) {
            super.setOnItemLongClick(v, position)
        } else {
            super.setOnItemLongClick(v, position)
        }
        return false
    }

}