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
	
	val ROOT_PATH = "${Environment.getExternalStorageDirectory().absolutePath}/${string(BaseApp.app.applicationContext.applicationInfo.labelRes)}"
	
	val DIR_COMPRESS : String = "$ROOT_PATH/compress"
	val NOMEDIA_COMPRESS : String = "$DIR_COMPRESS/.nomedia"
	
	val DIR_CAPTURE : String = "$ROOT_PATH/capture"
	val NOMEDIA_CAPTURE : String = "$DIR_CAPTURE/.nomedia"
	
	val DIR_CROP : String = "$ROOT_PATH/crop"
	val NOMEDIA_CROP : String = "$DIR_CROP/.nomedia"

	val DIR_VIDEO: String = "$ROOT_PATH/video"
	val NOMEDIA_VIDEO : String = "$DIR_VIDEO/.nomedia"
	
	val DIR_EXCEPTION_LOG : String = "$ROOT_PATH/Log"
}