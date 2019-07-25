package com.memo.tool.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * title:基础ViewPagerAdapter
 * tip:
 *
 * @author zhou
 * @date 2018/8/1 下午7:07
 */
class BaseViewPagerAdapter<T : View>(private var mData:List<T> = arrayListOf()) : PagerAdapter() {

    fun setData(list: List<T>) {
        this.mData = list
        this.notifyDataSetChanged()
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
}