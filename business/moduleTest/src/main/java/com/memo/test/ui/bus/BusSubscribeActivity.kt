package com.memo.test.ui.bus

import com.memo.base.base.activity.BaseActivity
import com.memo.base.manager.bus.BusManager
import com.memo.base.tool.ext.onClick
import com.memo.base.tool.ext.startActivity
import com.memo.test.R
import kotlinx.android.synthetic.main.activity_bus_subscribe.*

class BusSubscribeActivity : BaseActivity() {

    /**
     * 绑定布局id
     */
    override fun bindLayoutRes() : Int = R.layout.activity_bus_subscribe

    /**
     * 进行初始化操作
     */
    override fun initialize() {
        mBtnBus.onClick { startActivity<BusPostActivity>() }

        BusManager.get().subscribeMain(this) {
            mTv1.text = it.message
            mTv2.text = it.message
        }

    }

    override fun onDestroy() {
        BusManager.get().unregister(this)
        super.onDestroy()
    }
}
