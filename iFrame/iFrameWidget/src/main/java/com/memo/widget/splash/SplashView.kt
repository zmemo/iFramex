package com.memo.widget.splash

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.animation.Animation
import android.widget.FrameLayout
import com.memo.tool.ext.*
import com.memo.tool.helper.AnimHelper
import com.memo.tool.simple.SimpleAnimationListener
import com.memo.widget.R
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.layout_splash_view.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-20 11:00
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SplashView @JvmOverloads constructor(
		context: Context,
		attrs: AttributeSet? = null,
		defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	private val hideAnim = AnimHelper.createAlphaAnim(1f, 0f, 500) {
		it.setAnimationListener(object : SimpleAnimationListener() {
			override fun onAnimationEnd(animation: Animation?) {
				gone()
			}
		})
	}


	var task: Disposable? = null

	init {
		inflaterView(R.layout.layout_splash_view, this)
		val attr = context.obtainStyledAttributes(attrs, R.styleable.SplashView)
		val drawable =
				attr.getDrawable(R.styleable.SplashView_splash_src) ?: ColorDrawable(Color.WHITE)
		val duration = attr.getInteger(R.styleable.SplashView_splash_duration, 3000)
		attr.recycle()

		// 设置背景
		mFlRoot.background = drawable
		// 延时时间到消失
		onGlobalLayoutListener {
			task = delay(duration.toLong()) { if (it == 0L && isShown) startAnimation(hideAnim) }
		}
		// 点击退出
		mTvClose.onClick {
			startAnimation(hideAnim)
			task?.dispose()
		}
	}

	override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		task?.dispose()
	}


}