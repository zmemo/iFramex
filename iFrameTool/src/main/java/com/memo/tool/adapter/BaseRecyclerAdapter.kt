package com.memo.tool.adapter

import android.view.View
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.tool.utils.ClickHelper

/**
 * title:对BaseQuickAdapter的改造
 * describe:
 *
 * @author zhou
 * @date 2019-07-04 14:55
 */
abstract class BaseRecyclerAdapter<T>(@LayoutRes layoutResInt: Int) :
    BaseQuickAdapter<T, BaseRecyclerAdapter.ViewHolder>(layoutResInt) {

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
        if (ClickHelper.isNotFastClick) {
            super.setOnItemClick(v, position)
        }
    }

    override fun setOnItemLongClick(v: View?, position: Int): Boolean {
        if (ClickHelper.isNotFastLongClick) {
            return super.setOnItemLongClick(v, position)
        }
        return false
    }

    /**
     * 自定义ViewHolder
     * 1、setText 如果传入String为null的时候设置""
     */
    class ViewHolder(view: View) : BaseViewHolder(view) {

        override fun setText(viewId: Int, value: CharSequence?): BaseViewHolder {
            return super.setText(viewId, value ?: "")
        }
    }
}