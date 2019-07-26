package com.memo.test.ninegridview

import com.memo.test.R
import com.memo.tool.adapter.BaseRecyclerAdapter
import com.memo.tool.utils.ImageLoadHelper

class NineGridViewAdapter : BaseRecyclerAdapter<String>(R.layout.recycler_item_nine_grid) {

    override fun converts(helper: ViewHolder, item: String) {
        ImageLoadHelper.loadImage(
            mContext,
            item,
            R.drawable.ic_pic_error,
            helper.getView(R.id.mImg)
        )
    }
}