package com.memo.test.download

import android.os.Environment
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.api.Api
import com.memo.tool.ext.onClick
import com.memo.tool.utils.PermissionHelper
import kotlinx.android.synthetic.main.activity_down_load.*
import java.io.File

class DownLoadActivity : BaseActivity() {
    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_down_load

    /**
     * 进行初始化操作
     */
    override fun initialize() {

        mBtnService.onClick {
            if (PermissionHelper.grantedStorage(mContext)) {
                DownloadService.start(
                    Api.DownUrl,
                    Environment.getExternalStorageDirectory().absolutePath +
                            File.separator + "IFrame",
                    "${AppUtils.getAppName()}.apk"
                )
            }
        }

        mBtnManager.onClick {
            AppDownloadManager.get().downloadApk(Api.DownUrl, "文件下载", "文件更新")
                .setUpdateListener { currentByte, totalByte ->
                    LogUtils.iTag("down", "current = $currentByte  total = $totalByte")
                }
        }
    }

}
