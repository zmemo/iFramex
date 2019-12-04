package com.memo.test.ui.bus

import com.memo.base.manager.bus.BusManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_bus_post.*

class BusPostActivity : BaseActivity() {
    /**
     * 绑定布局id
     */
    override fun bindLayoutRes() : Int = R.layout.activity_bus_post

    /**
     * 进行初始化操作
     */
    override fun initialize() {
        mBtnBus.onClick {
            val message = "这是${javaClass.simpleName}传递的数据"
            BusManager.get().postMain(message)
            finish()
        }
    }
}
