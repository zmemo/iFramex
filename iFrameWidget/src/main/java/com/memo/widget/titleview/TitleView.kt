package com.memo.widget.titleview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.memo.tool.ext.*
import com.memo.widget.R
import kotlinx.android.synthetic.main.title_view.view.*

/**
 * title:标题控件
 * describe:
 *
 * @author zhou
 * @date 2019-05-07 11:26
 */
@SuppressLint("ObsoleteSdkInt")
class TitleView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs, 0) {

    /*** 标题 ***/
    private var titleText: String = ""
    private var titleSize: Float = dimen(R.dimen.sp18)
    private var titleColor: Int = color(R.color.color_333333)
    private var titleBold: Boolean = true
    private var titleMarqueeEnable: Boolean = false

    /*** 副标题 ***/
    private var subtitleText: String = ""
    private var subtitleSize: Float = dimen(R.dimen.sp14)
    private var subtitleColor: Int = color(R.color.color_333333)
    private var subtitleBold: Boolean = false

    /*** 左侧图标 ***/
    private var leftDrawable: Int = R.drawable.ic_back
    private var leftShown: Boolean = true

    /*** 右侧图标 ***/
    private var rightText: String = ""
    private var rightTextSize: Float = dimen(R.dimen.sp14)
    private var rightTextColor: Int = color(R.color.color_333333)
    private var rightTextBold: Boolean = false
    private var rightDrawable: Int = 0
    private var rightDrawablePadding: Float = dimen(R.dimen.dp5)

    /*** 底部分割线 ***/
    private var dividerShown: Boolean = true

    private var mListener: SimpleTitleClickListener? = null

    init {
        inflaterView(R.layout.title_view, this)
        val attr: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
        initOption(attr)
        initView()
        initListener()
        attr.recycle()
    }

    private fun initOption(attr: TypedArray) {
        titleText = attr.getString(R.styleable.TitleView_title_title_text) ?: titleText
        titleSize = attr.getDimension(R.styleable.TitleView_title_title_size, titleSize)
        titleColor = attr.getColor(R.styleable.TitleView_title_title_color, titleColor)
        titleBold = attr.getBoolean(R.styleable.TitleView_title_title_bold, titleBold)
        titleMarqueeEnable = attr.getBoolean(R.styleable.TitleView_title_marquee_enable, titleMarqueeEnable)

        subtitleText = attr.getString(R.styleable.TitleView_title_subtitle_text) ?: subtitleText
        subtitleSize = attr.getDimension(R.styleable.TitleView_title_subtitle_size, subtitleSize)
        subtitleColor = attr.getColor(R.styleable.TitleView_title_subtitle_color, subtitleColor)
        subtitleBold = attr.getBoolean(R.styleable.TitleView_title_subtitle_bold, subtitleBold)

        leftDrawable = attr.getResourceId(R.styleable.TitleView_title_left_drawable, leftDrawable)
        leftShown = attr.getBoolean(R.styleable.TitleView_title_left_shown, leftShown)

        rightText = attr.getString(R.styleable.TitleView_title_right_text) ?: rightText
        rightTextSize = attr.getDimension(R.styleable.TitleView_title_right_text_size, rightTextSize)
        rightTextColor = attr.getColor(R.styleable.TitleView_title_right_text_color, rightTextColor)
        rightTextBold = attr.getBoolean(R.styleable.TitleView_title_right_text_bold, rightTextBold)
        rightDrawable = attr.getResourceId(R.styleable.TitleView_title_right_drawable, rightDrawable)
        rightDrawablePadding = attr.getDimension(R.styleable.TitleView_title_right_drawable_padding, rightDrawablePadding)

        dividerShown = attr.getBoolean(R.styleable.TitleView_title_divider_shown, dividerShown)
    }

