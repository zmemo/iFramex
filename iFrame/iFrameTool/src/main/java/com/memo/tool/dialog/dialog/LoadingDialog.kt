package com.memo.tool.dialog.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import com.memo.tool.R
import kotlinx.android.synthetic.main.dialog_tip.view.*
import razerdp.basepopup.BasePopupWindow

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 15:28
 */
class LoadingDialog constructor(context: Context) : BasePopupWindow(context) {

    /**
     * 初始化
     */
    init {
        popupGravity = Gravity.CENTER
        // 点击外部不消失
        setOutSideDismiss(false)
        setBackPressEnable(false)
    }

    /**
     * 返回一个contentView以作为PopupWindow的contentView
     * 强烈建议使用[BasePopupWindow.createPopupById]，该方法支持读取View的xml布局参数，否则可能会出现与布局不一样的展示从而必须手动传入宽高等参数**
     */
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_load)
    }

    /**
     * 显示动画
     */
    override fun onCreateShowAnimation(): Animation {
        return getDefaultAlphaAnimation(true)
    }

    /**
     * 消失动画
     */
    override fun onCreateDismissAnimation(): Animation {
        return getDefaultAlphaAnimation(false)
    }

    /**
     * 简化显示方法
     */
    fun show(tip : String = "加载中") {
	    contentView.mTvMessage.text = tip
        showPopupWindow()
    }
}