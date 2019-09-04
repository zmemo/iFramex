package com.memo.tool.dir

import android.os.Environment
import com.memo.tool.app.BaseApp

/**
 * title:本地文件夹地址
 * describe:
 *
 * @author zhou
 * @date 2019-07-23 15:26
 */
object LocalDir{
    val DIR_COMPRESS: String = "${BaseApp.app.applicationContext.filesDir.absolutePath}/compress"
    val NOMEDIA_COMPRESS: String = "$DIR_COMPRESS/.nomedia"

    val DIR_CAPTURE: String = "${BaseApp.app.applicationContext.filesDir.absolutePath}/capture"
    val NOMEDIA_CAPTURE: String = "$DIR_CAPTURE/.nomedia"

    val DIR_CROP: String = "${BaseApp.app.applicationContext.filesDir.absolutePath}/crop"
    val NOMEDIA_CROP: String = "$DIR_CROP/.nomedia"

    val DIR_VIDEO: String = "${BaseApp.app.applicationContext.filesDir.absolutePath}/video"
    val NOMEDIA_VIDEO: String = "$DIR_VIDEO/.nomedia"

    val DIR_EXCEPTION_LOG: String =
        "${Environment.getExternalStorageDirectory().absolutePath}/${BaseApp.app.applicationContext.packageName}/Log"
}