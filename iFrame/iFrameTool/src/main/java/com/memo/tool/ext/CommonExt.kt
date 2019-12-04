package com.memo.tool.ext

import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.memo.tool.R
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
 * @param noMoreData true -> 不再上拉加载更多 false -> 正常上拉加载
 */
fun SmartRefreshLayout.finish(noMoreData : Boolean) {
	when (state) {
		RefreshState.Refreshing -> finishRefresh(400)
		RefreshState.Loading -> finishLoadMore(400, true, noMoreData)
		else -> {
		}
	}
}


//---------------------------------------- List ----------------------------------------
fun Collection<Any>.convert2String() : String {
	val builder = StringBuilder()
	this.forEachIndexed { index, i ->
		if (index == 0) {
			builder.append(i.toString())
		} else {
			builder.append(",").append(i.toString())
		}
	}
	return builder.toString()
}


//---------------------------------------- Int ----------------------------------------

/**
 * 判断Int数据是否小于0 小于0则为0
 */
fun Int.checkLessZero() : Int = if (this < 0) 0 else this