package com.memo.question.q1


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.base.fragment.BaseFragment
import com.memo.question.R
import com.memo.question.utils.DataFactory
import com.memo.question.utils.Entity
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.ext.value
import com.memo.tool.helper.ImageLoadHelper
import kotlinx.android.synthetic.main.fragment_q1.*


class Q1Fragment : BaseFragment() {

    /*** 绑定布局 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_q1

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        // 注意：RecyclerView内部和LinearLayoutManager内部都实现了onSaveInstanceState onRestoreInstanceState 不需要人工保存了
        mRvList.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
        mAdapter.setNewData(DataFactory.getData(100))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //保存会销毁的数据
        outState.putString("search", mEtSearch.value)
        outState.putBoolean("isChecked", mCheck.isChecked)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        //重置被销毁的数据
        savedInstanceState?.let {
            mEtSearch.value = it.getString("search") ?: ""
            mCheck.isChecked = it.getBoolean("isChecked", false)
        }
    }

    private val mAdapter = object : BaseRecyclerAdapter<Entity>(R.layout.item_q1) {
        override fun converts(helper: ViewHolder, item: Entity) {
            ImageLoadHelper.loadRoundImage(mContext, item.images, 10, R.drawable.ic_empty_zhihu, R.drawable.ic_empty_zhihu, helper.getView(R.id.mIvCover))
            helper.setText(R.id.mTvTitle, item.title)
                .setText(R.id.mTvContent, item.content)
        }
    }


}
