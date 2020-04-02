package com.memo.ui

import android.app.Activity
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.base.activity.BaseActivity
import com.memo.base.manager.router.RouterPath
import com.memo.tool.ext.onClick
import com.memo.tool.ext.toast
import com.memo.tool.helper.PermissionHelper
import com.memo.tool.helper.QRCodeHelper
import com.memo.ui.qrcode.QRCodeScanActivity
import com.memo.ui.web.WebActivity
import kotlinx.android.synthetic.main.activity_ui.*

/**
 * title:UI模块的启动界面
 * describe:
 *
 * @author memo
 * @date 2019-12-15 20:47
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Launcher.UiActivity)
class UiActivity : BaseActivity() {

	private val REQUEST_CODE_SCAN = 1

	private var isUrlError = true

	/*** 绑定布局id ***/
    override fun bindLayoutRes(): Int = R.layout.activity_ui

	/*** 进行初始化操作 ***/
	override fun initialize() {
		mItemScan.onClick {
            PermissionHelper.grantedCamera(mContext) {
				QRCodeScanActivity.start(mContext, REQUEST_CODE_SCAN)
			}
		}
		mItemWeb.onClick {
			val url = if (isUrlError) "" else "https://www.baidu.com"
			isUrlError = !isUrlError
			WebActivity.start(mContext, url, "百度一下")
		}
	}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SCAN) {
			val result = QRCodeHelper.obtainQRCode(data)
			toast(result)
		}
	}
}