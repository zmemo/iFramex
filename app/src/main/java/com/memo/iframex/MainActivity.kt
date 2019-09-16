package com.memo.iframex

import android.annotation.SuppressLint
import com.memo.base.manager.router.RouterManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.tool.ext.onClick
import com.memo.tool.helper.ClickHelper
import com.memo.tool.helper.OOMHelper
import com.memo.tool.helper.toastCancel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * title:主界面
 * describe:
 *
 * @author zhou
 * @date 2019-07-25 17:14
 */
class MainActivity : BaseActivity() {

    override fun bindLayoutResId(): Int = R.layout.activity_main

    @SuppressLint("SetTextI18n")
    override fun initialize() {
        //开启内存监听
        OOMHelper.startMonitorLowMemory()

        mBtnTest.onClick {
            RouterManager.get().startLauncherTest()
        }
        mBtnUi.onClick {
            RouterManager.get().startLauncherUi()
        }
    }

    override fun finish() {
        if (ClickHelper.isDoubleClickExit) {
            toastCancel()
            super.finish()
        }
    }
}
