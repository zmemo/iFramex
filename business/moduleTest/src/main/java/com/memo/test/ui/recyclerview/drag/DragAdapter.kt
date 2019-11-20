package com.memo.test.ui.recyclerview.drag

import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.memo.test.R
import com.memo.test.entity.MultiEntity
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-05 09:51
 */
class DragAdapter :
    BaseItemDraggableAdapter<MultiEntity, ViewHolder>(R.layout.item_nine_grid, null) {

    override fun convert(helper: ViewHolder, item: MultiEntity) {
        ImageLoadHelper.loadImage(mContext, item.img, helper.getView(R.id.mImg))
    }
}