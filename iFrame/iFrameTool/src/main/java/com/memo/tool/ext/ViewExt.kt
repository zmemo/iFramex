package com.memo.tool.ext

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.memo.tool.R
import com.memo.tool.helper.ClickHelper
import com.memo.tool.helper.RxHelper
import com.trello.rxlifecycle3.kotlin.bindToLifecycle
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * title:View的Kotlin拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 13:49
 */

/**
 * 设置View的高度
 * @param height 设置的高度
 */
fun View.height(height: Int): View {
    val params = layoutParams
    params.height = height
    layoutParams = params
    return this
}

/**
 * 设置View的宽度
 * @param width 设置的宽度
 */
fun View.width(width: Int): View {
    val params = layoutParams
    params.width = width
    layoutParams = params
    return this
}

/**
 * 设置View的宽度和高度
 * @param width 要设置的宽度
 * @param height 要设置的高度
 */
fun View.widthAndHeight(width: Int, height: Int): View {
    val params: ViewGroup.LayoutParams? = layoutParams
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
 * 设置背景颜色
 * @param color Int
 * @return View
 */
fun View.backgroundColor(@ColorInt color: Int): View {
    setBackgroundColor(color)
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
    leftMargin: Int = Int.MAX_VALUE,
    topMargin: Int = Int.MAX_VALUE,
    rightMargin: Int = Int.MAX_VALUE,
    bottomMargin: Int = Int.MAX_VALUE
): View {
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
fun visible(vararg views: View?) {
    for (view in views) {
        view?.visible()
    }
}

/**
 * 设置控件Invisible
 */
fun invisible(vararg views: View?) {
    for (view in views) {
        view?.invisible()
    }
}

/**
 * 设置控件Gone
 */
fun gone(vararg views: View?) {
    for (view in views) {
        view?.gone()
    }
}

/**
 * 设置是否可见
 * @param visibleOrGone true - Visible false - Gone
 */
fun View.setVisible(visibleOrGone: Boolean) {
    visibility = if (visibleOrGone) View.VISIBLE else View.GONE
}

/**
 * 判断控件是否为Gone
 */
val View.isGone: Boolean
    get() {
        return visibility == View.GONE
    }

/**
 * 判断控件是否为Visible
 */
val View.isVisible: Boolean
    get() {
        return visibility == View.VISIBLE
    }

/**
 * 判断控件是否为InVisible
 */
val View.isInvisible: Boolean
    get() {
        return visibility == View.INVISIBLE
    }

/**
 * 获取View的Bitmap
 * 支持RecyclerView ScrollView 基础控件 不支持ListView了
 * 注意:使用这个方法的时候必须要在View测量完毕之后才能进行
 */
fun View.toBitmap(): Bitmap {
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
                Bitmap.createBitmap(this.getWidth(), totalHeight, Bitmap.Config.ARGB_4444)
            val canvas = Canvas(screenshot)
            this.draw(canvas)
            screenshot
        }
        else -> {
            val screenshot =
                Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444)
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
 * 设置控件的点击时间
 * @param method 点击事件
 */
fun View.onClick(method: (view: View) -> Unit) {
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
fun View.onClick(listener: OnNotFastClickListener) {
    setOnClickListener(listener)
}

/**
 * 对多个控件设置点击事件
 * @param views 控件列表
 * @param onClick 点击方法
 */
fun onViewsClickListener(vararg views: View, onClick: (View) -> Unit) {
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
    fun onNotFastClick(view: View)
    override fun onClick(v: View) {
        if (ClickHelper.isNotFastClick) {
            onNotFastClick(v)
        }
    }
}

/**
 * 控件在多少秒之后可用
 * @param second 秒 3秒 10秒 60秒
 */
@SuppressLint("CheckResult")
fun View.enableAfter(second: Long) {
    Observable.interval(0, 1, TimeUnit.SECONDS)
        .take(second + 1)
        .map { second - it }
        .compose(RxHelper.io2Main())
        .bindToLifecycle(this)
        .doOnSubscribe { isEnabled = false }
        .subscribe({}, {}, { isEnabled = true })
}

/**
 * 重新发送验证码
 * @param second 默认60秒
 */
@SuppressLint("SetTextI18n", "CheckResult")
fun TextView.resendVerificationCodeAfter(second: Long = 60) {
    Observable.interval(0, 1, TimeUnit.SECONDS)
        .take(second + 1)
        .map { second - it }
        .compose(RxHelper.io2Main())
        .bindToLifecycle(this)
        .doOnSubscribe { isEnabled = false }
        .subscribe({
            text = "(${it}秒)"
            setTextColor(color(R.color.color_F5F5F5))
        }, {}) {
            isEnabled = true
            text = "发送验证码"
            setTextColor(color(R.color.color_666666))
        }
}

// ---------------------------------------- TextView ----------------------------------------

/**
 * 安全的设置控件文字
 * @param content 内容文字可以为空
 */
fun TextView.setSafeText(content: CharSequence?) {
    text = content ?: ""
}

