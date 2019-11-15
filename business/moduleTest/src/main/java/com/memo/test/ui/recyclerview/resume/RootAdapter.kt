package com.memo.test.ui.recyclerview.resume

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.memo.test.R
import com.memo.test.ui.recyclerview.SectionItem
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-25 15:08
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RootAdapter : BaseRecyclerAdapter<ArrayList<SectionItem>>(R.layout.item_resume_root) {

    /**
     * 转换 数据都不为空
     */
    override fun converts(helper: ViewHolder, item: ArrayList<SectionItem>) {
        val mRvList = helper.getView<RecyclerView>(R.id.mRvList)
        mRvList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        mRvList.onFlingListener = null
        PagerSnapHelper().attachToRecyclerView(mRvList)

        val mAdapter = ChildAdapter()
        mAdapter.bindToRecyclerView(mRvList)
        mAdapter.setData(item, mStateCache[helper.adapterPosition])
        mRvList.adapter = mAdapter
    }

    override fun bindSaveStateChildRecyclerView(holder: ViewHolder): RecyclerView? {
        return holder.getView(R.id.mRvList)
    }
}