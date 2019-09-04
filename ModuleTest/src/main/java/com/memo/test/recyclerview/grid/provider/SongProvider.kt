package com.memo.test.recyclerview.grid.provider

import android.content.Context
import com.memo.test.R
import com.memo.test.recyclerview.GridMulti
import com.memo.test.recyclerview.MultiEntity
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-02 15:01
 */
class SongProvider :
    BaseMultiProvider<MultiEntity>(GridMulti.TYPE_SONG, R.layout.item_multi_grid_song) {
    override fun converts(context: Context, helper: ViewHolder, item: MultiEntity) {
        ImageLoadHelper.loadImage(context, item.img, helper.getView(R.id.iv_cover))
        helper.setText(R.id.tv_title, item.title)
    }
}