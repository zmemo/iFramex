package com.memo.test.ui.ninegridview

import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.preview.ImagePreviewHelper
import com.memo.test.R
import com.memo.test.tool.DataFactory
import kotlinx.android.synthetic.main.activity_nine_grid.*

class NineGridActivity : BaseActivity() {

    private val images = DataFactory.provideImages()

    private val mGridManager by lazy { GridLayoutManager(this, 3) }
    private val mAdapter by lazy { NineGridViewAdapter() }

    /**
     * 绑定布局id
     */
    override fun bindLayoutRes() : Int = R.layout.activity_nine_grid

    /**
     * 进行初始化操作
     */
    override fun initialize() {
        mRvNineGridView.run {
            setHasFixedSize(true)
            layoutManager = mGridManager
            mAdapter.setNewData(images)
            adapter = mAdapter
            mAdapter.setOnItemClickListener { _, _, position ->
                LogUtils.iTag("aaa", "aaa")
                ImagePreviewHelper.start(mContext, mGridManager, R.id.mImg, images, position)
            }
        }
    }
}
