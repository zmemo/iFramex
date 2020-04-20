package com.memo.iframex

import android.annotation.SuppressLint
import com.memo.base.base.activity.BaseActivity
import com.memo.base.manager.init.InitManager
import com.memo.base.manager.router.RouterManager
import com.memo.tool.ext.onClick
import com.memo.tool.ext.toastCancel
import com.memo.tool.helper.ClickHelper
import com.memo.tool.helper.MediaHelper
import com.memo.tool.helper.OOMHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * title:主界面
 * describe:
 *
 * @author zhou
 * @date 2019-07-25 17:14
 */
class MainActivity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_main

	@SuppressLint("SetTextI18n")
	override fun initialize() {
		//在启动页进行的初始化操作
		InitManager.get().initInSplash()
		//开启内存监听
		OOMHelper.startMonitorLowMemory()
		MediaHelper.createLocalDir()
		mBtnTest.onClick {
			RouterManager.startLauncherTest()
		}
		mBtnUi.onClick {
			RouterManager.startLauncherUi()
		}
	}

	override fun onBackPressed() {
		if (ClickHelper.isDoubleClickExit) {
			toastCancel()
			super.onBackPressed()
		}
	}
}
