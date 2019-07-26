package com.memo.test.matisse

import android.widget.ImageView

import com.blankj.utilcode.util.ConvertUtils
import com.memo.test.R
import com.memo.tool.adapter.BaseRecyclerAdapter
import com.memo.tool.utils.ImageLoadHelper


class MatisseAdapter : BaseRecyclerAdapter<String>(R.layout.recycler_item_matisse) {

    override fun converts(helper: ViewHolder, item: String) {
        ImageLoadHelper.loadRoundImage(
            mContext,
            item,
            ConvertUtils.dp2px(5f),
            helper.itemView as ImageView
        )
    }
}