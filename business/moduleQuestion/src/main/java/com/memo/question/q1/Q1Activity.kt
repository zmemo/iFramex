package com.memo.question.q1

import com.blankj.utilcode.util.LogUtils
import com.memo.base.base.activity.BaseActivity
import com.memo.question.R
import com.memo.tool.adapter.BaseFragmentPagerAdapter
import com.memo.tool.helper.UUIDHelper
import kotlinx.android.synthetic.main.activity_q1.*

class Q1Activity : BaseActivity() {

    private val mAdapter by lazy { BaseFragmentPagerAdapter(supportFragmentManager) }
    private val titles by lazy { arrayListOf("tab1", "tab2", "tab3", "tab4", "tab5") }
    private val fragments by lazy {
        arrayListOf(Q1Fragment(), Q1Fragment(), Q1Fragment(), Q1Fragment(), Q1Fragment())
    }

    /*** 绑定布局id ***/
    override fun bindLayoutRes(): Int = R.layout.activity_q1

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mAdapter.setData(fragments, titles)
        mViewPager.adapter = mAdapter
        mTab.setViewPager(mViewPager)
        LogUtils.iTag("uuid", UUIDHelper.getUUID())
    }
}
