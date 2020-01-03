package com.memo.question.q2

import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.memo.base.base.activity.BaseActivity
import com.memo.question.R

class TwoActivity : BaseActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.iTag("task", "复用")
    }

    /*** 绑定布局id ***/
    override fun bindLayoutRes(): Int = R.layout.activity_two

    /*** 进行初始化操作 ***/
    override fun initialize() {
        LogUtils.iTag("task", "taskId = $taskId")
    }

}
