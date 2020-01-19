package com.memo.tool.adapter.recyclerview

import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.util.set
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.memo.tool.helper.ClickHelper

/**
 * title:对BaseQuickAdapter的改造
 * describe:
 *
 * @author zhou
 * @date 2019-07-04 14:55
 */
abstract class BaseRecyclerAdapter<T>(@LayoutRes layoutResInt: Int) :
    BaseQuickAdapter<T, ViewHolder>(layoutResInt) {

    /**
     * 是否允许过快点击 只正对setOnItemClickListener 对于 setOnItemChildClickListener还是需要手动操作
     */
    private var enableItemViewFastClick = false

    /*** 用户存储子RecyclerView的状态 ***/
    protected val mStateCache by lazy { SparseArray<Parcelable?>() }


    override fun convert(helper: ViewHolder, item: T?) {
        item ?: return
        converts(helper, item)
    }

    /**
     * 转换 数据都不为空
     */
    protected abstract fun converts(helper: ViewHolder, item: T)

    /**
     * 是否允许过快点击 只正对setOnItemClickListener 对于 setOnItemChildClickListener还是需要手动操作
     * @param enable Boolean
     * @return BaseRecyclerAdapter<T>
     */
    fun enableItemViewFastClick(enable: Boolean): BaseRecyclerAdapter<T> {
        this.enableItemViewFastClick = enable
        return this
    }

    /**
     * 设置数据并且恢复状态
     * @param data 数据源
     * @param state 状态数据
     */
    fun setData(data: List<T>, state: Parcelable?) {
        (this.recyclerView?.layoutManager as LinearLayoutManager?)?.onRestoreInstanceState(state)
        setNewData(data)
    }

    /**
     * 在界面回收的时候进行保存子RecyclerView的状态
     * 对于其他自定义控件 建议进行自定义的保存状态
     * @param holder ViewHolder
     */
    override fun onViewRecycled(holder: ViewHolder) {
        bindSaveStateChildRecyclerView(holder)?.let {
            val position = holder.adapterPosition
            val layoutManager: RecyclerView.LayoutManager? = it.layoutManager
            mStateCache[position] = layoutManager?.onSaveInstanceState()
        }
        super.onViewRecycled(holder)
    }

    /**
     * 绑定子RecyclerView
     * @param holder ViewHolder
     * @return RecyclerView?
     */
    protected open fun bindSaveStateChildRecyclerView(holder: ViewHolder): RecyclerView? = null

    /**
     * 防止过快点击
     */
    override fun setOnItemClick(v: View?, position: Int) {
        if (!enableItemViewFastClick) {
            if (ClickHelper.isNotFastClick) {
                super.setOnItemClick(v, position)
            }
        } else {
            super.setOnItemClick(v, position)
        }
    }

    override fun setOnItemLongClick(v: View?, position: Int): Boolean {
        if (!enableItemViewFastClick) {
            if (ClickHelper.isNotFastLongClick) {
                super.setOnItemLongClick(v, position)
            }
        } else {
            super.setOnItemLongClick(v, position)
        }
        return false
    }

}