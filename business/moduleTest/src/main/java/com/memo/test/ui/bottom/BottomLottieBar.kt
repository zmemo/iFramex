package com.memo.test.ui.bottom

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent.ACTION_UP
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import com.memo.test.R
import com.memo.tool.ext.dimen
import com.memo.tool.ext.inflaterView
import kotlinx.android.synthetic.main.layout_bottom_lottie.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-12-31 17:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BottomLottieBar : FrameLayout {

    private var lastPosition = 0

    private var mTabChangeListener: OnTabChangeListener? = null

    private var mTabClickListener: OnTabClickListener? = null

    constructor(context: Context)
            : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        inflaterView(R.layout.layout_bottom_lottie, this)
        //是否显示边框阴影
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.outlineProvider = ViewOutlineProvider.BOUNDS
            ViewCompat.setElevation(this, dimen(R.dimen.dp5))
            clipToPadding = false
        }
        mLottie.frame = 220
        mLottie.setOnTouchListener { _, event ->
            if (event.action == ACTION_UP && !mLottie.isAnimating) {
                val width = mLottie.width
                val x = event.x
                val index = if (x > 0 && x <= width * 0.25) {
                    0
                } else if (x > width * 0.25 && x <= width * 0.5) {
                    1
                } else if (x > width * 0.5 && x <= width * 0.75) {
                    2
                } else {
                    3
                }
                if (lastPosition != index) {
                    lastPosition = index
                    when (index) {
                        0 -> mLottie.setMinAndMaxFrame(209, 216)
                        1 -> mLottie.setMinAndMaxFrame(36, 42)
                        2 -> mLottie.setMinAndMaxFrame(89, 97)
                        3 -> mLottie.setMinAndMaxFrame(149, 157)
                    }
                    mLottie.playAnimation()
                    mTabChangeListener?.onTabChanged(lastPosition)
                }
                mTabClickListener?.onTabClick(lastPosition)
            }
            true
        }
    }

    fun setOnTabClickListener(onTabClicked: (Int) -> Unit) {
        mTabClickListener = object : OnTabClickListener {
            override fun onTabClick(position: Int) {
                onTabClicked(position)
            }
        }
    }

    fun setOnTabChangedListener(onTabChanged: (Int) -> Unit) {
        mTabChangeListener = object : OnTabChangeListener {
            override fun onTabChanged(position: Int) {
                onTabChanged(position)
            }
        }
    }


    override fun onDetachedFromWindow() {
        mLottie.removeAllAnimatorListeners()
        mTabClickListener = null
        mTabChangeListener = null
        super.onDetachedFromWindow()
    }

    interface OnTabChangeListener {
        fun onTabChanged(position: Int)
    }

    interface OnTabClickListener {
        fun onTabClick(position: Int)
    }
}