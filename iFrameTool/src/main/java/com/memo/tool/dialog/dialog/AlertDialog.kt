package com.memo.tool.dialog.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import com.memo.tool.dialog.listener.OnTipClickListener
import com.memo.tool.R
import com.memo.tool.ext.gone
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.dialog_tip.view.*
import razerdp.basepopup.BasePopupWindow

/**
 * title: 提示弹窗
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 15:28
 */
class AlertDialog(
    context: Context,
    private val title: String = "提示",
    private val message: String = "内容",
    private val negative: String = "取消",
    private val positive: String = "确定"
) : BasePopupWindow(context) {

    /*** 监听 ***/
    private var mListener: OnTipClickListener? = null

    /**
     * 初始化
     */
    init {
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
        initialize()
    }

    /**
     * 返回一个contentView以作为PopupWindow的contentView
     * 强烈建议使用[BasePopupWindow.createPopupById]，该方法支持读取View的xml布局参数，否则可能会出现与布局不一样的展示从而必须手动传入宽高等参数**
     */
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_tip)
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
     * 初始化
     */
    private fun initialize() {
        contentView.mTvTitle.text = title
        contentView.mTvMessage.text = message
        contentView.mTvNegative.text = negative
        contentView.mTvPositive.text = positive
        contentView.mTvPositive.onClick {
            mListener?.onPositive()
            dismiss()
        }
        contentView.mTvNegative.onClick {
            mListener?.onNegative()
            dismiss()
        }
    }

    /**
     * 隐藏Negative按钮
     * @return TipDialog
     */
    fun hideNegative(): AlertDialog {
        contentView.mTvNegative.gone()
        contentView.mLine.gone()
        return this
    }

    /**
     * 设置点击事件
     */
    fun setOnTipClickListener(onPositive: () -> Unit, onNegative: () -> Unit): AlertDialog {
        mListener = object : OnTipClickListener() {
            /**
             * 点击确定
             */
            override fun onPositive() {
                onPositive()
            }

            /**
             * 点击取消
             */
            override fun onNegative() {
                onNegative()
            }
        }
        return this
    }

    /**
     * 设置点击事件
     */
    fun setOnTipClickListener(onPositive: () -> Unit): AlertDialog {
        mListener = object : OnTipClickListener() {
            override fun onPositive() {
                onPositive()
            }
        }
        return this
    }

    /**
     * 简化显示方法
     */
    fun show() {
        showPopupWindow()
    }
}