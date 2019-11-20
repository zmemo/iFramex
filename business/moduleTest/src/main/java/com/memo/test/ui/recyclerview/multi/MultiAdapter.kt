package com.memo.test.ui.recyclerview.multi

import com.memo.test.entity.MultiEntity
import com.memo.test.ui.recyclerview.multi.provider.ImageMultiProvider
import com.memo.test.ui.recyclerview.multi.provider.TextImageMultiProvider
import com.memo.test.ui.recyclerview.multi.provider.TextMultiProvider
import com.memo.tool.adapter.recyclerview.BaseMultiAdapter
import com.memo.tool.adapter.recyclerview.BaseMultiProvider

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:37
 */
class MultiAdapter : BaseMultiAdapter<MultiEntity>() {
    /**
     * 使用bindMultiTypeProvider方法进行设置Provider
     */
    override fun bindMultiType(): List<BaseMultiProvider<MultiEntity>> {
        return listOf(
            TextMultiProvider(),
            ImageMultiProvider(),
            TextImageMultiProvider()
        )
    }

}