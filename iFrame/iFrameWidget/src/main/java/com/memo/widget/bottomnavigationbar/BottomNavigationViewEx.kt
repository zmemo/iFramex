package com.memo.widget.bottomnavigationbar


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * title:底部导航栏
 * describe:使用selector进行切换图标和文字是否选中的状态变化
 *
 * @author zhou
 * @date 2019-08-19 17:08
 */
class BottomNavigationViewEx : BottomNavigationViewInner {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setIconVisibility(visibility: Boolean): BottomNavigationViewInner {
        try {
            return super.setIconVisibility(visibility)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setTextVisibility(visibility: Boolean): BottomNavigationViewInner {
        try {
            return super.setTextVisibility(visibility)
        } catch (e: Exception) {
            return this
        }

    }

    override fun getCurrentItem(): Int {
        try {
            return super.getCurrentItem()
        } catch (e: Exception) {
            return 0
        }

    }

    override fun getMenuItemPosition(item: MenuItem): Int {
        try {
            return super.getMenuItemPosition(item)
        } catch (e: Exception) {
            return 0
        }

    }

    override fun setCurrentItem(index: Int): BottomNavigationViewInner {
        try {
            return super.setCurrentItem(index)
        } catch (e: Exception) {
            return this
        }

    }

    override fun getOnNavigationItemSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener? {
        try {
            return super.getOnNavigationItemSelectedListener()
        } catch (e: Exception) {
            return null
        }

    }

    override fun setOnNavigationItemSelectedListener(listener: BottomNavigationView.OnNavigationItemSelectedListener?) {
        try {
            super.setOnNavigationItemSelectedListener(listener)
        } catch (ignored: Exception) {
        }

    }

    override fun clearIconTintColor(): BottomNavigationViewInner {
        try {
            return super.clearIconTintColor()
        } catch (e: Exception) {
            return this
        }

    }

    override fun getBottomNavigationItemViews(): Array<BottomNavigationItemView>? {
        try {
            return super.getBottomNavigationItemViews()
        } catch (e: Exception) {
            return null
        }

    }

    override fun getBottomNavigationItemView(position: Int): BottomNavigationItemView? {
        try {
            return super.getBottomNavigationItemView(position)
        } catch (e: Exception) {
            return null
        }

    }

    override fun getIconAt(position: Int): ImageView? {
        try {
            return super.getIconAt(position)
        } catch (e: Exception) {
            return null
        }

    }

    override fun getSmallLabelAt(position: Int): TextView? {
        try {
            return super.getSmallLabelAt(position)
        } catch (e: Exception) {
            return null
        }

    }

    override fun getLargeLabelAt(position: Int): TextView? {
        try {
            return super.getLargeLabelAt(position)
        } catch (e: Exception) {
            return null
        }

    }

    override fun getItemCount(): Int {
        try {
            return super.getItemCount()
        } catch (e: Exception) {
            return 0
        }

    }

    override fun setSmallTextSize(sp: Float): BottomNavigationViewInner {
        try {
            return super.setSmallTextSize(sp)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setLargeTextSize(sp: Float): BottomNavigationViewInner {
        try {
            return super.setLargeTextSize(sp)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setTextSize(sp: Float): BottomNavigationViewInner {
        try {
            return super.setTextSize(sp)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setIconSizeAt(
        position: Int,
        width: Float,
        height: Float
    ): BottomNavigationViewInner {
        try {
            return super.setIconSizeAt(position, width, height)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setIconSize(width: Float, height: Float): BottomNavigationViewInner {
        try {
            return super.setIconSize(width, height)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setIconSize(dpSize: Float): BottomNavigationViewInner {
        try {
            return super.setIconSize(dpSize)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setItemHeight(height: Int): BottomNavigationViewInner {
        try {
            return super.setItemHeight(height)
        } catch (e: Exception) {
            return this
        }

    }

    override fun getItemHeight(): Int {
        try {
            return super.getItemHeight()
        } catch (e: Exception) {
            return 0
        }

    }

    override fun setTypeface(typeface: Typeface, style: Int): BottomNavigationViewInner {
        try {
            return super.setTypeface(typeface, style)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setTypeface(typeface: Typeface): BottomNavigationViewInner {
        try {
            return super.setTypeface(typeface)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setupWithViewPager(viewPager: ViewPager): BottomNavigationViewInner {
        try {
            return super.setupWithViewPager(viewPager)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setupWithViewPager(
        viewPager: ViewPager,
        smoothScroll: Boolean
    ): BottomNavigationViewInner {
        try {
            return super.setupWithViewPager(viewPager, smoothScroll)
        } catch (e: Exception) {
            return this
        }

    }

    override fun enableShiftingMode(position: Int, enable: Boolean): BottomNavigationViewInner {
        try {
            return super.enableShiftingMode(position, enable)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setItemBackground(position: Int, background: Int): BottomNavigationViewInner {
        try {
            return super.setItemBackground(position, background)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setIconTintList(position: Int, tint: ColorStateList): BottomNavigationViewInner {
        try {
            return super.setIconTintList(position, tint)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setTextTintList(position: Int, tint: ColorStateList): BottomNavigationViewInner {
        try {
            return super.setTextTintList(position, tint)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setIconsMarginTop(marginTop: Int): BottomNavigationViewInner {
        try {
            return super.setIconsMarginTop(marginTop)
        } catch (e: Exception) {
            return this
        }

    }

    override fun setIconMarginTop(position: Int, marginTop: Int): BottomNavigationViewInner {
        try {
            return super.setIconMarginTop(position, marginTop)
        } catch (e: Exception) {
            return this
        }

    }
}

