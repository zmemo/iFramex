package com.memo.widget.itemview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.memo.tool.ext.drawable
import com.memo.widget.R

/**
 * title: 横条控件
 * describe:
 *
 * @author zhou
 * @date 2019-06-10 14:26
 */
class ItemView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {


    private val minDp45 = dimen(R.dimen.dp45)

    private val mTvMain: TextView by lazy { TextView(context) }
    private val mTvExtra: TextView by lazy { TextView(context) }
    private val mLine: View by lazy { View(context) }

    private var mItemPadding: Float = dimen(R.dimen.dp15)

    private var mItemMainDrawable: Drawable? = null
    private var mItemMainDrawablePadding: Float = dimen(R.dimen.dp10)

    private var mItemMainText: String = ""
    private var mItemMainTextSize: Float = dimen(R.dimen.sp14)
    private var mItemMainTextColor: Int = Color.parseColor("#333333")
    private var mItemMainTextBold: Boolean = false

    private var mItemExtraText: String = ""
    private var mItemExtraTextSize: Float = dimen(R.dimen.sp12)
    private var mItemExtraTextColor: Int = Color.parseColor("#999999")
    private var mItemExtraTextBold: Boolean = false

    private var mItemExtraDrawable: Drawable? = null
    private var mItemExtraDrawablePadding: Float = dimen(R.dimen.dp10)

    private var mItemEnableRipple: Boolean = true

