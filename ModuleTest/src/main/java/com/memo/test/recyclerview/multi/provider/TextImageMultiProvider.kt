package com.memo.test.recyclerview.multi.provider

import android.content.Context
import com.memo.test.R
import com.memo.test.recyclerview.Multi
import com.memo.test.recyclerview.MultiEntity
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:图文类型
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:31
 */
class TextImageMultiProvider
    : BaseMultiProvider<MultiEntity>(Multi.TYPE_TEXT_IMG, R.layout.item_multi_text_img) {

    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        helper.setText(R.id.tv_title, item.title)
        ImageLoadHelper.loadImage(context, item.img, helper.getView(R.id.iv_icon))
    }
}