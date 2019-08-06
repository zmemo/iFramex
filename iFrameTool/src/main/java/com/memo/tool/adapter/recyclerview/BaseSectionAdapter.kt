package com.memo.tool.adapter.recyclerview

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.entity.SectionEntity

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 10:02
 */
abstract class BaseSectionAdapter<E, T : SectionEntity<E>>
    (@LayoutRes headerLayoutRes: Int, @LayoutRes itemLayoutRes: Int) :
    BaseSectionQuickAdapter<T, ViewHolder>(itemLayoutRes, headerLayoutRes, null) {

    override fun convertHead(helper: ViewHolder?, item: T) {
        helper ?: return
        convertsHeader(helper, item)
    }

    override fun convert(helper: ViewHolder, item: T) {
        convertsItem(helper, item.t)
    }

    abstract fun convertsHeader(helper: ViewHolder, item: T)

    abstract fun convertsItem(helper: ViewHolder, item: E)


}