    private var mItemShowDivider: Boolean = true
    private var mItemDividerHeight: Float = dimen(R.dimen.dp0_5)
    private var mItemDividerColor: Int = Color.parseColor("#F5F5F5")
    private var mItemDividerMargin: Float = 0f


    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.ItemView)

        getParameters(attr)

        buildView()

        attr.recycle()

        minimumHeight = minDp45.toInt()
    }

    private fun getParameters(attr: TypedArray) {
        mItemPadding = attr.getDimension(R.styleable.ItemView_item_padding, mItemPadding)

        mItemMainDrawable = attr.getDrawable(R.styleable.ItemView_item_main_drawable)
        mItemMainDrawablePadding = attr.getDimension(
            R.styleable.ItemView_item_main_drawablePadding,
            mItemMainDrawablePadding
        )

        mItemMainText = attr.getString(R.styleable.ItemView_item_main_text) ?: mItemMainText
        mItemMainTextSize =
            attr.getDimension(R.styleable.ItemView_item_main_textSize, mItemMainTextSize)
        mItemMainTextColor =
            attr.getColor(R.styleable.ItemView_item_main_textColor, mItemMainTextColor)
        mItemMainTextBold =
            attr.getBoolean(R.styleable.ItemView_item_main_textBold, mItemMainTextBold)

        mItemExtraText = attr.getString(R.styleable.ItemView_item_extra_text) ?: mItemExtraText
        mItemExtraTextSize =
            attr.getDimension(R.styleable.ItemView_item_extra_textSize, mItemExtraTextSize)
        mItemExtraTextColor =
            attr.getColor(R.styleable.ItemView_item_extra_textColor, mItemExtraTextColor)
        mItemExtraTextBold =
            attr.getBoolean(R.styleable.ItemView_item_extra_textBold, mItemExtraTextBold)

        mItemExtraDrawable = attr.getDrawable(R.styleable.ItemView_item_extra_drawable)
        mItemExtraDrawablePadding = attr.getDimension(
            R.styleable.ItemView_item_extra_drawablePadding,
            mItemExtraDrawablePadding
        )

        mItemEnableRipple =
            attr.getBoolean(R.styleable.ItemView_item_ripple_enable, mItemEnableRipple)

        mItemShowDivider =
            attr.getBoolean(R.styleable.ItemView_item_divider_shown, mItemShowDivider)
        mItemDividerColor =
            attr.getColor(R.styleable.ItemView_item_divider_color, mItemDividerColor)
        mItemDividerHeight =
            attr.getDimension(R.styleable.ItemView_item_divider_height, mItemDividerHeight)
        mItemDividerMargin =
            attr.getDimension(R.styleable.ItemView_item_divider_margin, mItemDividerMargin)
    }

    private fun buildView() {
        // 主文本
        mTvMain.gravity = Gravity.CENTER_VERTICAL
        mTvMain.text = mItemMainText
        mTvMain.setTextSize(TypedValue.COMPLEX_UNIT_PX, mItemMainTextSize)
        mTvMain.setTextColor(mItemMainTextColor)
        mTvMain.paint.isFakeBoldText = mItemMainTextBold
        mTvMain.setCompoundDrawablesWithIntrinsicBounds(mItemMainDrawable, null, null, null)
        mTvMain.compoundDrawablePadding = mItemMainDrawablePadding.toInt()
        mTvMain.setPadding(mItemPadding.toInt(), 0, 0, 0)
        val mMainParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        mMainParams.gravity = Gravity.START
        mTvMain.layoutParams = mMainParams
        // 额外文本
        mTvExtra.gravity = Gravity.CENTER_VERTICAL
        mTvExtra.text = mItemExtraText
        mTvExtra.setTextSize(TypedValue.COMPLEX_UNIT_PX, mItemExtraTextSize)
        mTvExtra.setTextColor(mItemExtraTextColor)
        mTvExtra.paint.isFakeBoldText = mItemExtraTextBold
        mTvExtra.setCompoundDrawablesWithIntrinsicBounds(null, null, mItemExtraDrawable, null)
        mTvExtra.compoundDrawablePadding = mItemExtraDrawablePadding.toInt()
        mTvExtra.setPadding(0, 0, mItemPadding.toInt(), 0)
        val mExtraParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        mExtraParams.gravity = Gravity.END
        mTvExtra.layoutParams = mExtraParams
        // 水波纹
        if (mItemEnableRipple && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val attrs = intArrayOf(android.R.attr.selectableItemBackgroundBorderless)
            val typedArray = context.applicationContext.theme.obtainStyledAttributes(attrs)
            foreground = typedArray.getDrawable(0)
        }
        // 分割线 左右间隔和高度
        mLine.setBackgroundColor(mItemDividerColor)
        val layoutParams = LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            mItemDividerHeight.toInt()
        )
        layoutParams.marginStart = mItemDividerMargin.toInt()
        layoutParams.marginEnd = mItemDividerMargin.toInt()
        layoutParams.gravity = Gravity.BOTTOM
        mLine.layoutParams = layoutParams

        mLine.visibility = if (mItemShowDivider) {
            View.VISIBLE
        } else {
            View.GONE
        }
        // 添加到控件中
        addView(mTvMain)
        addView(mTvExtra)
        addView(mLine)
    }

    private fun dimen(dimenRes: Int): Float = context.resources.getDimension(dimenRes)

    /**
     * 设置左右两边的边距
     * @param padding 边距
     */
    fun setItemPadding(padding: Int) {
        mTvMain.setPadding(padding, 0, 0, 0)
        mTvExtra.setPadding(0, 0, padding, 0)
    }

    /**
     * 设置主图标
     * @param drawableRes 图标资源
     */
    fun setItemMainDrawable(@DrawableRes drawableRes: Int) {
        mTvMain.setCompoundDrawablesRelativeWithIntrinsicBounds(
            drawable(drawableRes),
            null,
            null,
            null
        )
    }

    /**
     * 设置图标边距
     * @param padding 边距
     */
    fun setItemMainDrawablePadding(padding: Int) {
        mTvMain.compoundDrawablePadding = padding
    }

    /**
     * 设置主文本
     * @param text 文本
     */
    fun setItemMainText(text: CharSequence?) {
        mTvMain.text = text ?: ""
    }

    /**
     * 设置主文本字体大小
     * @param size 字体大小
     */
    fun setItemMainTextSize(size: Float) {
        mTvMain.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    /**
     * 设置主文本颜色
     * @param color 文本颜色
     */
    fun setItemMainTextColor(@ColorInt color: Int) {
        mTvMain.setTextColor(color)
    }

    /**
     * 设置主文本粗体
     * @param isBold 是否粗体
     */
    fun setItemMainTextBold(isBold: Boolean) {
        mTvMain.paint.isFakeBoldText = isBold
    }

    /**
     * 设置额外文本
     * @param text 文本
     */
    fun setItemExtraText(text: CharSequence?) {
        mTvExtra.text = text ?: ""
    }

    /**
     * 设置额外文本字体大小
     * @param size 字体大小
     */
    fun setItemExtraTextSize(size: Float) {
        mTvExtra.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    /**
     * 设置额外文本颜色
     * @param color 文本颜色
     */
    fun setItemExtraTextColor(@ColorInt color: Int) {
        mTvExtra.setTextColor(color)
    }

    /**
     * 设置额外文本粗体
     * @param isBold 是否粗体
     */
    fun setItemExtraTextBold(isBold: Boolean) {
        mTvExtra.paint.isFakeBoldText = isBold
    }

    /**
     * 开启水波纹效果
     */
    fun enableRipple() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val attrs = intArrayOf(android.R.attr.selectableItemBackgroundBorderless)
            val typedArray = context.applicationContext.theme.obtainStyledAttributes(attrs)
            foreground = typedArray.getDrawable(0)
        }
    }

    /**
     * 是否显示分割线
     * @param isShow 是否显示
     */
    fun showItemDivider(isShow: Boolean) {
        mLine.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * 分割线高度
     * @param height 高度
     */
    fun setItemDividerHeight(height: Int) {
        mLine.layoutParams.height = height
    }

    /**
     * 分割线颜色
     * @param color 颜色
     */
    fun setItemDividerColor(@ColorInt color: Int) {
        mLine.setBackgroundColor(color)
    }

    /**
     * 分割线边距
     * @param padding 边距
     */
    fun setItemDividerPadding(padding: Int) {
        mLine.setPadding(padding, 0, padding, 0)
    }
}