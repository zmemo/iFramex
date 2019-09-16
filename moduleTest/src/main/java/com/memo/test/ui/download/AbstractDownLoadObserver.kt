package com.memo.test.ui.download

import androidx.annotation.IntRange
import com.memo.tool.helper.IOHelper
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * title:下载Observer
 * tip:
 *
 * @author zhou
 * @date 2018/8/10 下午7:32
 */
abstract class AbstractDownLoadObserver {

    /**
     * 可以重写，具体可由子类实现
     */
    fun onComplete() {}

    /**
     * 下载成功的回调
     *
     * @param file File
     */
    abstract fun onDownLoadSuccess(file: File)

    /**
     * 下载失败回调
     *
     * @param throwable 异常
     */
    abstract fun onDownLoadFail(throwable: Throwable)

    /**
     * 下载进度监听
     *
     * @param progress 进度
     * @param total 总量
     */
    abstract fun onProgress(@IntRange(from = 0, to = 100) progress: Int, total: Long)

    /**
     * 开始网络订阅的时候
     *
     * @param disposable 订阅状态
     */
    abstract fun onSubscribe(disposable: Disposable)

    /**
     * 将文件写入本地
     *
     * @param responseBody 请求结果全体
     * @param destFileDir 目标文件夹
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     */
    fun saveFile(responseBody: ResponseBody, destFileDir: String, destFileName: String): File {
        var inputStream: InputStream? = null
        val buf = ByteArray(2048)
        var len: Int
        var fos: FileOutputStream? = null
        try {
            inputStream = responseBody.byteStream()
            val total = responseBody.contentLength()
            var sum: Long = 0

            val dir = File(destFileDir)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val file = File(dir, destFileName)
            fos = FileOutputStream(file)
            len = inputStream.read(buf)
            while (len != -1) {
                sum += len.toLong()
                fos.write(buf, 0, len)
                val finalSum = sum
                onProgress((finalSum * 100 / total).toInt(), total)
                len = inputStream.read(buf)
            }
            fos.flush()
            return file
        } finally {
            IOHelper.close(inputStream)
            IOHelper.close(fos)
        }
    }
}
