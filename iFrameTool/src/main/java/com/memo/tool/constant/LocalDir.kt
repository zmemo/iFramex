package com.memo.tool.constant

import android.os.Environment
import com.blankj.utilcode.util.Utils

/**
 * title:本地文件夹地址
 * describe:
 *
 * @author zhou
 * @date 2019-07-23 15:26
 */
object LocalDir{
    val DIR_COMPRESS: String = "${Utils.getApp().applicationContext.filesDir.absolutePath}/compress"
    val NOMEDIA_COMPRESS: String = "$DIR_COMPRESS/.nomedia"

    val DIR_CAPTURE: String = "${Utils.getApp().applicationContext.filesDir.absolutePath}/capture"
    val NOMEDIA_CAPTURE: String = "$DIR_CAPTURE/.nomedia"

    val DIR_CROP: String = "${Utils.getApp().applicationContext.filesDir.absolutePath}/crop"
    val NOMEDIA_CROP: String = "$DIR_CROP/.nomedia"

    val DIR_VIDEO: String = "${Utils.getApp().applicationContext.filesDir.absolutePath}/video"
    val NOMEDIA_VIDEO: String = "$DIR_VIDEO/.nomedia"

    val DIR_EXCEPTION_LOG: String = "${Environment.getExternalStorageDirectory().absolutePath}/${Utils.getApp().applicationContext.packageName}/Log"
}