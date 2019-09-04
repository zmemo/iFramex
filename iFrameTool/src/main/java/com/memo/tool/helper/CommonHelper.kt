package com.memo.tool.helper

import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.memo.tool.R
import kotlinx.android.synthetic.main.layout_toast.view.*

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */

// ------------------------------- Toast相关 -------------------------------//

fun toast(message: Any?) {
    message?.let {
        ToastUtils.setGravity(-1, -1, -1)
        ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
    }
}

fun toastCenter(message: Any?) {
    message?.let {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
        ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
    }
}

fun toastCancel() {
    ToastUtils.cancel()
}
