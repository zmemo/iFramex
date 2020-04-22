package com.memo.base.tool.dialog.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.memo.base.R
import kotlin.math.roundToInt

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 16:13
 */
abstract class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    private val TAG by lazy { this.javaClass.simpleName }

    lateinit var mBehavior: BottomSheetBehavior<View>

    /*** 内容视图 ***/
    lateinit var contentView: View

    /*** 是否背景透明 ***/
    protected open fun transparent(): Boolean = true

    /*** 设置背景阴影浓度 ***/
    protected open fun dimAmount(): Float = 0.5f

    /*** 设置显示的高度 ***/
    protected open fun peekHeight(): Int = (ScreenUtils.getScreenHeight() / 2.5).roundToInt()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        contentView = LayoutInflater.from(context).inflate(bindLayoutRes(), null)
        dialog.setContentView(contentView)
        mBehavior = BottomSheetBehavior.from(contentView.parent as View)
        initConfig(dialog)
        return dialog
    }

    private fun initConfig(dialog: Dialog) {
        val window = dialog.window

        mBehavior.peekHeight = peekHeight()

        // 是否设置窗体背景透明
        if (transparent()) {
            window?.findViewById<View>(R.id.design_bottom_sheet)
                ?.setBackgroundResource(android.R.color.transparent)
        }

        // 设置窗体阴影浓度
        window?.attributes?.dimAmount = dimAmount()

        // 设置窗体动画
        window?.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)
    }

    override fun onStart() {
        super.onStart()
        initialize()
    }

    /**
     * 设置布局
     */
    @LayoutRes
    abstract fun bindLayoutRes(): Int

    /**
     * 初始化方法
     */
    abstract fun initialize()


    fun show(fragmentManager: FragmentManager) {
        try {
            val mDismissed = DialogFragment::class.java.getDeclaredField("mDismissed")
            mDismissed.isAccessible = true
            mDismissed.set(this, false)
            val mShownByMe = DialogFragment::class.java.getDeclaredField("mShownByMe")
            mShownByMe.isAccessible = true
            mShownByMe.set(this, true)
            fragmentManager.beginTransaction()
                .add(this, TAG)
                .commitAllowingStateLoss()
        } catch (e: Throwable) {
            LogUtils.eTag(TAG, e.toString())
        }
    }

    override fun dismiss() {
        super.dismissAllowingStateLoss()
    }

}