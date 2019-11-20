package com.memo.test.ui.recyclerview.grid.provider

import android.content.Context
import com.memo.test.R
import com.memo.test.entity.GridMulti
import com.memo.test.entity.MultiEntity
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-02 15:58
 */
class BannerProvider :
    BaseMultiProvider<MultiEntity>(GridMulti.TYPE_BANNER, R.layout.item_multi_grid_banner) {
    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        ImageLoadHelper.loadImage(context, item.img, helper.getView(R.id.iv_banner))
    }
}