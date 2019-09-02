package com.memo.test.recyclerview.grid

import androidx.recyclerview.widget.GridLayoutManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.recyclerview.GridMulti
import com.memo.test.recyclerview.MultiEntity
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import kotlinx.android.synthetic.main.activity_grid_multi.*

class GridMultiActivity : BaseActivity() {

    private val url =
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/e993669a22c1469aa9ecd35368107267.jpeg"

    private val mData = arrayListOf<MultiEntity>()
    private val mAdapter by lazy { GridMultiAdapter() }
    private val mStickyDecoration by lazy {
        PinnedHeaderItemDecoration.Builder(GridMulti.TYPE_TITLE).create()
    }

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_grid_multi

    /*** 进行初始化操作 ***/
    override fun initialize() {
        initData()
        initView()
    }

    private fun initData() {
        with(mData) {
            add(MultiEntity("", url, GridMulti.TYPE_BANNER))
            add(MultiEntity("推荐歌单", "", GridMulti.TYPE_TITLE))
            add(MultiEntity("[华语私人定制]你爱的好歌都在这儿", url, GridMulti.TYPE_LIST))
            add(MultiEntity("90后青春纪念手册", url, GridMulti.TYPE_LIST))
            add(MultiEntity("让你耳朵怀孕的英文歌", url, GridMulti.TYPE_LIST))
            add(MultiEntity("独家放送", "", GridMulti.TYPE_TITLE))
            add(MultiEntity("传奇名作Top10", url, GridMulti.TYPE_POSTER))
            add(MultiEntity("不一样的《年少有为》", url, GridMulti.TYPE_POSTER))
            add(MultiEntity("最新音乐", "", GridMulti.TYPE_TITLE))
            add(MultiEntity("富士山下", url, GridMulti.TYPE_SONG))
            add(MultiEntity("最佳损友", url, GridMulti.TYPE_SONG))
            add(MultiEntity("好久不见", url, GridMulti.TYPE_SONG))
            add(MultiEntity("一丝不挂", url, GridMulti.TYPE_SONG))
            add(MultiEntity("孤独患者", url, GridMulti.TYPE_SONG))
            add(MultiEntity("落花流水", url, GridMulti.TYPE_SONG))
        }
    }

    private fun initView() {
        mRvList.run {
            //设置一排的展示个数
            layoutManager = GridLayoutManager(context, 6)
            //添加粘性头
            addItemDecoration(mStickyDecoration)
            //设置数量  展示数量 = 6 / spanSizeLookup
            mAdapter.setSpanSizeLookup { _, position ->
                when (mAdapter.data[position].type) {
                    GridMulti.TYPE_TITLE,
                    GridMulti.TYPE_SONG -> 6
                    GridMulti.TYPE_POSTER -> 3
                    GridMulti.TYPE_LIST -> 2
                    else -> 6
                }
            }
            //设置数据
            mAdapter.setNewData(mData)
            //设置adapter
            adapter = mAdapter
        }
    }
}
