package com.memo.test.ui.recyclerview.multi.provider

import android.content.Context
import com.memo.base.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.base.tool.adapter.recyclerview.ViewHolder
import com.memo.test.R
import com.memo.test.entity.Multi
import com.memo.test.entity.MultiEntity

/**
 * title:文字类型
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:31
 */
class TextMultiProvider
    : BaseMultiProvider<MultiEntity>(Multi.TYPE_TEXT, R.layout.item_multi_text) {

    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        helper.setText(R.id.tv_title, item.title)
    }
}