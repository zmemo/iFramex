@file:Suppress("DEPRECATION")

package com.memo.base.tool.helper

import android.app.PendingIntent
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.NotificationUtils

/**
 * title:通知帮助
 * describe:
 *
 * @author zhou
 * @date 2019-05-16 17:44
 */
object NotificationHelper {


    /**
     * 判断是否开启应用通知权限
     * @return Boolean
     */
    @JvmStatic
    fun isNotificationEnable(): Boolean = NotificationUtils.areNotificationsEnabled()


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
    fun sendMessageNotification(
        notifyId: Int,
        channelId: String,
        channelName: String,
        smallIconId: Int,
        title: CharSequence,
        content: CharSequence,
        @Nullable pendingIntent: PendingIntent? = null
    ) {
        val config = NotificationUtils.ChannelConfig(
            channelId,
            channelName,
            NotificationUtils.IMPORTANCE_HIGH
        )
        NotificationUtils.notify(notifyId, config) { param ->
            param.setSmallIcon(smallIconId)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
        }
    }

    /**
     * 设置下载进度通知
     *
     * @param notifyId 消息ID
     * @param channelId 渠道id
     * @param channelName 渠道名称 这个在app设置界面可以看到一般为 这条通知的作用 比如应用更新 消息通知
     * @param smallIconId 通知栏小图标
     * @param title 标题
     * @param ticker 出现消息时状态栏的提示文字
     * @param progress 进度（0-100）
     * @param pendingIntent 点击后的intent
     */
    @JvmStatic
    fun sendProgressNotification(
        notifyId: Int,
        channelId: String,
        channelName: String,
        smallIconId: Int,
        title: CharSequence,
        @IntRange(from = 0, to = 100) progress: Int,
        @Nullable pendingIntent: PendingIntent? = null
    ) {
        val config = NotificationUtils.ChannelConfig(
            channelId,
            channelName,
            NotificationUtils.IMPORTANCE_HIGH
        )
        NotificationUtils.notify(notifyId, config) { param ->
            param.setSmallIcon(smallIconId)
                .setContentTitle(title)
	            .setOnlyAlertOnce(true)
                .setProgress(100, progress, false)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
        }
    }

    /**
     * 取消通知
     *
     * @param notifyId 通知ID
     */
    @JvmStatic
    fun cancel(notifyId: Int) {
        NotificationUtils.cancel(notifyId)
    }

    /**
     * 取消所有通知
     */
    @JvmStatic
    fun cancelAll() {
        NotificationUtils.cancelAll()
    }
}