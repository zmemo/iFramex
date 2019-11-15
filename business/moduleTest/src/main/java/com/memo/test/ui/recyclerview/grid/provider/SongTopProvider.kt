package com.memo.test.ui.recyclerview.grid.provider

import android.content.Context
import com.memo.test.R
import com.memo.test.ui.recyclerview.GridMulti
import com.memo.test.ui.recyclerview.MultiEntity
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-12 14:03
 */
class SongTopProvider :
    BaseMultiProvider<MultiEntity>(GridMulti.TYPE_SONG_TOP, R.layout.item_multi_grid_song_top) {
    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        ImageLoadHelper.loadImage(context, item.img, helper.getView(R.id.iv_cover))
        helper.setText(R.id.tv_title, item.title)
    }
}