package com.memo.test.ui.recyclerview.resume

import com.memo.test.R
import com.memo.test.ui.recyclerview.SectionItem
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-25 15:11
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ChildAdapter : BaseRecyclerAdapter<SectionItem>(R.layout.item_resume_child) {


    /**
     * 转换 数据都不为空
     */
    override fun converts(helper: ViewHolder, item: SectionItem) {
        helper.setText(R.id.mTvName, item.content)
        ImageLoadHelper.loadImage(mContext, item.image, helper.getView(R.id.mIvLogo))
    }
}