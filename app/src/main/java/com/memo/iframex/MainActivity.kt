package com.memo.iframex

import com.memo.base.manager.router.RouterManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.tool.ext.onClick
import com.memo.tool.utils.ClickHelper
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

    override fun initialize() {
        mBtnIntent.onClick {
            RouterManager.get().startTestActivity()
        }
    }

    override fun finish() {
        if (ClickHelper.isDoubleClickExit) {
            super.finish()
        }
    }
}
