package com.memo.tool.helper

import android.os.Handler
import android.os.HandlerThread
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.app.BaseApp


/**
 * title:清除所有图片占用的内存
 * describe:
 *
 * @author zhou
 * @date 2019-09-04 09:44
 */
object OOMHelper {

    /**
     * 开启低内存监测，如果低内存了，作出相应的反应
     */
    fun startMonitorLowMemory() {
        val delayMillis: Long = 1000 * 60
        val thread = HandlerThread("MonitorLowMemoryThread")
        thread.start()
        val lowMemoryMonitorHandler = Handler(thread.looper)
        lowMemoryMonitorHandler.postDelayed(object : Runnable {
            override fun run() {
                val totalMemory = Runtime.getRuntime().totalMemory()
                val freeMemory = Runtime.getRuntime().freeMemory()
                val usedMemory = totalMemory - freeMemory
                val maxMemory = Runtime.getRuntime().maxMemory()
                //如果可用内存超过最大内存的80% 那么就把Glide的图片内存缓存清除
                val shouldCleanMemory = usedMemory.toDouble().compareTo(maxMemory * 0.8) == 1
                LogUtils.iTag(
                    "Memory", "MaxMemory = $maxMemory " +
                            "TotalMemory = $totalMemory " +
                            "UsedMemory = $usedMemory " +
                            "FreeMemory = $freeMemory " +
                            "shouldCleanMemory = $shouldCleanMemory"
                )
                if (shouldCleanMemory) {
                    ImageLoadHelper.clearMemoryCache(BaseApp.app.applicationContext)
                }
                lowMemoryMonitorHandler.postDelayed(this, delayMillis)
            }
        }, delayMillis)
    }

    /**
     * 在onDestroy的时候将界面上的所有图片内存清除
     * @param root View? 视图
     */
    fun onDestroy(root: View?) {
        root?.background = null
        if (root is ViewGroup) {
            val childCount = root.childCount
            for (i in 0 until childCount) {
                val child: View? = root.getChildAt(i)
                if (child is ImageView) {
                    child.setImageDrawable(null)
                }
                onDestroy(child)
            }
        }
    }

}