package com.memo.test.ninegridview

import com.memo.test.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

class NineGridViewAdapter : BaseRecyclerAdapter<String>(R.layout.item_nine_grid) {

    override fun converts(helper: ViewHolder, item: String) {
        ImageLoadHelper.loadImage(
            mContext,
            item,
            R.mipmap.ic_pic_error,
            helper.getView(R.id.mImg)
        )
    }
}