    private fun initView() {
        // 标题
        mTvTitle.text = titleText
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize)
        mTvTitle.setTextColor(titleColor)
        if (titleBold) {
            mTvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
        if (titleMarqueeEnable) {
            mTvTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
            mTvTitle.setSingleLine()
            mTvTitle.isSelected = true
            mTvTitle.isFocusable = true
            mTvTitle.isFocusableInTouchMode = true
        }
        // 副标题
        if (subtitleText.isNotEmpty()) {
            mTvSubTitle.visible()
            mTvSubTitle.text = subtitleText
            mTvSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subtitleSize)
            mTvSubTitle.setTextColor(subtitleColor)
            if (subtitleBold) {
                mTvSubTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }
        }
        // 左侧图标
        if (leftShown) {
            mIvLeft.setImageDrawable(drawable(leftDrawable))
        } else {
            mIvLeft.gone()
        }
        // 右侧文字
        if (rightText.isNotEmpty()) {
            mTvRight.visible()
            mTvRight.text = rightText
            mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize)
            mTvRight.setTextColor(rightTextColor)
            if (rightTextBold) {
                mTvRight.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }
        }
        if (rightDrawable != 0) {
            mTvRight.visible()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mTvRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, rightDrawable, 0)
            } else {
                mTvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightDrawable, 0)
            }
            mTvRight.compoundDrawablePadding = rightDrawablePadding.toInt()
        }

        mLine.setVisible(dividerShown)
    }

    private fun initListener() {
        mIvLeft.onClick {
            if (mListener == null) {
                if (context is Activity) {
                    (context as Activity).finish()
                }
            } else {
                mListener?.onLeftClick()
            }
        }
        mTvTitle.onClick {
            mListener?.onTitleClick()
        }
        mTvRight.onClick {
            mListener?.onRightClick(mTvRight)
        }
    }

    /**
     * 设置标题
     * @param title String? 标题
     */
    fun setTitle(title: String?) {
        mTvTitle.text = title ?: ""
    }

    /**
     * 设置标题字体大小
     * @param dimenSp Int R.dimen.sp10
     */
    fun setTitleSize(@DimenRes dimenSp: Int) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen(dimenSp))
    }

    /**
     * 设置标题字体颜色
     * @param colorInt Int Color.White
     */
    fun setTitleColor(@ColorInt colorInt: Int) {
        mTvTitle.setTextColor(colorInt)
    }

    /**
     * 是否开启标题跑马灯
     * @param enable Boolean
     */
    fun enableTitleMarquee(enable: Boolean) {
        mTvTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
        mTvTitle.setSingleLine()
        mTvTitle.isSelected = enable
        mTvTitle.isFocusable = enable
        mTvTitle.isFocusableInTouchMode = enable
    }

    /**
     * 是否允许标题粗体
     * @param enable Boolean
     */
    fun enableTitleBold(enable: Boolean) {
        mTvRight.typeface = if (enable) {
            Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    /**
     * 设置副标题
     * @param subTitle String? 副标题
     */
    fun setSubTitle(subTitle: String?) {
        subTitle?.let {
            mTvSubTitle.visible()
            mTvSubTitle.text = it
        }
    }

    /**
     * 副标题字体大小
     * @param dimenSp Int R.dimen.sp10
     */
    fun setSubTitleSize(@DimenRes dimenSp: Int) {
        mTvSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen(dimenSp))
    }

    /**
     * 设置副标题颜色
     * @param colorInt Int Color.white
     */
    fun setSubTitleColor(@ColorInt colorInt: Int) {
        mTvSubTitle.setTextColor(colorInt)
    }

    /**
     * 是否允许副标题粗体
     * @param enable Boolean
     */
    fun enableSubTitleBold(enable: Boolean) {
        mTvSubTitle.typeface = if (enable) {
            Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    /**
     * 左侧图标
     * @param drawableRes Int
     */
    fun setLeftDrawable(@DrawableRes drawableRes: Int) {
        mIvLeft.setImageDrawable(drawable(drawableRes))
    }

    /**
     * 是否显示左侧图标
     * @param shown Boolean
     */
    fun showLeft(shown: Boolean) {
        mIvLeft.setVisible(shown)
    }

    /**
     * 设置右侧图标
     * @param drawable Int
     */
    fun setRightDrawable(@DrawableRes drawable: Int) {
        if (drawable != 0) {
            mTvRight.visible()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mTvRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, rightDrawable, 0)
            } else {
                mTvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightDrawable, 0)
            }
            mTvRight.compoundDrawablePadding = rightDrawablePadding.toInt()
        }
    }

    /**
     * 设置右侧文字
     * @param moreText String? 文字
     */
    fun setRightText(moreText: String?) {
        moreText?.let {
            mTvRight.visible()
            mTvRight.text = it
        }
    }

    /**
     * 设置右侧文字大小
     * @param dimenSp Int R.dimen.sp10
     */
    fun setRightTextSize(@DimenRes dimenSp: Int) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen(dimenSp))
    }

    /**
     * 设置右侧文字颜色
     * @param colorInt Int Color.white
     */
    fun setRightTextColor(@ColorInt colorInt: Int) {
        mTvRight.setTextColor(colorInt)
    }

    /**
     * 右侧文字粗体
     * @param enable Boolean
     */
    fun enableRightTextBold(enable: Boolean) {
        mTvRight.typeface = if (enable) {
            Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    /**
     * 设置右侧文字和图标的间隔
     * @param padding Int
     */
    fun setRightDrawablePadding(padding: Int) {
        mTvRight.compoundDrawablePadding = padding
    }

    /**
     * 设置点击事件
     * 左侧点击
     * 标题点击
     * 右侧点击
     * @param listener SimpleTitleClickListener
     */
    fun setOnTitleClickListener(listener: SimpleTitleClickListener) {
        mListener = listener
    }

    private interface OnClickListener {
        /**
         * 左侧点击
         */
        fun onLeftClick()

        /**
         * 标题点击
         */
        fun onTitleClick()

        /**
         * 右侧点击
         * @param mTvRight TextView 右侧控件
         */
        fun onRightClick(mTvRight: TextView)
    }

    open class SimpleTitleClickListener : OnClickListener {

        override fun onLeftClick() {}

        override fun onTitleClick() {}

        override fun onRightClick(mTvRight: TextView) {}
    }
}