package com.memo.test.ui.status

import androidx.fragment.app.Fragment
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.adapter.BaseFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_load_sir.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:23
 */
class LoadSirActivity : BaseActivity() {
	
	override fun bindLayoutRes() : Int = R.layout.activity_load_sir

    override fun initialize() {
        val fragments = arrayListOf(LazyFragment(), LazyFragment(), LazyFragment(), LazyFragment(), LazyFragment())
        val titles = arrayOf("第一", "第二", "第三", "第四", "第五")
        val mAdapter = BaseFragmentPagerAdapter<Fragment>(supportFragmentManager)
        mAdapter.setData(fragments, titles)
        mViewPager.offscreenPageLimit = titles.size
        mViewPager.adapter = mAdapter
        mTab.setupWithViewPager(mViewPager)
    }
}