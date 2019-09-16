package com.memo.tool.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * title：ViewPager和Fragment 的适配器
 * tip:
 *
 * @author zhou
 * @date 2018/8/20 下午5:33
 */
class BaseFragmentPagerAdapter<T : Fragment>(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    protected var mData: List<T>? = null
    private var withTabLayoutTitles: Array<String>? = null

    fun setData(list: List<T>) {
        this.mData = list
        this.notifyDataSetChanged()
    }

    fun setData(list: List<T>, withTabLayoutTitles: Array<String>?) {
        this.mData = list
        this.withTabLayoutTitles = withTabLayoutTitles
        this.notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return mData!![position]
    }

    override fun getCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (withTabLayoutTitles != null) {
            withTabLayoutTitles!![position]
        } else super.getPageTitle(position)
    }
}