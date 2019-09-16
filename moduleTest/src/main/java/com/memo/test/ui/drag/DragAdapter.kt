package com.memo.test.ui.drag

import com.memo.test.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-06 10:07
 */
class DragAdapter : BaseRecyclerAdapter<DragInfo>(R.layout.item_drag) {
    /**
     * 转换 数据都不为空
     */
    override fun converts(helper: ViewHolder, item: DragInfo) {
        helper.setText(R.id.mTvName, item.text)
    }
}