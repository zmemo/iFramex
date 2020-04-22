package com.memo.test.ui.download

import android.app.IntentService
import android.content.Intent
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.memo.base.tool.app.BaseApp
import com.memo.base.tool.ext.io2Main
import com.memo.base.tool.helper.NotificationHelper
import com.memo.test.ui.api.RetrofitClient
import com.memo.test.ui.config.TestConfig
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.File

/**
 * title:   全局下载的service
 * tip:     传入下载地址 本地存储文件夹 本地存储文件名
 *
 * @author zhou
 * @date 2018-09-28 下午2:16
 */
class DownloadService : IntentService("DownloadService") {

    private var currentProgress: Int = 0

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val url = it.getStringExtra(DownUrl)
            val path = it.getStringExtra(DownPath)
            val name = it.getStringExtra(DownName)
            LogUtils.iTag("Down", "url = $url\npath = $path\nname = $name")
            if (!url.isNullOrEmpty() && !path.isNullOrEmpty() && !name.isNullOrEmpty()) {
                if (!File(path).exists()) {
                    File(path).mkdirs()
                }
                val observer = DownObserver()
                RetrofitClient.mDownApi
                    .download(url)
                    .map { response -> observer.saveFile(response, path, name) }
                    .io2Main()
                    .subscribe(object : Observer<File> {

                        override fun onSubscribe(d: Disposable) {
                            observer.onSubscribe(d)
                            ToastUtils.showShort("开始下载")
                        }

                        override fun onNext(file: File) {
                            observer.onDownLoadSuccess(file)
                        }

                        override fun onError(e: Throwable) {
                            observer.onDownLoadFail(e)
                        }

                        override fun onComplete() {
                            observer.onComplete()
                        }
                    })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.iTag("Down", "退出下载服务")
    }

    /**
     * 下载回调
     */
    inner class DownObserver : AbstractDownLoadObserver() {

        override fun onDownLoadSuccess(file: File) {
            LogUtils.iTag("Down", "完成")
	        // 最后发送一次 避免网速太快导致通知还没有变化
	        NotificationHelper.sendProgressNotification(
		        TestConfig.DOWNLOAD_NOTIFY_ID,
		        TestConfig.DOWNLOAD_CHANNEL_ID,
		        TestConfig.DOWNLOAD_CHANNEL_NAME,
		        android.R.drawable.ic_dialog_dialer,
		        "应用更新 - 100%",
		        100
	        )
            AppUtils.installApp(file)
        }

        override fun onDownLoadFail(throwable: Throwable) {
            LogUtils.iTag("Down", "失败${throwable.message}")
        }

        override fun onProgress(progress: Int, total: Long) {
            if (progress > currentProgress) {
                currentProgress = progress
                LogUtils.iTag("Down", "下载中:$currentProgress")
                // 这里可以对系统通知栏进行更新
                NotificationHelper.sendProgressNotification(
                    TestConfig.DOWNLOAD_NOTIFY_ID,
                    TestConfig.DOWNLOAD_CHANNEL_ID,
                    TestConfig.DOWNLOAD_CHANNEL_NAME,
                    android.R.drawable.ic_dialog_dialer,
                    "应用更新 - $progress%",
                    progress
                )
            }
        }

        override fun onSubscribe(disposable: Disposable) {

        }
    }

    companion object {

        const val DownUrl = "DOWN_URL"
        const val DownName = "DOWN_NAME"
        const val DownPath = "DOWN_PATH"

        fun start(url: String, path: String, name: String) {
            val context = BaseApp.app.applicationContext
            context.startService(
                Intent(context, DownloadService::class.java)
                    .putExtra(DownUrl, url)
                    .putExtra(DownPath, path)
                    .putExtra(DownName, name)
            )
        }
    }
}
