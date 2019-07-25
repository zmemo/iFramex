@file:Suppress("DEPRECATION")

package com.memo.tool.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import androidx.core.app.NotificationCompat

/**
 * title:通知帮助
 * describe:
 *
 * @author zhou
 * @date 2019-05-16 17:44
 */
object NotificationHelper {

    private var sNotificationManager: NotificationManager? = null

    /**
     * 文本消息
     *
     * @param notifyId 消息ID
     * @param title 标题
     * @param summary 内容
     * @param ticker 出现消息时状态栏的提示文字
     * @param pendingIntent 点击后的intent
     */
    @JvmStatic
    fun setMessageNotification(
        context: Context,
        notifyId: Int,
        channelId: String,
        channelName: String,
        smallIconId: Int,
        title: CharSequence,
        summary: CharSequence,
        ticker: CharSequence? = null,
        pendingIntent: PendingIntent? = null
    ) {
        val builder: NotificationCompat.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, channelId)
        } else {
            NotificationCompat.Builder(context)
        }
        builder.setSmallIcon(smallIconId)
            // .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.iframe))
            .setContentTitle(title)
            .setContentText(summary)
            .setAutoCancel(true)
            .setProgress(0, 0, false)
        if (!TextUtils.isEmpty(ticker)) {
            builder.setTicker(ticker)
        }
        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent)
        } else {
            builder.setContentIntent(PendingIntent.getBroadcast(context, 0, Intent(), PendingIntent.FLAG_UPDATE_CURRENT))
        }
        val manager = createNotificationManager(context, channelId, channelName)
        manager.notify(notifyId, builder.build())
    }

    /**
     * 设置进度通知
     *
     * @param notifyId 消息ID
     * @param title 标题
     * @param progress 进度（0-100）
     */
    @JvmStatic
    fun setProgressNotification(context: Context, notifyId: Int, channelId: String, channelName: String, smallIconId: Int, title: CharSequence, progress: Int) {
        setProgressNotification(context, notifyId, channelId, channelName, smallIconId, title, null, progress)
    }

    /**
     * 设置下载进度通知
     *
     * @param notifyId 消息ID
     * @param title 标题
     * @param ticker 出现消息时状态栏的提示文字
     * @param progress 进度（0-100）
     * @param pendingIntent 点击后的intent
     */
    @JvmStatic
    fun setProgressNotification(
        context: Context,
        notifyId: Int,
        channelId: String,
        channelName: String,
        smallIconId: Int,
        title: CharSequence,
        ticker: CharSequence?,
        progress: Int,
        pendingIntent: PendingIntent? = null
    ) {
        val builder: NotificationCompat.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, channelId)
        } else {
            NotificationCompat.Builder(context)
        }
        builder.setSmallIcon(smallIconId)
            // .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable-xhdpi.iframe))
            .setContentTitle(title)
            .setProgress(100, progress, progress == 0)
            .setOngoing(progress < 100)
            .setAutoCancel(progress == 100)
            // 表示只声音提示一次
            .setOnlyAlertOnce(true)
        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent)
        } else {
            builder.setContentIntent(PendingIntent.getBroadcast(context, 0, Intent(), PendingIntent.FLAG_UPDATE_CURRENT))
        }
        if (!TextUtils.isEmpty(ticker)) {
            builder.setTicker(ticker)
        }
        val manager = createNotificationManager(context, channelId, channelName)
        manager.notify(notifyId, builder.build())
    }

    /**
     * 取消通知
     *
     * @param notifyId 通知ID
     */
    @JvmStatic
    fun cancel(notifyId: Int) {
        sNotificationManager?.cancel(notifyId)
    }

    /**
     * 取消所有通知
     */
    @JvmStatic
    fun cancelAll() {
        sNotificationManager?.cancelAll()
    }

    private fun createNotificationManager(context: Context, channelId: String, channelName: String): NotificationManager {
        if (sNotificationManager != null) {
            return sNotificationManager!!
        }
        sNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 适配>=7.0手机通知栏显示问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            sNotificationManager!!.createNotificationChannel(notificationChannel)
        }
        return sNotificationManager!!
    }
}