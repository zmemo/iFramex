package com.memo.tool.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.memo.tool.R
import com.memo.tool.app.BaseApp
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import kotlinx.android.synthetic.main.layout_toast.view.*

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */

// ------------------------------- Toast相关 -------------------------------//

fun toast(message : Any?) {
	message?.let {
		if (it.toString().isNotEmpty()) {
			ToastUtils.setGravity(-1, -1, -1)
			ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
		}
	}
}

fun toastCenter(message : Any?) {
	message?.let {
		if (it.toString().isNotEmpty()) {
			ToastUtils.setGravity(Gravity.CENTER, 0, 0)
			ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
		}
	}
}

fun toastCancel() {
	ToastUtils.cancel()
}


// ------------------------------- SmartRefreshLayout相关 -------------------------------//

/**
 * 关闭刷新
 * @param hasMore true -> 加载更多 false -> 不上拉加载
 */
fun SmartRefreshLayout.finish(hasMore : Boolean) {
	this.setEnableLoadMore(hasMore)
	when (state) {
		RefreshState.Refreshing -> finishRefresh(400)
		RefreshState.Loading -> finishLoadMore(400)
		else -> {
		}
	}
}
//---------------------------------------- Int ----------------------------------------

/**
 * 判断Int数据是否小于0 小于0则为0
 */
fun Int.checkLessZero() : Int = if (this < 0) 0 else this

/**
 * 判断数据是否大于99 显示99+
 */
fun Int.checkMore99() : String = if (this > 99) "99+" else this.toString()

//---------------------------------------- String ----------------------------------------

/**
 * 复制到粘贴板
 * @param content 内容
 */
fun copyToClipboard(content : String) {
	val plainText = ClipData.newPlainText("Copy", content)
	val clipboardManager = ContextCompat.getSystemService(BaseApp.app.applicationContext, ClipboardManager::class.java)
	clipboardManager?.primaryClip = plainText
}

/**
 * 从粘贴板上获取复制数据
 */
fun getFromClipboard() : String {
	val clipData = ContextCompat.getSystemService(BaseApp.app.applicationContext, ClipboardManager::class.java)?.primaryClip
	return if (clipData != null && clipData.itemCount > 0) {
		clipData.getItemAt(0).text.toString()
	} else {
		""
	}
}