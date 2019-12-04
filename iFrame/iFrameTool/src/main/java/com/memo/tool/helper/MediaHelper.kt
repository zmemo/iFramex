@file:Suppress("DEPRECATION")

package com.memo.tool.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Camera
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.net.Uri
import androidx.annotation.IntRange
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.R
import com.memo.tool.app.BaseApp
import com.memo.tool.dir.LocalDir
import com.memo.tool.ext.md5
import com.memo.tool.photo.GifSizeFilter
import com.memo.tool.photo.Glide4Engine
import com.memo.tool.photo.VideoTimeFilter
import com.yalantis.ucrop.UCrop
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.internal.utils.MediaStoreCompat
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.util.*
import java.util.concurrent.locks.ReentrantLock

/**
 * title: 安卓6.0一下获取了权限 但是有系统软件拦截 导致不能录制音视频造成的问题
 * tip:
 *
 * @author zhou
 * @date 2018-09-12 上午11:05
 */
object MediaHelper {

    var MATISSE_PROVIDER = "${AppUtils.getAppPackageName()}.provider.MatisseFileProvider"


    /**
     * 创建多媒体文件夹和.nomedia文件
     */
    @JvmStatic
    fun createLocalDir() {
        FileUtils.createOrExistsDir(File(LocalDir.DIR_CAPTURE))
        FileUtils.createOrExistsFile(File(LocalDir.NOMEDIA_CAPTURE))
        FileUtils.createOrExistsDir(File(LocalDir.DIR_COMPRESS))
        FileUtils.createOrExistsFile(File(LocalDir.NOMEDIA_COMPRESS))
        FileUtils.createOrExistsDir(File(LocalDir.DIR_CROP))
        FileUtils.createOrExistsFile(File(LocalDir.NOMEDIA_CROP))
        FileUtils.createOrExistsDir(File(LocalDir.DIR_VIDEO))
        FileUtils.createOrExistsFile(File(LocalDir.NOMEDIA_VIDEO))
        FileUtils.createOrExistsDir(File(LocalDir.DIR_EXCEPTION_LOG))
    }

