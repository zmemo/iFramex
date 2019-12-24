package com.memo.base.api

import android.net.ParseException
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.*
import com.google.gson.JsonParseException
import com.memo.base.config.config.Config
import com.memo.tool.dir.LocalDir
import com.memo.tool.helper.IOHelper
import org.json.JSONException
import retrofit2.HttpException
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

/**
 * title:异常处理
 * describe:
 *
 * @author zhou
 * @date 2019-07-09 10:41
 */
object ExceptionHandler {

    fun handleException(exception: Throwable): ApiException {
        //错误日志打印
        LogUtils.eTag("HTTP ERROR", exception.toString())
        //在这里把错误日志存储到本地
        saveErrorLog2Local(exception)
        return when (exception) {
            is ApiException -> {
                // 服务器返回的错误
                exception
            }
            is JsonParseException,
            is JSONException,
            is ParseException -> {
                // 解析错误
                ApiException(ApiCode.ParseErrorCode, "数据解析失败")
            }
            is HttpException,
            is ConnectException,
            is SocketException -> {
                // 连接错误
                ApiException(
                    ApiCode.ServerErrorCode,
                    "无法连接服务器"
                )
            }
            is UnknownHostException -> {
                // 网络错误
                ApiException(ApiCode.NetworkErrorCode, "网络异常")
            }
            else -> {
                // 位置错误
                ApiException(
                    ApiCode.UnknownErrorCode,
                    "发生未知错误"
                )
            }
        }
    }

    private fun saveErrorLog2Local(exception: Throwable) {
        //正式环境下 不进行日志保存
        if (!Config.isOpenLog) return

        //没有存储权限 直接返回
        if (!PermissionUtils.isGranted(PermissionConstants.STORAGE)) return

        //创建错误日志文件夹
        val file = File(LocalDir.DIR_EXCEPTION_LOG)
        FileUtils.createOrExistsDir(file)

        //获取详细错误信息
        var info: String
        var sw:StringWriter?  = null
        var pw:PrintWriter? = null
        try {
            sw = StringWriter()
            pw = PrintWriter(sw)
            exception.printStackTrace(pw)
            pw.flush()
            sw.flush()
            info = sw.toString()
        }catch (e:Exception){
            info = exception.toString()
        }finally {
	        IOHelper.close(sw, pw)
        }
        //信息存储
        val log = "${file.absolutePath}/${TimeUtils.getNowString()}.txt"
        FileIOUtils.writeFileFromString(log, info)
    }
}