package com.memo.tool.utils

import android.app.ActivityManager
import android.content.Context

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-18 16:51
 */
object ProcessHelper {

    fun inMainProcess(context: Context, packageName: String): Boolean {
        val pid = android.os.Process.myPid()
        val mManager: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = mManager.runningAppProcesses
        for (process in runningAppProcesses) {
            if (process.pid == pid && process.processName == packageName) {
                return true
            }
        }
        return false
    }
}