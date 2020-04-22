package com.memo.base.base.activity

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.memo.base.tool.dialog.dialog.LoadingDialog
import com.memo.base.tool.ext.inflaterView
import com.memo.base.tool.helper.KeyboardHelper
import com.memo.base.tool.helper.OOMHelper
import com.memo.base.tool.helper.StatusBarHelper

/**
 * title:基础的Activity
 * tip:
 *
 * @author zhou
 * @date 2018-11-14 上午9:54
 */
abstract class BaseActivity : AppCompatActivity() {

    /*** 根布局 ***/
    protected lateinit var mRootView: View

    /*** Context ***/
    protected val mContext: BaseActivity by lazy { this }

    /*** LifecycleOwner ***/
    protected val mLifecycleOwner: LifecycleOwner by lazy { this }

    /*** 加载弹窗 ***/
    protected val mLoadDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    /*** 是否点击空白处隐藏软键盘 ***/
    protected open fun clickBlank2HideKeyboard(): Boolean = true

    /*** 是否透明状态栏 ***/
    protected open fun transparentStatusBar(): Boolean = false

    /*** 设置状态栏颜色 ***/
    @ColorInt
    protected open fun statusBarColor(): Int = Color.WHITE

    /**
     * 当前Activity是否始终是竖屏 不会发生屏幕变化
     * false 一般配合 android:configChanges="keyboardHidden|orientation|screenSize"
     */
    protected open fun alwaysPortrait(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        before()
        super.onCreate(savedInstanceState)
	    if (bindLayoutRes() != -1) {
		    mRootView = inflaterView(bindLayoutRes())
            setContentView(mRootView)
        }
        baseInit()
        initialize()
    }

    /**
     * 在初始化之前进行一些操作
     */
    protected open fun before() {}

    /*** 基础的初始化操作 ***/
    protected open fun baseInit() {
        if (transparentStatusBar()) {
            // 状态栏透明
            BarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0), true)
        } else {
            // 状态栏颜色
            StatusBarHelper.setStatusTextDarkMode(this)
            StatusBarHelper.setColor(this, Color.WHITE, 0)
	        // 注意这一行代码 让内容向下偏移
            mRootView.fitsSystemWindows = true
        }
        // 设置是否始终竖屏
        if (alwaysPortrait()) {
            ScreenUtils.setPortrait(this)
        }
    }

    /*** 绑定布局id ***/
    @LayoutRes
    protected abstract fun bindLayoutRes() : Int

    /*** 进行初始化操作 ***/
    protected abstract fun initialize()

    /*** 分发点击事件 用来隐藏软键盘 ***/
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (clickBlank2HideKeyboard()) {
            KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
        }
        return super.dispatchTouchEvent(ev)
    }
	
	/*** 显示加载弹窗 ***/
	fun showLoading(tip : String = "加载中") {
		mLoadDialog.show(tip)
	}
	
	/*** 隐藏加载弹窗 ***/
	fun hideLoading() {
		mLoadDialog.dismiss()
	}

    override fun onDestroy() {
	    super.onDestroy()
        // 销毁软键盘
        KeyboardHelper.onDestroy(mContext)
        // 清除所有的图片内存占用
        OOMHelper.onDestroy(mRootView)
    }
}