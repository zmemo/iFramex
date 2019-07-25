package com.memo.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.memo.tool.dialog.dialog.LoadingDialog
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.FragmentEvent
import com.trello.rxlifecycle3.components.RxFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * title:基础的Fragment
 * tip:
 *
 * @author zhou
 * @date 2018-11-14 上午10:39
 */
abstract class BaseFragment : RxFragment() {

    /*** 根布局 ***/
    protected lateinit var mRootView: View

    /*** 生命周期提供 ***/
    protected val mLifecycleProvider: LifecycleProvider<FragmentEvent> by lazy { this }

    /*** 加载弹窗 ***/
    protected val mLoadDialog: LoadingDialog by lazy { LoadingDialog(activity!!) }

    /*** RxJava2请求序列 ***/
    private val compositeDisposable by lazy { CompositeDisposable() }

    /*** 标识 标识是否界面准备完毕 ***/
    private var isPrepare: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(bindLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRootView = view
        isPrepare = true
        initMvp()
        onVisibleToUser()
        initialize()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisibleToUser()
        }
    }

    private fun onVisibleToUser() {
        if (isPrepare && userVisibleHint) {
            isPrepare = false
            lazyInitialize()
        }
    }

    /**
     * 绑定布局
     */
    @LayoutRes
    protected abstract fun bindLayoutResId(): Int

    /*** 对于BaseMvpFragment的初始化 ***/
    protected open fun initMvp() {}

    /*** 正常初始化Fragment ***/
    protected open fun initialize() {}

    /*** 懒加载 ***/
    protected abstract fun lazyInitialize()

    /*** 添加入队列 ***/
    fun addDisposable(disposable: Disposable?) {
        disposable?.let { compositeDisposable.add(it) }
    }

    override fun onDestroyView() {
        // 清空RxJava2序列
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        super.onDestroyView()
    }
}