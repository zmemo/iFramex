package com.memo.tool.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.memo.tool.ext.onClick

/**
 * title:基础ViewPagerAdapter
 * tip:
 *
 * @author zhou
 * @date 2018/8/1 下午7:07
 */
class BaseViewPagerAdapter<T : View> : PagerAdapter() {

    private var mData: List<T> = arrayListOf()
    private var mListener: OnItemClickListener? = null

    fun setData(list: List<T>) {
        this.mData = list
        this.mData.forEachIndexed { index, t ->
            t.onClick { mListener?.onItemClick(index) }
        }
        this.notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mData[position])
        return mData[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (obj is View) {
            container.removeView(obj)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}