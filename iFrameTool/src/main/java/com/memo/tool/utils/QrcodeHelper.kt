package com.memo.tool.utils

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Nullable
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.helper.RxHelper
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

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
    fun obtainQrcode(intent: Intent): String? {
        return intent.getStringExtra("result")
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
        picPath: String,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit = { toast("无法读取二维码数据") }
    ): Disposable? {
        return Observable.just(picPath)
            .map { QRCodeDecoder.syncDecodeQRCode(it) }
            .compose(RxHelper.io2Main())
            .subscribe({
                onSuccess(it)
            }, {
                LogUtils.eTag("QRCode", it.toString())
                onFailure()
            })
    }

    /**
     * 构造二维码
     * @param content String                内容
     * @param sizePx Int                    宽高
     * @param logo Bitmap?                  内部图片 可以为null
     * @param foregroundColor Int 二维码颜色
     * @param backgroundColor Int 背景颜色
     * @param onSuccess (Bitmap) -> Unit    成功回调
     * @param onFailure () -> Unit          失败回调
     * @return Disposable?
     */
    @JvmStatic
    fun encodeQRCode(
        content: String,
        sizePx: Int,
        logo: Bitmap? = null,
        @ColorInt foregroundColor: Int = Color.BLACK,
        @ColorInt backgroundColor: Int = Color.WHITE,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit = { toast("二维码创建失败") }
    ): Disposable? {

        return Observable.create<Bitmap> {
            it.onNext(QRCodeEncoder.syncEncodeQRCode(content, sizePx, foregroundColor, backgroundColor, logo))
        }.compose(RxHelper.io2Main())
            .subscribe({
                onSuccess(it)
            }, {
                LogUtils.eTag("QRCode", it.toString())
                onFailure()
            })
    }
}