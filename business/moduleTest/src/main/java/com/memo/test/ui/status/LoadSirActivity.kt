package com.memo.test.ui.status

import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.adapter.BaseFragmentPagerAdapter
import com.memo.test.R
import kotlinx.android.synthetic.main.activity_load_sir.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:23
 */
class LoadSirActivity : BaseActivity() {

    override fun bindLayoutRes(): Int = R.layout.activity_load_sir

    override fun initialize() {
        val fragments = arrayListOf(LazyFragment(), LazyFragment(), LazyFragment(), LazyFragment(), LazyFragment())
        val titles = arrayListOf("第一", "第二", "第三", "第四", "第五")
        val mAdapter = BaseFragmentPagerAdapter(supportFragmentManager)
        mAdapter.setData(fragments, titles)
        mViewPager.offscreenPageLimit = titles.size
        mViewPager.adapter = mAdapter
        mTab.setupWithViewPager(mViewPager)
    }
}