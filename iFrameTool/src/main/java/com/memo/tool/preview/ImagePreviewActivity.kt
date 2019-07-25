package com.memo.tool.preview

import android.graphics.Color
import android.os.Bundle
import com.blankj.utilcode.util.BarUtils
import com.memo.tool.R
import com.previewlibrary.GPreviewActivity

/**
 * title:大图预览界面
 * describe:
 *
 * @author zhou
 * @date 2019-01-31 17:28
 */
class ImagePreviewActivity : GPreviewActivity() {

    override fun setContentLayout(): Int = R.layout.activity_image_preview

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        super.onCreate(savedInstanceState)
    }
}
