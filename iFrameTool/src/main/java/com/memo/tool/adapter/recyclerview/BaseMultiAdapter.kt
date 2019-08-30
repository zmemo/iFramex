package com.memo.tool.adapter.recyclerview

import android.util.SparseArray
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 13:41
 */
abstract class BaseMultiAdapter<T : MultiItemEntity>
    : BaseMultiItemQuickAdapter<T, ViewHolder>(null) {

    /*** Provider存储 ***/
    private val mTypeProviders = SparseArray<BaseMultiProvider<T>>()

    init {
        // 绑定多布局类型
        this.bindMultiType().forEach {
            addMultiTypeProviders(it)
        }
    }

    /**
     * 使用addMultiTypeProvider方法进行设置
     */
    private fun addMultiTypeProviders(vararg providers: BaseMultiProvider<T>) {
        providers.forEach {
            mTypeProviders.put(it.multiType, it)
            //
            addItemType(it.multiType, it.layoutRes)
        }
    }

    override fun convert(helper: ViewHolder, item: T) {
        // 更具类型获取Provider进行设置数据
        mTypeProviders[helper.itemViewType]?.converts(mContext, helper, item)
    }

    /**
     * 使用bindMultiTypeProvider方法进行设置Provider
     */
    abstract fun bindMultiType(): List<BaseMultiProvider<T>>

}