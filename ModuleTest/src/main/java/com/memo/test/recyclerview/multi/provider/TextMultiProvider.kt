package com.memo.test.recyclerview.multi.provider

import android.content.Context
import com.memo.test.R
import com.memo.test.recyclerview.Multi
import com.memo.test.recyclerview.MultiEntity
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder

/**
 * title:
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