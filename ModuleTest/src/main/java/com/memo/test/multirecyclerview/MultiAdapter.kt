package com.memo.test.multirecyclerview

import com.memo.test.multirecyclerview.provider.ImageMultiProvider
import com.memo.test.multirecyclerview.provider.TextImageMultiProvider
import com.memo.test.multirecyclerview.provider.TextMultiProvider
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