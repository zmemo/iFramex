package com.memo.test.ui.recyclerview.multi

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.ui.recyclerview.Multi
import com.memo.test.ui.recyclerview.MultiEntity
import com.memo.widget.recyclerview.RecyclerViewDivider
import kotlinx.android.synthetic.main.activity_multi_recycler.*

class MultiRecyclerActivity : BaseActivity() {

    private val url =
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/e993669a22c1469aa9ecd35368107267.jpeg"
    private val mAdapter = MultiAdapter()
    private val mData = arrayListOf<MultiEntity>()
    private val mDecoration by lazy { RecyclerViewDivider.Builder(mContext).setHeight(20f).build() }

    override fun bindLayoutResId(): Int = R.layout.activity_multi_recycler

    override fun initialize() {
        initData()
        initView()
    }

    private fun initData() {
        with(mData) {
            add(MultiEntity("文本类型", "", Multi.TYPE_TEXT))
            add(MultiEntity("", url, Multi.TYPE_IMG))
            add(MultiEntity("图文混合类型", url, Multi.TYPE_TEXT_IMG))
        }
    }

    private fun initView() {
        mRvMultiList.run {
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(mDecoration)
            mAdapter.setNewData(mData)
            adapter = mAdapter
        }
    }


}
