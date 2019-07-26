package com.memo.test.download

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.net.Uri
import android.os.Environment
import android.os.Handler
import com.blankj.utilcode.util.AppUtils
import com.memo.tool.app.BaseApp
import java.io.File

/**
 * title:App下载管理
 * describe:
 *
 * @author zhou
 * @date 2019-03-22 10:14
 */
class AppDownloadManager private constructor() {

    private object SingletonHolder {
        val holder = AppDownloadManager()
    }

    companion object {
        fun get() = SingletonHolder.holder
    }

    /*** DownloadManager ***/
    private val mDownloadManager: DownloadManager =
        BaseApp.app.applicationContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    private val mDownLoadChangeObserver: DownloadChangeObserver by lazy {
        DownloadChangeObserver(
            Handler()
        )
    }

    private val mDownloadReceiver: DownloadReceiver = DownloadReceiver()
    private var mUpdateListener: OnUpdateListener? = null
    private var requestId: Long = 0
    private val apkName = AppUtils.getAppName() + ".apk"

    private fun setUpdateListener(mUpdateListener: OnUpdateListener): AppDownloadManager {
        this.mUpdateListener = mUpdateListener
        return this
    }

    fun setUpdateListener(method: (currentByte: Int, totalByte: Int) -> Unit): AppDownloadManager {
        setUpdateListener(object : OnUpdateListener {
            override fun update(currentByte: Int, totalByte: Int) {
                method(currentByte, totalByte)
            }
        })
        return this
    }

    fun downloadApk(apkUrl: String, title: String, desc: String): AppDownloadManager {
        // 设置下载文件
        val apkFile =
            File(
                BaseApp.app.applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                apkName
            )
        // 如果文件已经存在 那么删除原来的文件
        if (apkFile.exists()) {
            apkFile.delete()
        }
        // 设置下载请求
        val request = DownloadManager.Request(Uri.parse(apkUrl))
        // 设置title
        request.setTitle(title)
        // 设置描述
        request.setDescription(desc)
        // 完成后显示通知栏
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        // 设置下载地址和文件名称
        request.setDestinationInExternalFilesDir(
            BaseApp.app.applicationContext,
            Environment.DIRECTORY_DOWNLOADS,
            apkName
        )
        // 设置MimeType
        request.setMimeType("application/vnd.android.package-archive")
        // 获取下载的请求id
        requestId = mDownloadManager.enqueue(request)

        // 设置监听Uri.parse("content://downloads/my_downloads")
        BaseApp.app.applicationContext.contentResolver.registerContentObserver(
            Uri.parse("content://downloads/my_downloads"), true,
            mDownLoadChangeObserver
        )
        // 注册广播，监听APK是否下载完成
        BaseApp.app.applicationContext.registerReceiver(
            mDownloadReceiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
        return this
    }

    /**
     * 取消下载
     */
    fun cancel() {
        mDownloadManager.remove(requestId)
    }

    /**
     * 取消监听下载
     */
    fun onDestroy() {
        BaseApp.app.applicationContext.contentResolver.unregisterContentObserver(
            mDownLoadChangeObserver
        )
        BaseApp.app.applicationContext.unregisterReceiver(mDownloadReceiver)
    }

    private inner class DownloadChangeObserver(handler: Handler) : ContentObserver(handler) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            updateView()
        }
    }

    private fun updateView() {
        var currentByte = 0
        var totalByte = 0
        val query = DownloadManager.Query().setFilterById(requestId)
        mDownloadManager.query(query).use { cursor ->
            if (cursor != null && cursor.moveToFirst()) {
                // 已经下载的字节数
                currentByte =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                // 总需下载的字节数
                totalByte =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
            }
        }

        if (mUpdateListener != null) {
            mUpdateListener!!.update(currentByte, totalByte)
        }
    }

    private inner class DownloadReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val completeDownLoadId =
                intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (completeDownLoadId == requestId) {
                // 开始安装
                AppUtils.installApp(
                    File(
                        context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                        apkName
                    )
                )
            }
        }
    }

    interface OnUpdateListener {
        /**
         * 更新
         *
         * @param currentByte 当前下载字节
         * @param totalByte 总共下载字节
         */
        fun update(currentByte: Int, totalByte: Int)
    }
}