package com.memo.base.ui.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import com.memo.tool.dialog.dialog.LoadingDialog
import com.memo.tool.ext.inflaterView
import com.memo.tool.utils.KeyboardHelper
import com.memo.tool.utils.StatusBarHelper
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * title:基础的Activity
 * tip:
 *
 * @author zhou
 * @date 2018-11-14 上午9:54
 */
abstract class BaseActivity : RxAppCompatActivity() {

    /*** 根布局 ***/
    protected lateinit var mRootView: View

    /*** Context ***/
    protected val mContext: Context by lazy { this }

    /*** Activity ***/
    protected val mActivity: AppCompatActivity by lazy { this }

    /*** 生命周期提供 ***/
    protected val mLifecycleProvider: LifecycleProvider<ActivityEvent> by lazy { this }

    /*** 加载弹窗 ***/
    protected val mLoadDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    /*** RxJava2请求序列 ***/
    private val compositeDisposable by lazy { CompositeDisposable() }

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
        if (bindLayoutResId() != -1) {
            mRootView = inflaterView(bindLayoutResId())
            setContentView(mRootView)
        }
        baseInit()
        initialize()
    }

    /**
     * 在初始化之前进行一些操作
     */
    open fun before() {}

    /*** 基础的初始化操作 ***/
    protected open fun baseInit() {
        if (transparentStatusBar()) {
            // 状态栏透明
            BarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0), true)
        } else {
            // 状态栏颜色
            StatusBarHelper.setStatusTextDarkMode(this)
            StatusBarHelper.setColor(this, Color.WHITE, 0)
            // 注意这一行代码 让内容乡下偏移
            mRootView.fitsSystemWindows = true
        }
        // 设置是否始终竖屏
        if (alwaysPortrait()) {
            ScreenUtils.setPortrait(this)
        }
    }

    /*** 绑定布局id ***/
    @LayoutRes
    abstract fun bindLayoutResId(): Int

    /*** 进行初始化操作 ***/
    abstract fun initialize()

    /*** 分发点击事件 用来隐藏软键盘 ***/
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (clickBlank2HideKeyboard()) {
            KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    /*** 添加入队列 ***/
    fun addDisposable(disposable: Disposable?) {
        disposable?.let { compositeDisposable.add(it) }
    }

    override fun onDestroy() {
        // 清空RxJava2序列
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        // 隐藏软键盘
        KeyboardUtils.hideSoftInput(mActivity)
        // 修复软键盘内存泄漏
        KeyboardUtils.fixSoftInputLeaks(mActivity)
        super.onDestroy()
    }
}