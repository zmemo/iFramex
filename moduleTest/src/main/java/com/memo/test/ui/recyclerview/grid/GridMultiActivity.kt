package com.memo.test.ui.recyclerview.grid

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.tool.DataFactory
import com.memo.test.ui.recyclerview.GridMulti
import com.memo.test.ui.recyclerview.MultiEntity
import com.memo.tool.handler.WeakHandler
import com.memo.tool.helper.GsonHelper
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import kotlinx.android.synthetic.main.activity_grid_multi.*

/**
 * title:网格多布局
 * describe: 利用网格布局来进行复杂界面的展示
 *
 * @author zhou
 * @date 2019-09-12 13:58
 *
 * Talk is cheap. Show me the code.
 */
class GridMultiActivity : BaseActivity() {

    private var isChanged = true

    private val mAdapter by lazy { GridMultiAdapter() }
    private val mStickyDecoration by lazy {
        PinnedHeaderItemDecoration.Builder(GridMulti.TYPE_TITLE).create()
    }
    private val mHandler = WeakHandler()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_grid_multi

    /*** 进行初始化操作 ***/
    override fun initialize() {
        initView()
        initListener()
        start()
    }

    private fun initView() {
        mRvList.run {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            //设置一排的展示个数
            layoutManager = GridLayoutManager(context, 6)
            //添加粘性头
            addItemDecoration(mStickyDecoration)
            //设置数量  展示数量 = 6 / spanSizeLookup
            mAdapter.setSpanSizeLookup { _, position ->
                when (mAdapter.data[position].type) {
                    GridMulti.TYPE_TITLE,
                    GridMulti.TYPE_BANNER,
                    GridMulti.TYPE_SONG_RIGHT,
                    GridMulti.TYPE_SONG_TOP -> 6
                    GridMulti.TYPE_POSTER -> 3
                    GridMulti.TYPE_LIST -> 2
                    else -> 6
                }
            }
            //设置adapter
            adapter = mAdapter
        }
    }

    private fun initListener() {
        mRefreshLayout.setOnRefreshListener {
            mHandler.postDelayed({
                //更新数据源
                val data = if (isChanged) {
                    GsonHelper.parse2List<MultiEntity>(DataFactory.provideHomeJson1())
                } else {
                    GsonHelper.parse2List<MultiEntity>(DataFactory.provideHomeJson2())
                }
                isChanged = !isChanged
                mAdapter.setNewData(data)
            }, 400)
            mRefreshLayout.finishRefresh(400)
        }
    }

    private fun start() {
        mRefreshLayout.autoRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.onDestroy()
    }
}
