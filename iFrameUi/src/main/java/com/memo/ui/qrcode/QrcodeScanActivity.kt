package com.memo.ui.qrcode

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.activity.BaseActivity
import com.memo.tool.ext.finishActivityWithResult
import com.memo.tool.utils.MediaHelper
import com.memo.tool.utils.QrcodeHelper
import com.memo.ui.R
import com.memo.widget.titleview.TitleView
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.activity_qrcode_scan.*

/**
 * title:二维码扫描界面
 * describe:
 *
 * @author zhou
 * @date 2019-05-08 15:41
 */
@Route(path = RouterPath.Ui.QrcodeScanActivity)
class QrcodeScanActivity : BaseActivity() {

    private val REQUEST_CODE_QRCODE_ALBLUM = 1

    override fun bindLayoutResId(): Int = R.layout.activity_qrcode_scan

    override fun initialize() {
        initTitle()
        initQrcode()
    }

    private fun initTitle() {
        mTitleView.setOnTitleClickListener(object : TitleView.SimpleTitleClickListener() {
            override fun onRightClick(mTvRight: TextView) {
                MediaHelper.choosePhoto(mActivity, false, 1, REQUEST_CODE_QRCODE_ALBLUM)
            }
        })
    }

    private fun initQrcode() {
        // 只识别扫描框中的二维码
        mZXingView.scanBoxView.isOnlyDecodeScanBoxArea = true
        mZXingView.setDelegate(object : QRCodeView.Delegate {
            // 扫描成功
            override fun onScanQRCodeSuccess(result: String?) {
                finishActivityWithResult("result" to result)
            }

            // 摄像头亮度变化
            override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
            }

            // 打开相机出错
            override fun onScanQRCodeOpenCameraError() {
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_QRCODE_ALBLUM && Activity.RESULT_OK == resultCode && data != null) {
            val results = Matisse.obtainPathResult(data)
            if (results.isNotEmpty()) {
                addDisposable(
                    // 从相册图片中获取二维码
                    QrcodeHelper.decodeQRCode(results[0], {
                        finishActivityWithResult("result" to it)
                    }))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // 开启摄像头
        mZXingView.startCamera()
        // 开启扫描
        mZXingView.startSpotAndShowRect()
    }

    override fun onDestroy() {
        mZXingView.stopCamera()
        mZXingView.onDestroy()
        super.onDestroy()
    }
}
