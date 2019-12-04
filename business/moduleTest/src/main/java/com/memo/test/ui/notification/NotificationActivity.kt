package com.memo.test.ui.notification

import com.blankj.utilcode.util.LogUtils
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.ui.config.TestConfig
import com.memo.tool.ext.io2MainLifecycle
import com.memo.tool.ext.onClick
import com.memo.tool.helper.NotificationHelper
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_notification.*
import java.util.concurrent.TimeUnit

class NotificationActivity : BaseActivity() {
	
	
	override fun bindLayoutRes() : Int = R.layout.activity_notification

    override fun initialize() {
        mBtnMessage.onClick {
            NotificationHelper.sendMessageNotification(
                TestConfig.MESSAGE_NOTIFY_ID,
                TestConfig.MESSAGE_CHANNEL_ID,
                TestConfig.MESSAGE_CHANNEL_NAME,
                android.R.drawable.ic_dialog_dialer,
                "测试标题",
                "测试内容",
                "IFrame"
            )
        }

        mBtnProgress.onClick { _ ->
            Observable.interval(0, 100, TimeUnit.MILLISECONDS)
                .take(101)
                .io2MainLifecycle(mLifecycleOwner)
                .subscribe {
                    LogUtils.iTag("progress", it)
                    NotificationHelper.sendProgressNotification(
                        TestConfig.PROGRESS_NOTIFY_ID,
                        TestConfig.PROGRESS_CHANNEL_ID,
                        TestConfig.PROGRESS_CHANNEL_NAME,
                        android.R.drawable.ic_dialog_dialer,
                        "下载进度 $it%",
                        "iFrame",
                        it.toInt()
                    )
                }
        }

        mBtnCancel.onClick {
            NotificationHelper.cancelAll()
        }
    }
}
