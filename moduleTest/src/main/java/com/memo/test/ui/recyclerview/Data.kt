package com.memo.test.ui.recyclerview

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.entity.SectionEntity

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 14:32
 */
object Multi {
    const val TYPE_TEXT = 0
    const val TYPE_IMG = 1
    const val TYPE_TEXT_IMG = 2
}

object GridMulti {
    //轮播图
    const val TYPE_BANNER = 0
    //标题
    const val TYPE_TITLE = 1
    //列表
    const val TYPE_LIST = 2
    //海报
    const val TYPE_POSTER = 3
    //歌曲 右标题
    const val TYPE_SONG_RIGHT = 4
    //歌曲 上标题
    const val TYPE_SONG_TOP = 5
}

data class MultiEntity(
    val title: String,
    val img: String,
    val type: Int
) : MultiItemEntity {
    override fun getItemType(): Int = type
}

class Sections : SectionEntity<SectionItem> {

    constructor(isHeader: Boolean, header: String) : super(isHeader, header)

    constructor(item: SectionItem) : super(item)
}

data class SectionItem(
    val content: String,
    val image: String
)

