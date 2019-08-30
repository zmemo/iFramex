package com.memo.test.recyclerview.multi.provider

import android.content.Context
import com.memo.test.R
import com.memo.test.recyclerview.Multi
import com.memo.test.recyclerview.MultiEntity
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.utils.ImageLoadHelper

/**
 * title:图片类型
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:31
 */
class ImageMultiProvider
    : BaseMultiProvider<MultiEntity>(Multi.TYPE_IMG, R.layout.item_multi_img) {

    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        ImageLoadHelper.loadImage(context, item.img, helper.getView(R.id.iv_icon))
    }
}