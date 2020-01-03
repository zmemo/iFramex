package com.memo.question.q2

import com.blankj.utilcode.util.LogUtils
import com.memo.base.base.activity.BaseActivity
import com.memo.question.R
import com.memo.tool.ext.onClick
import com.memo.tool.ext.startActivity
import com.memo.tool.lifecycle.LifecycleHandler
import kotlinx.android.synthetic.main.activity_one.*

class OneActivity : BaseActivity() {

    private val mHandler = LifecycleHandler(this) {
        LogUtils.iTag("handler", it.what)
    }


    /*** 绑定布局id ***/
    override fun bindLayoutRes(): Int = R.layout.activity_one

    /*** 进行初始化操作 ***/
    override fun initialize() {
        LogUtils.iTag("task", "taskId = $taskId")
        mBtnIntent.onClick {
            startActivity<TwoActivity>()
            startActivity<TwoActivity>()
            startActivity<TwoActivity>()
        }
        LogUtils.iTag("handler", System.currentTimeMillis() / 1000)
        mHandler.postDelayed({
            //9秒后打印
            LogUtils.iTag("handler", "5000 - " + System.currentTimeMillis() / 1000)
        }, 5000)
        mHandler.postDelayed({
            //4s后打印
            LogUtils.iTag("handler", "1000 - " + System.currentTimeMillis() / 1000)
            Thread.sleep(5000)
        }, 4000)
    }

}
