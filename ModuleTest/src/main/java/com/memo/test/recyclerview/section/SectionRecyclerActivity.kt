package com.memo.test.recyclerview.section

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.recyclerview.SectionItem
import com.memo.test.recyclerview.Sections
import kotlinx.android.synthetic.main.activity_section_recycler.*

class SectionRecyclerActivity : BaseActivity() {

    private val url =
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/e993669a22c1469aa9ecd35368107267.jpeg"

    private val mAdapter by lazy { SectionAdapter() }

    private val mData = arrayListOf<Sections>()

    override fun bindLayoutResId(): Int = R.layout.activity_section_recycler

    override fun initialize() {
        initData()
        initView()
    }

    private fun initData() {
        with(mData) {
            add(Sections(true, "这是标题1"))
            add(Sections(SectionItem("内容1-1", url)))
            add(Sections(SectionItem("内容1-2", url)))
            add(Sections(SectionItem("内容1-3", url)))
            add(Sections(SectionItem("内容1-4", url)))
            add(Sections(SectionItem("内容1-5", url)))
            add(Sections(true, "这是标题2"))
            add(Sections(SectionItem("内容2-1", url)))
            add(Sections(SectionItem("内容2-2", url)))
            add(Sections(SectionItem("内容2-3", url)))
            add(Sections(SectionItem("内容2-4", url)))
            add(Sections(SectionItem("内容2-5", url)))
            add(Sections(true, "这是标题3"))
            add(Sections(SectionItem("内容3-1", url)))
            add(Sections(SectionItem("内容3-2", url)))
            add(Sections(SectionItem("内容3-3", url)))
            add(Sections(SectionItem("内容3-4", url)))
            add(Sections(SectionItem("内容3-5", url)))
        }
    }

    private fun initView() {
        mRvSectionList.run {
            layoutManager = LinearLayoutManager(mContext)
            mAdapter.setNewData(mData)
            adapter = mAdapter
        }
    }


}
