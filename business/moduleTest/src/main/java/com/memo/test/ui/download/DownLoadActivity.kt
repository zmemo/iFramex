package com.memo.test.ui.download

import com.memo.base.base.activity.BaseActivity
import com.memo.test.R
import com.memo.test.ui.api.Api
import com.memo.tool.dir.LocalDir
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_down_load.*

class DownLoadActivity : BaseActivity() {
	/**
	 * 绑定布局id
	 */
	override fun bindLayoutRes(): Int = R.layout.activity_down_load

	/**
	 * 进行初始化操作
	 */
	override fun initialize() {

		mBtnService.onClick {
			DownloadService.start(Api.DownUrl, LocalDir.CACHE_DIR_FILE, "temp.apk")
		}

	}

}
