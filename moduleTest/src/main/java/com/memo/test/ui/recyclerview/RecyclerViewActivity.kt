package com.memo.test.ui.recyclerview

import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.ui.recyclerview.drag.DragRvActivity
import com.memo.test.ui.recyclerview.expand.ExpandActivity
import com.memo.test.ui.recyclerview.grid.GridMultiActivity
import com.memo.test.ui.recyclerview.multi.MultiRecyclerActivity
import com.memo.test.ui.recyclerview.resume.RecyclerViewResumeActivity
import com.memo.test.ui.recyclerview.section.SectionRecyclerActivity
import com.memo.tool.ext.onClick
import com.memo.tool.ext.startActivity
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : BaseActivity() {

    override fun bindLayoutResId(): Int = R.layout.activity_recycler_view

    override fun initialize() {
        mBtnMulti.onClick {
            startActivity<MultiRecyclerActivity>()
        }
        mBtnGrid.onClick {
            startActivity<GridMultiActivity>()
        }
        mBtnSection.onClick {
            startActivity<SectionRecyclerActivity>()
        }
        mBtnDrag.onClick {
            startActivity<DragRvActivity>()
        }
        mBtnExpand.onClick {
            startActivity<ExpandActivity>()
        }
        mBtnResume.onClick {
            startActivity<RecyclerViewResumeActivity>()
        }
    }
}
