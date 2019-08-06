package com.memo.test.recyclerview.multi

import com.memo.test.recyclerview.MultiEntity
import com.memo.test.recyclerview.multi.provider.ImageMultiProvider
import com.memo.test.recyclerview.multi.provider.TextImageMultiProvider
import com.memo.test.recyclerview.multi.provider.TextMultiProvider
import com.memo.tool.adapter.recyclerview.BaseMultiAdapter

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:37
 */
class MultiAdapter : BaseMultiAdapter<MultiEntity>() {

    /**
     * 具体的业务逻辑都在各自的Provider中进行处理
     * 注意使用bindMultiTypeProvider进行加载Provider
     */
    override fun bindMultiType() {
        bindMultiTypeProviders(
            TextMultiProvider(),
            ImageMultiProvider(),
            TextImageMultiProvider()
        )
    }

}