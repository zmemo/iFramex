package com.memo.test.ui.viewpager

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.memo.base.base.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.adapter.BaseViewPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*


class ViewPagerActivity : BaseActivity() {

    private val mViews by lazy { arrayListOf(buildView(1), buildView(2), buildView(3)) }

    private val mAdapter = BaseViewPagerAdapter<View>()

    private val mEvaluate = ArgbEvaluator()

    /*** 绑定布局id ***/
    override fun bindLayoutRes() : Int = R.layout.activity_view_pager

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mViewPager.offscreenPageLimit = mViews.size
        mAdapter.setData(mViews)
        mViewPager.adapter = mAdapter
        mViewPager.addOnPageChangeListener(listener)
    }

    @SuppressLint("SetTextI18n")
    private fun buildView(position: Int): View {
        val view = TextView(mContext)
        view.gravity = Gravity.CENTER
        view.text = "第${position}页"
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return view
    }

    private val listener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val color = if (position == 0) {
                // 根据positionOffset和第0页~第1页的颜色转换范围取颜色值
                mEvaluate.evaluate(positionOffset, Color.WHITE, Color.BLUE) as Int
            } else if (position == 1) {
                // 根据positionOffset和第1页~第2页的颜色转换范围取颜色值
                mEvaluate.evaluate(positionOffset, Color.BLUE, Color.GREEN) as Int
            } else {
                Color.GREEN
            }
            mViewPager.setBackgroundColor(color)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewPager.removeOnPageChangeListener(listener)
    }

}
