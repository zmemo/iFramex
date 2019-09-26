package com.memo.tool.helper

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.ext.doInBackground
import com.memo.tool.ext.io2MainLifecycle
import io.reactivex.Observable

/**
 * title: 二维码帮助类
 * describe:
 *
 * @author zhou
 * @date 2019-05-08 14:00
 */
object QrcodeHelper {

    /**
     * 获取二维码数据
     * @param intent Intent
     * @return String? 可能为空
     */
    @JvmStatic
    @Nullable
    fun obtainQRCode(intent: Intent?): String? {
        return intent?.getStringExtra("result")
    }

    /**
     * 解析本地二维码
     * @param picPath String                图片地址
     * @param onSuccess (String) -> Unit    成功回调
     * @param onFailure () -> Unit          失败回调
     * @return Disposable?
     */
    @JvmStatic
    fun decodeQRCode(
        lifecycleOwner: LifecycleOwner,
        picPath: String,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit = { toast("无法读取二维码数据") }
    ) {
        Observable.just(picPath)
            .map { QRCodeDecoder.syncDecodeQRCode(it) }
            .io2MainLifecycle(lifecycleOwner)
            .subscribe({
                onSuccess(it)
            }, {
                LogUtils.eTag("QRCode", it.toString())
                onFailure()
            })
    }

    /**
     * 构造二维码
     * @param lifecycleOwner: LifecycleOwner 生命周期提供
     * @param content String                内容
     * @param sizePx Int                    宽高
     * @param logo Bitmap?                  内部图片 可以为null
     * @param foregroundColor Int 二维码颜色
     * @param backgroundColor Int 背景颜色
     * @param onSuccess (Bitmap) -> Unit    成功回调
     * @param onFailure () -> Unit          失败回调
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun encodeQRCode(
        lifecycleOwner: LifecycleOwner,
        content: String,
        sizePx: Int,
        logo: Bitmap? = null,
        @ColorInt foregroundColor: Int = Color.BLACK,
        @ColorInt backgroundColor: Int = Color.WHITE,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit = { toast("二维码创建失败") }
    ) {
        doInBackground(lifecycleOwner, {
            QRCodeEncoder.syncEncodeQRCode(content, sizePx, foregroundColor, backgroundColor, logo)
        }, {
            onSuccess(it)
        }, {
            LogUtils.eTag("QRCode", it)
            onFailure()
        })
    }
}