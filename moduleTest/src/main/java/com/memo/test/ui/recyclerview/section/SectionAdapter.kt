package com.memo.test.ui.recyclerview.section

import com.blankj.utilcode.util.LogUtils
import com.memo.test.R
import com.memo.test.ui.recyclerview.SectionItem
import com.memo.test.ui.recyclerview.Sections
import com.memo.tool.adapter.recyclerview.BaseSectionAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 10:56
 */
class SectionAdapter : BaseSectionAdapter<SectionItem, Sections>(
    R.layout.item_section_header,
    R.layout.item_section_item
) {
    override fun convertsHeader(helper: ViewHolder, item: Sections) {
        LogUtils.iTag("position", helper.adapterPosition)
        helper.setText(R.id.tv_header, item.header)
    }

    override fun convertsItem(helper: ViewHolder, item: SectionItem) {
        LogUtils.iTag("position", helper.adapterPosition)
        helper.setText(R.id.tv_content, item.content)
        ImageLoadHelper.loadImage(mContext, item.image, helper.getView(R.id.iv_icon))
    }

}