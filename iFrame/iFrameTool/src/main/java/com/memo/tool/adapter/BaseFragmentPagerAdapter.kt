package com.memo.tool.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * title:ViewPager和Fragment结合的Adapter
 * describe:
 * 注意使用情况添加了behavior，当前不可见的Fragment会执行生命周期到onResume之前（不执行onResume），可以作为懒加载使用
 *
 * @author zhou
 * @date 2019-09-26 15:41
 *
 * Talk is cheap, Show me the code.
 */
@SuppressLint("WrongConstant")
class BaseFragmentPagerAdapter<T : Fragment>(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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