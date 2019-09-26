package com.memo.test.ui.recyclerview.expand

import android.content.Context
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.memo.test.R
import com.memo.test.ui.recyclerview.LevelContent
import com.memo.test.ui.recyclerview.LevelMulti
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-21 15:48
 *
 * Talk is cheap. Show me the code.
 */

class ContentProvider :
    BaseMultiProvider<MultiItemEntity>(LevelMulti.TYPE_CONTENT, R.layout.item_multi_text) {
    /**
     * 数据处理
     */
    override fun converts(context: Context, helper: ViewHolder, item: MultiItemEntity) {
        helper.setText(R.id.tv_title, (item as LevelContent).content)
    }

}