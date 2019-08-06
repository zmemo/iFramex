package com.memo.test.recyclerview

import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.recyclerview.multi.MultiRecyclerActivity
import com.memo.test.recyclerview.section.SectionRecyclerActivity
import com.memo.tool.ext.onClick
import com.memo.tool.ext.startActivity
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : BaseActivity() {

    override fun bindLayoutResId(): Int = R.layout.activity_recycler_view

    override fun initialize() {
        mBtnMulti.onClick {
            startActivity<MultiRecyclerActivity>()
        }
        mBtnSection.onClick {
            startActivity<SectionRecyclerActivity>()
        }
    }
}
