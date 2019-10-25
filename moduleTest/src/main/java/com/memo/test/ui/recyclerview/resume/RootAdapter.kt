package com.memo.test.ui.recyclerview.resume

import android.os.Parcelable
import android.util.SparseArray
import androidx.core.util.set
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.memo.test.R
import com.memo.test.ui.recyclerview.SectionItem
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.widget.recyclerview.StartSnapHelper

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

    private val mStatesSpareArray by lazy { SparseArray<Parcelable?>() }

    /**
     * 转换 数据都不为空
     */
    override fun converts(helper: ViewHolder, item: ArrayList<SectionItem>) {
        val mRvList = helper.getView<RecyclerView>(R.id.mRvList)
        mRvList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        mRvList.onFlingListener = null
        StartSnapHelper().attachToRecyclerView(mRvList)
        val mAdapter = ChildAdapter()
        mAdapter.bindToRecyclerView(mRvList)
        mAdapter.setData(item, mStatesSpareArray[helper.adapterPosition])
        mRvList.adapter = mAdapter
    }

    override fun onViewRecycled(holder: ViewHolder) {
        val position = holder.adapterPosition
        val mRvList: RecyclerView? = holder.getView(R.id.mRvList)
        val layoutManager = mRvList?.layoutManager
        mStatesSpareArray[position] = layoutManager?.onSaveInstanceState()
        super.onViewRecycled(holder)
    }
}