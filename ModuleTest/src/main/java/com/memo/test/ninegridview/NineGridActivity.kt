package com.memo.test.ninegridview

import androidx.recyclerview.widget.GridLayoutManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.preview.ImagePreviewHelper
import kotlinx.android.synthetic.main.activity_nine_grid.*

class NineGridActivity : BaseActivity() {

    private val images = arrayListOf(
        "",
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/e993669a22c1469aa9ecd35368107267.jpeg",
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/c1101b8adca244edac8b0e1d1c6a0e7b.jpeg",
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/7bd4123abc2a48a18104431edcf21d15.jpeg",
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/980bf55fc2454833be8d76a1f84bbac2.jpeg",
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/5d031942cd424efdb60c95ed2b4730b0.jpeg",
        "http://5b0988e595225.cdn.sohucs.com/images/20180428/c63a46f0fa0646a5a12db8c3d34295c4.gif",
        "http://b-ssl.duitang.com/uploads/item/201709/21/20170921195519_zK4Vi.thumb.700_0.gif",
        "http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20180705/294d5a4aebf8410fa7ddf3ac30c004c8.gif"
    )

    private val mGridManager by lazy { GridLayoutManager(this, 3) }
    private val mAdapter by lazy { NineGridViewAdapter() }

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_nine_grid

    /**
     * 进行初始化操作
     */
    override fun initialize() {
        mRvList.run {
            setHasFixedSize(true)
            layoutManager = mGridManager
            mAdapter.setNewData(images)
            adapter = mAdapter
            mAdapter.setOnItemClickListener { _, _, position ->
                ImagePreviewHelper.start(mActivity, mGridManager, R.id.mImg, images, position)
            }
        }
    }
}
