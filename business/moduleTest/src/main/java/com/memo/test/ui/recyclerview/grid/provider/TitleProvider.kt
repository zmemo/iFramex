package com.memo.test.ui.recyclerview.grid.provider

import android.content.Context
import com.memo.base.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.base.tool.adapter.recyclerview.ViewHolder
import com.memo.test.R
import com.memo.test.entity.GridMulti
import com.memo.test.entity.MultiEntity

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-02 14:43
 */
class TitleProvider : BaseMultiProvider<MultiEntity>(
    GridMulti.TYPE_TITLE,
    R.layout.item_multi_grid_title
) {

    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        helper.setText(R.id.tv_title, item.title)
    }

}