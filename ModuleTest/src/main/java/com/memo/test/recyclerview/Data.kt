package com.memo.test.recyclerview

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