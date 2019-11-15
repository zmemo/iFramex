package com.memo.test.ui.recyclerview.expand

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.memo.tool.adapter.recyclerview.BaseMultiAdapter
import com.memo.tool.adapter.recyclerview.BaseMultiProvider

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-21 15:46
 *
 * Talk is cheap. Show me the code.
 */
class ExpandAdapter : BaseMultiAdapter<MultiItemEntity>() {
    /**
     * 返回MultiProvider集合
     */
    override fun bindMultiType(): List<BaseMultiProvider<MultiItemEntity>> {
        return arrayListOf(TitleProvider(this), ContentProvider())
    }
}