package com.memo.tool.ext

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.R
import com.memo.tool.helper.ClickHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * title:View的Kotlin拓展
 * describe:
 *
 * @author memo
 * @date 2019-11-20 14:39
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

/**
 * 设置View的高度
 * @param height 设置的高度
 */
fun View.height(height : Int) : View {
	layoutParams.height = height
	return this
}

/**
 * 设置View的宽度
 * @param width 设置的宽度
 */
fun View.width(width : Int) : View {
	layoutParams.width = width
	return this
}

/**
 * 设置View的宽度和高度
 * @param width 要设置的宽度
 * @param height 要设置的高度
 */
fun View.widthAndHeight(width : Int, height : Int) : View {
	val params : ViewGroup.LayoutParams? = layoutParams
	if (params == null) {
		layoutParams = ViewGroup.LayoutParams(width, height)
	} else {
		params.width = width
		params.height = height
		layoutParams = params
	}
	return this
}


/**
 * 设置View的margin  默认保留原设置
 * @param leftMargin 距离左的距离
 * @param topMargin 距离上的距离
 * @param rightMargin 距离右的距离
 * @param bottomMargin 距离下的距离
 */
fun View.margin(
	leftMargin : Int = Int.MAX_VALUE,
	topMargin : Int = Int.MAX_VALUE,
	rightMargin : Int = Int.MAX_VALUE,
	bottomMargin : Int = Int.MAX_VALUE
) : View {
	val params = layoutParams as ViewGroup.MarginLayoutParams
	if (leftMargin != Int.MAX_VALUE)
		params.leftMargin = leftMargin
	if (topMargin != Int.MAX_VALUE)
		params.topMargin = topMargin
	if (rightMargin != Int.MAX_VALUE)
		params.rightMargin = rightMargin
	if (bottomMargin != Int.MAX_VALUE)
		params.bottomMargin = bottomMargin
	layoutParams = params
	return this
}


/**
 * 设置控件Visible
 */
fun View.visible() {
	visibility = View.VISIBLE
}

/**
 * 设置控件Gone
 */
fun View.gone() {
	visibility = View.GONE
}

/**
 * 设置控件Invisible
 */
fun View.invisible() {
	visibility = View.INVISIBLE
}

/**
 * 设置控件Visible
 */
fun visible(vararg views : View?) {
	for (view in views) {
		view?.visible()
	}
}

/**
 * 设置控件Invisible
 */
fun invisible(vararg views : View?) {
	for (view in views) {
		view?.invisible()
	}
}

/**
 * 设置控件Gone
 */
fun gone(vararg views : View?) {
	for (view in views) {
		view?.gone()
	}
}

/**
 * 设置是否可见
 * @param visibleOrGone true - Visible false - Gone
 */
fun View.setVisible(visibleOrGone : Boolean) {
	visibility = if (visibleOrGone) View.VISIBLE else View.GONE
}

/**
 * 判断控件是否为Gone
 */
val View.isGone : Boolean
	get() {
		return visibility == View.GONE
	}

/**
 * 判断控件是否为Visible
 */
val View.isVisible : Boolean
	get() {
		return visibility == View.VISIBLE
	}

/**
 * 判断控件是否为InVisible
 */
val View.isInvisible : Boolean
	get() {
		return visibility == View.INVISIBLE
	}

/**
 * 获取View的Bitmap
 * 支持RecyclerView ScrollView 基础控件 不支持ListView了
 * 注意:使用这个方法的时候必须要在View测量完毕之后才能进行
 */
fun View.toBitmap() : Bitmap {
	if (measuredWidth == 0 || measuredHeight == 0) {
		throw RuntimeException("警告⚠️警告⚠️ 这个时候View还没有测量完毕")
	}
	return when (this) {
		is RecyclerView -> {
			this.scrollToPosition(0)
			this.measure(
				View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
				View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
			)
			
			val screenshot = Bitmap.createBitmap(width, measuredHeight, Bitmap.Config.ARGB_8888)
			val canvas = Canvas(screenshot)
			
			if (background != null) {
				background.setBounds(0, 0, width, measuredHeight)
				background.draw(canvas)
			} else {
				canvas.drawColor(Color.WHITE)
			}
			this.draw(canvas)
			this.measure(
				View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
				View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST)
			)
			screenshot
		}
		is ScrollView -> {
			var totalHeight = 0
			for (i in 0 until this.childCount) {
				totalHeight += this.getChildAt(i).height
			}
			val screenshot =
				Bitmap.createBitmap(this.getWidth(), totalHeight, Bitmap.Config.RGB_565)
			val canvas = Canvas(screenshot)
			this.draw(canvas)
			screenshot
		}
		else -> {
			val screenshot =
				Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.RGB_565)
			val canvas = Canvas(screenshot)
			if (background != null) {
				background.setBounds(0, 0, width, measuredHeight)
				background.draw(canvas)
			} else {
				canvas.drawColor(Color.WHITE)
			}
			draw(canvas)
			screenshot
		}
	}
}

/**
 * 控件绘制监听
 */
inline fun View.onGlobalLayoutListener(crossinline callback : () -> Unit) =
	with(viewTreeObserver) {
		addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
			@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
			override fun onGlobalLayout() {
				removeOnGlobalLayoutListener(this)
				callback()
			}
		})
	}

/**
 * 设置控件的点击时间
 * @param method 点击事件
 */
fun View.onClick(method : (view : View) -> Unit) {
	setOnClickListener {
		if (ClickHelper.isNotFastClick) {
			method(it)
		}
	}
}

/**
 * 对控件设置点击事件
 * @receiver View
 * @param listener OnNotFastClickListener
 */
fun View.onClick(listener : OnNotFastClickListener) {
	setOnClickListener(listener)
}

/**
 * 对多个控件设置点击事件
 * @param views 控件列表
 * @param onClick 点击方法
 */
fun onViewsClickListener(onClick : (View) -> Unit, vararg views : View) {
	val listener = View.OnClickListener {
		if (ClickHelper.isNotFastClick) {
			onClick(it)
		}
	}
	for (view in views) {
		view.setOnClickListener(listener)
	}
}

/**
 * 防止过快点击监听
 */
interface OnNotFastClickListener : View.OnClickListener {
	fun onNotFastClick(view : View)
	override fun onClick(v : View) {
		if (ClickHelper.isNotFastClick) {
			onNotFastClick(v)
		}
	}
}


/**
 * 重新发送验证码
 * @param second 默认60秒
 */
@SuppressLint("SetTextI18n", "CheckResult")
fun TextView.resendVerificationCodeAfter(owner : LifecycleOwner, second : Long = 60) {
	Observable.interval(0, 1, TimeUnit.SECONDS)
		.take(second + 1)
		.map { second - it }
		.observeOn(AndroidSchedulers.mainThread())
		.bindLifecycle(owner)
		.subscribe({
			LogUtils.iTag("sendCode", it)
			text = "(${it}秒)"
			setTextColor(color(R.color.color_F5F5F5))
		}, {
			isEnabled = true
			text = "发送验证码"
			setTextColor(color(R.color.color_666666))
		}, {
			isEnabled = true
			text = "发送验证码"
			setTextColor(color(R.color.color_666666))
		}, {
			isEnabled = false
		})
}


// ---------------------------------------- TextView ----------------------------------------

/**
 * 安全的设置控件文字
 */
var TextView.value : String
	get() = this.text?.toString()?.trim() ?: ""
	set(value) {
		this.text = value
	}





