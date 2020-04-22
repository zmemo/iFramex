package com.memo.base.tool.dialog.listener

import com.memo.base.tool.dialog.dialog.BottomGridDialog

/**
 * title:弹窗条目点击
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 14:35
 */
interface OnListItemClickListener {
    /**
     * 条目点击
     */
    fun onItemClick(position: Int, item: String)
}

interface OnGridItemClickListener {
    /**
     * 条目点击
     */
    fun onItemClick(position: Int, item: BottomGridDialog.GridItem)
}

abstract class OnTipClickListener {
    /**
     * 点击确定
     */
    abstract fun onPositive()

    /**
     * 点击取消
     */
    open fun onNegative() {}
}