package com.memo.test.ui.recyclerview.expand

import android.content.Context
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.memo.base.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.base.tool.adapter.recyclerview.ViewHolder
import com.memo.base.tool.ext.onClick
import com.memo.test.R
import com.memo.test.entity.LevelMulti
import com.memo.test.entity.LevelTitle

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-21 15:48
 *
 * Talk is cheap. Show me the code.
 */

class TitleProvider(private val adapter: BaseMultiItemQuickAdapter<MultiItemEntity, ViewHolder>) :
    BaseMultiProvider<MultiItemEntity>(LevelMulti.TYPE_TITLE, R.layout.item_multi_text) {
    /**
     * 数据处理
     */
    override fun converts(context: Context, helper: ViewHolder, item: MultiItemEntity) {
        helper.setText(R.id.tv_title, "点击标题可收缩 - " + (item as LevelTitle).content)
        helper.itemView.onClick {
            if (item.isExpanded) {
                adapter.collapse(helper.adapterPosition)
            } else {
                adapter.expand(helper.adapterPosition)
            }
        }
    }

}