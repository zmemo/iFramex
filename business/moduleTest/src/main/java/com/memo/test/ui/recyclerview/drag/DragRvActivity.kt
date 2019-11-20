package com.memo.test.ui.recyclerview.drag

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.entity.MultiEntity
import com.memo.tool.helper.toast
import kotlinx.android.synthetic.main.activity_drag_rv.*

class DragRvActivity : BaseActivity() {

    private val url =
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/e993669a22c1469aa9ecd35368107267.jpeg"

    private val mData = arrayListOf<MultiEntity>()

    private val mAdapter by lazy { DragAdapter() }

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_drag_rv

    /*** 进行初始化操作 ***/
    override fun initialize() {
        initData()
        initView()
    }

    private fun initData() {
        with(mData) {
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
            add(MultiEntity("标题", url, 1))
        }
    }

    private fun initView() {


        val callback = ItemDragAndSwipeCallback(mAdapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(mRvList)
        mAdapter.enableSwipeItem()
        mAdapter.enableDragItem(itemTouchHelper)
        mAdapter.setOnItemDragListener(object : OnItemDragListener {
            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                LogUtils.iTag("drag", "onItemDragStart")
            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                LogUtils.iTag("drag", "onItemDragEnd")
            }

            override fun onItemDragMoving(
                source: RecyclerView.ViewHolder?,
                from: Int,
                target: RecyclerView.ViewHolder?,
                to: Int
            ) {
                toast("from $from to $to")
            }
        })

        mRvList.run {
            layoutManager = GridLayoutManager(mContext, 3)
            mAdapter.setNewData(mData)
            adapter = mAdapter
        }

    }
}
