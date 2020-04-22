package com.memo.base.tool.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * title: ListView适配器基础类
 * tip:
 *
 * @author zhou
 * @date 2018/8/16 下午4:53
 */
abstract class BaseListAdapter<T>(protected var context: Context) : BaseAdapter() {
    protected var mData: List<T>? = null

    fun setData(mData: List<T>) {
        this.mData = mData
        this.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    override fun getItem(position: Int): Any? {
        return if (mData != null) mData!![position] else null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        return getItemView(position, convertView, parent)
    }

    /**
     * 设置条目的布局
     *
     * @param position 条目
     * @param convertView 内容
     * @param parent 副布局
     * @return 布局
     */
    abstract fun getItemView(position: Int, convertView: View, parent: ViewGroup): View
}
