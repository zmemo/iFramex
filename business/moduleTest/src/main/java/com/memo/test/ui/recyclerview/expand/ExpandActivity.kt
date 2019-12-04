package com.memo.test.ui.recyclerview.expand

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.entity.LevelContent
import com.memo.test.entity.LevelTitle
import kotlinx.android.synthetic.main.activity_expand.*

class ExpandActivity : BaseActivity() {

    private val mAdapter = ExpandAdapter()

    private val mData = arrayListOf<MultiItemEntity>()

    /*** 绑定布局id ***/
    override fun bindLayoutRes() : Int = R.layout.activity_expand

    /*** 进行初始化操作 ***/
    override fun initialize() {
        initData()
        initView()
        start()
    }

    private fun initData() {
        for (i in 0..20) {
            val levelTitle = LevelTitle()
            for (j in 0..10) {
                levelTitle.addSubItem(LevelContent())
            }
            mData.add(levelTitle)
        }
    }

    private fun initView() {
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    private fun start() {
        mAdapter.setNewData(mData)
    }
}
