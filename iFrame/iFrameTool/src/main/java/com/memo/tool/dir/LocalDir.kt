package com.memo.tool.dir

import android.os.Environment
import com.memo.tool.app.BaseApp
import com.memo.tool.ext.string

/**
 * title:本地文件夹地址
 * describe:
 *
 * @author zhou
 * @date 2019-07-23 15:26
 */
object LocalDir {

	/*** 本地沙箱地址 SDK29 Android Q后统一使用 不需要权限直接使用 手机上无法查看到数据***/
	val SANDBOX_CACHE by lazy { BaseApp.app.getExternalFilesDir("caches") }

	/*** 本地文件夹 需要权限才可以使用 ***/
	val LOCAL_CACHE by lazy { "${Environment.getExternalStorageDirectory().absolutePath}/${string(BaseApp.app.applicationInfo.labelRes)}" }

	/*** 本地缓存根目录 ***/
	val CACHE_ROOT_PATH = SANDBOX_CACHE?.absolutePath ?: LOCAL_CACHE

	/*** 本地图片压缩缓存 ***/
	val CACHE_DIR_COMPRESS: String = "$CACHE_ROOT_PATH/compress"
	val NOMEDIA_COMPRESS: String = "$CACHE_DIR_COMPRESS/.nomedia"

	/*** 本地拍照缓存 ***/
	val CACHE_DIR_CAPTURE: String = "$CACHE_ROOT_PATH/capture"
	val NOMEDIA_CAPTURE: String = "$CACHE_DIR_CAPTURE/.nomedia"

	/*** 本地图片裁剪缓存 ***/
	val CACHE_DIR_CROP: String = "$CACHE_ROOT_PATH/crop"
	val NOMEDIA_CROP: String = "$CACHE_DIR_CROP/.nomedia"

	/*** 本地文件缓存 ***/
	val CACHE_DIR_FILE: String = "$CACHE_ROOT_PATH/file"

	/*** 本地日志缓存 ***/
	val CACHE_DIR_LOG: String = "$CACHE_ROOT_PATH/log"
}