package com.memo.widget.bottomnavigationbar

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.ext.color

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-04 11:39
 */
class BottomBar : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mColorNormal: Int = Color.parseColor("#cccccc")
    private var mColorSelect: Int = Color.parseColor("#333333")
    private val mItemIconNormalList: ArrayList<Int> = arrayListOf()
    private val mItemIconSelectList: ArrayList<Int> = arrayListOf()
    private val mItemTitleList: ArrayList<String> = arrayListOf()
    private var mLastClickPosition: Int = 0
    private var mClickListener: OnItemClickListener? = null
    private var mChangeListener: OnItemChangedListener? = null
    private var enableRipple = true

    init {
        orientation = HORIZONTAL
    }

    fun setItemColorRes(@ColorRes colorNormal: Int, @ColorRes colorSelect: Int): BottomBar {
        mColorNormal = color(colorNormal)
        mColorSelect = color(colorSelect)
        return this
    }

    fun setItemColor(@ColorInt colorNormal: Int, @ColorInt colorSelect: Int): BottomBar {
        mColorNormal = colorNormal
        mColorSelect = colorSelect
        return this
    }

    fun addItem(@DrawableRes drawableNormalRes: Int, @DrawableRes drawableSelectRes: Int, title: String): BottomBar {
        mItemIconNormalList.add(drawableNormalRes)
        mItemIconSelectList.add(drawableSelectRes)
        mItemTitleList.add(title)
        return this
    }

    fun setFirstSelect(position: Int): BottomBar {
        mLastClickPosition = position
        return this
    }

    fun showRippleAfterAndroidM(show: Boolean): BottomBar {
        enableRipple = show
        return this
    }

    fun setOnItemClickListener(listener: OnItemClickListener): BottomBar {
        mClickListener = listener
        return this
    }

    fun setOnItemChangeListener(listener: OnItemChangedListener): BottomBar {
        mChangeListener = listener
        return this
    }

    fun build() {
        addItemView()
        addItemListener()
    }

    private fun addItemView() {
        val size: Int = mItemTitleList.size
        for (index in 0 until size) {
            val item = LinearLayout(context)
            // 横向权重为1
            item.layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
            // 垂直排列
            item.orientation = VERTICAL
            // 居中
            item.gravity = Gravity.CENTER
            // 添加水波纹效果
            if (enableRipple && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val attrs = intArrayOf(android.R.attr.selectableItemBackground)
                val typedArray = context.applicationContext.theme.obtainStyledAttributes(attrs)
                item.background = typedArray.getDrawable(0)
            }
            // 设置图标
            val icon = ImageView(context)
            icon.setImageResource(
                if (index == mLastClickPosition) {
                    mItemIconSelectList[index]
                } else {
                    mItemIconNormalList[index]
                }
            )
            // 设置文字
            val text = TextView(context)
            text.text = mItemTitleList[index]
            text.gravity = Gravity.CENTER
            text.setTextColor(
                if (index == mLastClickPosition) {
                    mColorSelect
                } else {
                    mColorNormal
                }
            )
            text.setPadding(0, 10, 0, 0)
            item.addView(icon)
            item.addView(text)
            addView(item)
        }
    }

    private fun addItemListener() {
        // 在界面绘制完毕之后进行点击事件设置
        val count = childCount
        for (index in 0 until count) {
            // 找到当前条目
            val curItem = getChildAt(index)
            LogUtils.iTag("Bar", "child at  $index curItem == null ? ${curItem == null}")
            curItem.setOnClickListener {
                // 添加点击监听
                mClickListener?.onItemClick(index)

                if (mLastClickPosition != index) {
                    // 找到上一个条目
                    val lastItem = getChildAt(mLastClickPosition)
                    // 添加改变监听
                    mChangeListener?.onItemChanged(index)

                    // 改变当前选中Item
                    if (curItem is ViewGroup) {
                        val curIcon = curItem.getChildAt(0)
                        if (curIcon is ImageView) {
                            curIcon.setImageResource(mItemIconSelectList[index])
                        }
                        val curText = curItem.getChildAt(1)
                        if (curText is TextView) {
                            curText.setTextColor(mColorSelect)
                        }
                    }

                    // 改变上一个Item
                    if (lastItem is ViewGroup) {
                        val lastIcon = lastItem.getChildAt(0)
                        if (lastIcon is ImageView) {
                            lastIcon.setImageResource(mItemIconNormalList[mLastClickPosition])
                        }
                        val lastText = lastItem.getChildAt(1)
                        if (lastText is TextView) {
                            lastText.setTextColor(mColorNormal)
                        }
                    }

                    // 改变当前选中下标
                    mLastClickPosition = index
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemChangedListener {
        fun onItemChanged(position: Int)
    }
}