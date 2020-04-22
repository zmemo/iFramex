package com.memo.test.ui.sw

import android.annotation.SuppressLint
import android.util.DisplayMetrics
import com.blankj.utilcode.util.ScreenUtils
import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.ext.dimen
import com.memo.base.tool.ext.height
import com.memo.test.R
import kotlinx.android.synthetic.main.activity_smallest_width.*

/**
 * title:使用最小宽度适配符的配置使用信息
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 14:24
 */
@SuppressLint("SetTextI18n")
class SmallestWidthActivity : BaseActivity() {
    /**
     * 绑定布局id
     */
    override fun bindLayoutRes() : Int = R.layout.activity_smallest_width

    /**
     * 进行初始化操作
     */

    override fun initialize() {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(dm)
        val dpi = "dpi : " + dm.densityDpi + "   width pixels : " + dm.widthPixels + "\n"
        val sw = "计算出来的smallestWidth : " + dm.widthPixels / (dm.densityDpi / 160.0) + "dp" + "\n"
        val swResource = "实际使用的smallestWidth :  " + resources.getString(R.string.base_dpi)
        mTvSmInfo.text = dpi + sw + swResource
	
	    mTvSmallest.text = "高度设置为dp375 \n dimen(R.dimen.dp375) = ${dimen(R.dimen.dp375)}"
	
	    val height = ScreenUtils.getAppScreenWidth()
	    mTvCustom.text = "高度设置为屏幕宽度 $height"
	    mTvCustom.height(height)
    }
}