    /**
     * 通知相册刷新
     *
     * @param file 文件
     */
    @JvmStatic
    fun refreshAlbum(file: File?) {
        if (file != null && file.exists()) {
            BaseApp.app.applicationContext.sendBroadcast(
                Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(file)
                )
            )
        }
    }

    /**
     * 通知相册刷新
     *
     * @param filePath 文件地址
     */
    @JvmStatic
    fun refreshAlbum(filePath: String?) {
        if (filePath != null) {
            refreshAlbum(File(filePath))
        }
    }

    /**
     * 判断是否录音可以使用
     *
     * @return 是否
     *
     * 开始录制音频
     * 防止某些手机崩溃，例如联想
     * 根据开始录音判断是否有录音权限
     */
    @JvmStatic
    val isAudioUsable: Boolean
        get() {
            val audioFormat = AudioFormat.ENCODING_PCM_16BIT
            val channelConfig = AudioFormat.CHANNEL_IN_STEREO
            val sampleRateInHz = 44100
            val bufferSizeInBytes = AudioRecord.getMinBufferSize(
                sampleRateInHz,
                channelConfig, audioFormat
            )
            val audioSource = MediaRecorder.AudioSource.MIC
            val audioRecord = AudioRecord(
                audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes
            )
            try {
                audioRecord.startRecording()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                return false
            }
            if (audioRecord.recordingState != AudioRecord.RECORDSTATE_RECORDING) {
                return false
            }
            audioRecord.stop()
            audioRecord.release()
            return true
        }

    /**
     * 判断是否摄像头可以使用
     *
     * @return 是否可以使用
     * setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
     */
    @JvmStatic
    val isCameraUsable: Boolean
        get() {
            var canUse = true
            var mCamera: Camera? = null
            try {
                mCamera = Camera.open()
                val mParameters = mCamera!!.parameters
                mCamera.parameters = mParameters
            } catch (e: Exception) {
                canUse = false
            }

            mCamera?.release()
            return canUse
        }

    /**
     * 选择视频
     * @param activity Activity
     * @param requestCode Int
     */
    @JvmStatic
    fun chooseVideo(activity: Activity, requestCode: Int) {
        Matisse.from(activity)
            .choose(MimeType.ofVideo())
            .addFilter(VideoTimeFilter())
            .countable(true)
            .showSingleMediaType(true)
            .maxSelectable(1)
            .thumbnailScale(0.8f)
            .theme(R.style.Matisse_Zhihu)
            .imageEngine(Glide4Engine())
            .autoHideToolbarOnSingleTap(true)
            .forResult(requestCode)
    }

    /**
     * 选择相册图片
     * @param mActivity 上下文
     * @param showCapture 是否显示照相
     * @param chooseSize 还可以选择多少张 至少1张
     * @param requestCode 请求🐎
     */
    @JvmStatic
    fun choosePhoto(
        mActivity: Activity,
        showCapture: Boolean, @IntRange(from = 1, to = 9) chooseSize: Int,
        requestCode: Int,
        chooseGif: Boolean = true
    ) {
        if (chooseSize < 1) {
            return
        }
        val matisse = Matisse.from(mActivity)
        val creator = if (!chooseGif) {
            // 不使用gif
            matisse.choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.BMP, MimeType.WEBP))
        } else {
            // 使用gif
            matisse.choose(MimeType.ofImage())
        }
        if (showCapture) {
            creator
                .capture(true)
                .captureStrategy(CaptureStrategy(true, MATISSE_PROVIDER, "capture"))
        }
        creator.addFilter(GifSizeFilter())
            .countable(true)
            .showSingleMediaType(true)
            .maxSelectable(chooseSize)
            .thumbnailScale(0.8f)
            .theme(R.style.Matisse_Zhihu)
            .imageEngine(Glide4Engine())
            .autoHideToolbarOnSingleTap(true)
            .forResult(requestCode)
    }

    /**
     * 照相
     * @param mActivity 上下文
     * @param requestCode 请求🐎
     * @return 照相的图片返回地址
     * 注意需要在onActivityResult中有返回值之后才可以用
     * 注意此时的data:Intent为空
     */
    @JvmStatic
    fun takePhoto(mActivity: Activity, requestCode: Int): String? {
        val mediaStoreCompat = MediaStoreCompat(mActivity)
        mediaStoreCompat.setCaptureStrategy(CaptureStrategy(true, MATISSE_PROVIDER, "capture"))
        mediaStoreCompat.dispatchCaptureIntent(mActivity, requestCode)
        // 因为是指定Uri所以onActivityResult中的data为空 只能再这里获取拍照的路径
        return mediaStoreCompat.currentPhotoPath
    }

    /**
     * 裁剪图片
     * @param mActivity 上下文
     * @param sourcePath 输入源
     * @param requestCode 请求🐎
     * @return 裁剪后的图片地址
     * 注意需要在onActivityResult中有返回值之后才可以用
     */
    @JvmStatic
    fun cropPhoto(mActivity: Activity, sourcePath: String, requestCode: Int): String? {
        val sourceUri = Uri.fromFile(File(sourcePath))
        val outDir = File(LocalDir.DIR_CROP)
        val outFile = File(outDir, "CROP_${System.currentTimeMillis()}.jpg")
        FileUtils.createOrExistsFile(outFile)
        val outUri = Uri.fromFile(outFile)

        val uCrop: UCrop = UCrop.of(sourceUri, outUri)
        // 配置
        val option: UCrop.Options = UCrop.Options()
        // 隐藏底部栏
        option.setHideBottomControls(true)
        // 显示圆形遮盖
        option.setCircleDimmedLayer(true)
        // 是否显示网格
        option.setShowCropGrid(false)
        // 设置裁剪质量
        option.setCompressionQuality(80)
        // 设置标题栏颜色
        option.setToolbarColor(Color.WHITE)
        // 设置状态栏颜色
        option.setStatusBarColor(Color.WHITE)
        // 是否自由裁剪
        option.setFreeStyleCropEnabled(true)
        // 设置长宽比
        option.withAspectRatio(1f, 1f)
        // 设置配置
        uCrop.withOptions(option)
        // 开始裁剪
        uCrop.start(mActivity, requestCode)
        return outFile.absolutePath
    }

    /**
     * 从Intent中获取图片路径地址
     * @param intent Intent
     */
    @JvmStatic
    fun obtainPathResult(intent: Intent?): MutableList<String> {
        intent ?: return arrayListOf()
        return Matisse.obtainPathResult(intent) ?: arrayListOf()
    }

    /**
     * 同步压缩图片
     * @param mContext 上下文
     * @param images 原图片
     * @param onSuccess 成功回调
     * @param onFailure 失败回调
     * @return Disposable
     */
    @JvmStatic
    fun compressImagesSyn(
        mContext: Context,
        images: MutableList<String>,
        onSuccess: (images: List<File>) -> Unit,
        onFailure: () -> Unit
    ): Disposable? {
        // 创建压缩图片文件夹
        val compressDirPath: String = File(LocalDir.DIR_COMPRESS).absolutePath
        FileUtils.createOrExistsDir(compressDirPath)
        return Flowable.just(images)
            .observeOn(Schedulers.io())
            .map {
                Luban.with(mContext)
                    .load(it)
                    .ignoreBy(0)
                    .setFocusAlpha(true)
                    .setTargetDir(compressDirPath)
                    .filter { path ->
                        //如果是gif图不进行压缩
                        !path.toLowerCase(Locale.getDefault()).endsWith(".gif")
                    }
                    .get()
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure()
            })
    }

    /**
     * 异步压缩图片
     * 注意点:回调是延时操作并且不回被取消
     * @param mContext 上下文
     * @param images 原图片
     * @param onSuccess 成功回调
     * @param onFailure 失败回调
     */
    @JvmStatic
    fun compressImagesASyn(
        mContext: Context,
        images: MutableList<String>,
        onSuccess: (images: List<File>) -> Unit,
        onFailure: () -> Unit
    ) {
        // 创建锁
        val successLock = ReentrantLock()
        val errorLock = ReentrantLock()
        // 创建压缩图片文件夹
        createLocalDir()
        // 存放压缩文件路径的列表
        val paths = arrayListOf<File>()
        // 判断是否错误发生了
        var isErrorHappened = false

        Luban.with(mContext)
            .load(images)
            .ignoreBy(0)
            .setFocusAlpha(true)
            .setTargetDir(LocalDir.DIR_COMPRESS)
            .setRenameListener {
                // 重命名 第一位是图片顺序
                "${images.indexOf(it)}${it.md5()}.jpg"
            }
            .filter {
                //如果是gif图不进行压缩
                !it.toLowerCase(Locale.getDefault()).endsWith(".gif")
            }
            .setCompressListener(object : OnCompressListener {
                /**
                 * 图片压缩开始执行
                 */
                override fun onStart() {}

                /**
                 * 一张图片压缩成功
                 */
                override fun onSuccess(file: File) {
                    // 加锁
                    successLock.lock()
                    try {
                        paths.add(file)
                        if (paths.size == images.size) {
                            onSuccess(sortFile(paths))
                        }
                    } finally {
                        //解锁
                        successLock.unlock()
                    }
                }

                /**
                 * 一张图片压缩失败
                 */
                override fun onError(e: Throwable?) {
                    LogUtils.eTag("compress", "图片压缩失败 $e")
                    errorLock.lock()
                    try {
                        if (!isErrorHappened) {
                            isErrorHappened = true
                            onFailure()
                        }
                    } finally {
                        errorLock.unlock()
                    }
                }
            }).launch()
    }


    /**
     * 对文件进行排序
     * 固定第一位是排序位置
     */
    private fun sortFile(files: List<File>): List<File> {
        val arrays: Array<File> = files.toTypedArray()
        Arrays.sort(arrays) { o1, o2 ->
            val leftIndex = o1.name.substring(0, 1).toInt()
            val rightIndex = o2.name.substring(0, 1).toInt()
            leftIndex - rightIndex
        }
        return arrays.asList()
    }

}
