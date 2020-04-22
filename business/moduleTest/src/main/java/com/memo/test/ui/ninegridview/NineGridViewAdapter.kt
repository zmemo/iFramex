package com.memo.test.ui.ninegridview

import com.memo.base.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.base.tool.adapter.recyclerview.ViewHolder
import com.memo.base.tool.helper.ImageLoadHelper
import com.memo.test.R

class NineGridViewAdapter : BaseRecyclerAdapter<String>(R.layout.item_nine_grid) {

    override fun converts(helper: ViewHolder, item: String) {
        ImageLoadHelper.loadImage(
            mContext,
            item,
            R.color.color_F5F5F5,
            R.mipmap.ic_pic_error,
            helper.getView(R.id.mImg)
        )
    }
}