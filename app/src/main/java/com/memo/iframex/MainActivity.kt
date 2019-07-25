package com.memo.iframex

import com.blankj.utilcode.util.ToastUtils
import com.memo.base.manager.bus.BusManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.tool.ext.onClick
import com.memo.tool.preview.ImagePreviewHelper
import com.memo.tool.utils.ClickHelper
import com.memo.tool.utils.ImageLoadHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val imageUrl =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563886048219&di=1743d64858a5494cc20ed9825e594ee9&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F12%2F20160212161339_R8Zuk.jpeg"

    override fun bindLayoutResId(): Int = R.layout.activity_main

    override fun initialize() {
        ImageLoadHelper.loadImage(mContext, imageUrl, mIvPic)
        mIvPic.onClick {
            ImagePreviewHelper.start(mActivity, imageUrl, it)
        }
    }

    override fun finish() {
        if(ClickHelper.isDoubleClickExit) {
            super.finish()
        }
    }

    override fun onDestroy() {
        ToastUtils.cancel()
        super.onDestroy()
    }
}
