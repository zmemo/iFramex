package com.memo.ui

import android.app.Activity
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.activity.BaseActivity
import com.memo.tool.ext.onClick
import com.memo.tool.helper.PermissionHelper
import com.memo.tool.helper.QrcodeHelper
import com.memo.tool.helper.toast
import com.memo.ui.qrcode.QrcodeScanActivity
import com.memo.ui.web.WebActivity
import kotlinx.android.synthetic.main.activity_ui.*

@Route(path = RouterPath.Launcher.UiActivity)
class UiActivity : BaseActivity() {

    private val REQUEST_CODE_SCAN = 1

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_ui

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mItemScan.onClick {
            if (PermissionHelper.grantedCamera(mContext)) {
                QrcodeScanActivity.start(mContext, REQUEST_CODE_SCAN)
            }
        }
        mItemWeb.onClick {
            WebActivity.start(mContext, "https://www.baidu.com", "百度一下")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SCAN) {
            val result = QrcodeHelper.obtainQRCode(data)
            toast(result)
        }
    }
}