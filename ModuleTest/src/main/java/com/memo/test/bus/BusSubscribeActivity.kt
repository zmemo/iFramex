package com.memo.test.bus

import com.memo.base.manager.bus.BusManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.ext.onClick
import com.memo.tool.ext.startActivity
import kotlinx.android.synthetic.main.activity_bus_subscribe.*

class BusSubscribeActivity : BaseActivity() {

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_bus_subscribe

    /**
     * 进行初始化操作
     */
    override fun initialize() {
        mBtnBus.onClick { startActivity<BusPostActivity>() }

        BusManager.get().subscribe(this) {
            mTvBus.text = it
        }

    }

    override fun onDestroy() {
        BusManager.get().unregister(this)
        super.onDestroy()
    }
}
