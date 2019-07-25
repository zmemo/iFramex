package com.memo.widget.bottomnavigationbar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.memo.tool.ext.inflaterView
import com.memo.widget.R

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-03-09 16:06
 */
class BottomNavigationBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    /*** 当前的选中下标 默认0 ***/
    private var mCurrentPosition: Int = 0
    /*** 上一次选中的下标 默认0 ***/
    private var mLastPosition: Int = 0
    /*** 文字为选中的颜色 ***/
    private var mTextColorNormal: Int = Color.GRAY
    /*** 文字选中的颜色 ***/
    private var mTextColorSelected: Int = Color.BLACK
    /*** Tab点击事件 ***/
    private var mClickListener: OnTabClickListener? = null
    /*** Tab改变事件 ***/
    private var mChangeListener: OnTabChangeListener? = null
    /*** Tab长按点击 ***/
    private var mLongClickListener: OnTabLongClickListener? = null

    /*** 底部Tab ***/
    private var mTab1: View
    private var mTab2: View
    private var mTab3: View
    private var mTab4: View

    /*** 底部Tab的图标 ***/
    private var mTabIcon1: ImageView
    private var mTabIcon2: ImageView
    private var mTabIcon3: ImageView
    private var mTabIcon4: ImageView

    /*** 底部Tab的文字 ***/
    private var mTabName1: TextView
    private var mTabName2: TextView
    private var mTabName3: TextView
    private var mTabName4: TextView

    /*** 图标列表未选中 ***/
    private var mIconNormalList: ArrayList<Int> = arrayListOf()
    /*** 图标列表选中 ***/
    private var mIconSelectedList: ArrayList<Int> = arrayListOf()
    /*** 文字列表 ***/
    private var mTitleList: ArrayList<String> = arrayListOf()

    /*** Tab控件集合 ***/
    private var mTabViews: ArrayList<View>
    /*** 图标控件集合 ***/
    private var mTabIcons: ArrayList<ImageView>
    /*** 文本控件集合 ***/
    private var mTabTitles: ArrayList<TextView>

    init {
        inflaterView(R.layout.bottom_navigation_bar, this)
        mTab1 = findViewById(R.id.tab1)
        mTab2 = findViewById(R.id.tab2)
        mTab3 = findViewById(R.id.tab3)
        mTab4 = findViewById(R.id.tab4)
        mTabIcon1 = findViewById(R.id.tab_icon1)
        mTabIcon2 = findViewById(R.id.tab_icon2)
        mTabIcon3 = findViewById(R.id.tab_icon3)
        mTabIcon4 = findViewById(R.id.tab_icon4)
        mTabName1 = findViewById(R.id.tab_name1)
        mTabName2 = findViewById(R.id.tab_name2)
        mTabName3 = findViewById(R.id.tab_name3)
        mTabName4 = findViewById(R.id.tab_name4)
        mTabViews = arrayListOf(mTab1, mTab2, mTab3, mTab4)
        mTabIcons = arrayListOf(mTabIcon1, mTabIcon2, mTabIcon3, mTabIcon4)
        mTabTitles = arrayListOf(mTabName1, mTabName2, mTabName3, mTabName4)
        for ((index, view) in mTabViews.withIndex()) {
            view.setOnClickListener {
                if (index != mCurrentPosition) {
                    mCurrentPosition = index
                    mTabIcons[mCurrentPosition].setImageResource(mIconSelectedList[mCurrentPosition])
                    mTabTitles[mCurrentPosition].setTextColor(mTextColorSelected)
                    mTabIcons[mLastPosition].setImageResource(mIconNormalList[mLastPosition])
                    mTabTitles[mLastPosition].setTextColor(mTextColorNormal)
                    mLastPosition = mCurrentPosition
                    mChangeListener?.onTabChange(index)
                }
                mClickListener?.onTabClick(index)
            }
            view.setOnLongClickListener {
                mLongClickListener?.onTabLongClick(index) ?: false
            }
        }
    }

    /**
     * 添加一个条目
     * @param iconResNormal 未选中的图片
     * @param iconResSelected 选中的图片
     * @param title 文本
     */
    fun addItem(iconResNormal: Int, iconResSelected: Int, title: String): BottomNavigationBar {
        mIconNormalList.add(iconResNormal)
        mIconSelectedList.add(iconResSelected)
        mTitleList.add(title)
        return this
    }

    /**
     * 添加所有条目
     * @param iconResNormals 未选中的图片集合
     * @param iconResSelecteds 选中的图片集合
     * @param titles 文字集合
     */
    fun addItems(
        iconResNormals: ArrayList<Int>,
        iconResSelecteds: ArrayList<Int>,
        titles: ArrayList<String>
    ): BottomNavigationBar {
        mIconNormalList = iconResNormals
        mIconSelectedList = iconResSelecteds
        mTitleList = titles
        return this
    }

    /**
     * 设置未选中和选中的文字颜色
     * @param textNormalColorRes 未选中的文字颜色
     * @param textSelectedColorRes 选中的文字颜色
     */
    fun setTextColor(@ColorRes textNormalColorRes: Int, @ColorRes textSelectedColorRes: Int): BottomNavigationBar {
        mTextColorNormal = ContextCompat.getColor(context, textNormalColorRes)
        mTextColorSelected = ContextCompat.getColor(context, textSelectedColorRes)
        return this
    }

    /**
     * 是指默认选中的Tab
     * @param position Tab的下标
     */
    fun setFirstSelected(position: Int): BottomNavigationBar {
        mCurrentPosition = position
        return this
    }

    /**
     * 设置Tab的点击事件
     */
    fun setOnTabClickListener(listener: OnTabClickListener): BottomNavigationBar {
        mClickListener = listener
        return this
    }

    /**
     * 设置Tab的点击事件
     */
    fun setOnTabClickListener(click: (Int) -> Unit): BottomNavigationBar {
        mClickListener = object : OnTabClickListener {
            override fun onTabClick(position: Int) {
                click(position)
            }
        }
        return this
    }

    /**
     * Tab长按事件
     */
    fun setOnTabLongClickListener(listener: OnTabLongClickListener): BottomNavigationBar {
        mLongClickListener = listener
        return this
    }

    /**
     * Tab长按点击事件
     */
    fun setOnTabLongClickListener(click: (Int) -> Boolean): BottomNavigationBar {
        mLongClickListener = object : OnTabLongClickListener {
            override fun onTabLongClick(position: Int): Boolean {
                return click(position)
            }
        }
        return this
    }

    /**
     * 设置Tab的改变事件
     */
    fun setOnTabChangeListener(listener: OnTabChangeListener): BottomNavigationBar {
        mChangeListener = listener
        return this
    }

    /**
     * 设置Tab的改变事件
     */
    fun setOnTabChangeListener(click: (Int) -> Unit): BottomNavigationBar {
        mChangeListener = object : OnTabChangeListener {
            override fun onTabChange(position: Int) {
                click(position)
            }
        }
        return this
    }

    /**
     * 构建布局
     */
    fun build() {
        for (index in 0 until mTabViews.size) {
            if (mCurrentPosition == index) {
                mTabIcons[index].setImageResource(mIconSelectedList[index])
                mTabTitles[index].text = mTitleList[index]
                mTabTitles[index].setTextColor(mTextColorSelected)
            } else {
                mTabIcons[index].setImageResource(mIconNormalList[index])
                mTabTitles[index].text = mTitleList[index]
                mTabTitles[index].setTextColor(mTextColorNormal)
            }
        }
    }

    /**
     * 单击事件
     */
    interface OnTabClickListener {
        fun onTabClick(position: Int)
    }

    /**
     * 长按点击事件
     */
    interface OnTabLongClickListener {
        fun onTabLongClick(position: Int): Boolean
    }

    /**
     * tab改变事件
     */
    interface OnTabChangeListener {
        fun onTabChange(position: Int)
    }
}