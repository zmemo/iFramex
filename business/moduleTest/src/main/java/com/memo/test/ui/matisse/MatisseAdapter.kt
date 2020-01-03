package com.memo.test.ui.matisse

import android.widget.ImageView

import com.blankj.utilcode.util.ConvertUtils
import com.memo.test.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper


class MatisseAdapter : BaseRecyclerAdapter<String>(R.layout.item_matisse) {

    override fun converts(helper: ViewHolder, item: String) {
        ImageLoadHelper.loadRoundImage(mContext, item,
            ConvertUtils.dp2px(5f),
            R.drawable.ic_empty_zhihu,
            R.drawable.ic_empty_zhihu,
            helper.itemView as ImageView
        )
    }
}