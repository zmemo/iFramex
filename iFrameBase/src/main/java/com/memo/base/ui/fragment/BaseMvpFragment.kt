package com.memo.base.ui.fragment

import com.memo.base.ui.mvp.IPresenter
import com.memo.base.ui.mvp.IView
import io.reactivex.disposables.Disposable

/**
 * title:基础的MVP模式Fragment
 * describe:
 *
 * @author zhou
 * @date 2019-01-28 13:58
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    protected var mPresenter: P? = null

    /*** 绑定Presenter ***/
    protected abstract fun buildPresenter(): P

    override fun baseInit() {
        super.baseInit()
        mPresenter = buildPresenter()
        mPresenter?.attachView(this as V)
    }

    /*** 显示加载 ***/
    override fun showLoading(message: String) {
        mLoadDialog.show(message)
    }

    /*** 显示自定义错误 ***/
    override fun showError(firstLoad: Boolean, code: Int) {
        mLoadDialog.dismiss()
        if (firstLoad) {
            //只有第一次加载失败才会显示错误假面
        }
    }

    /*** 隐藏全部 ***/
    override fun hideAll() {
        mLoadDialog.dismiss()
    }

    /*** 将请求放入队列 ***/
    override fun addDispose(dispose: Disposable?) {
        addDisposable(dispose)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }
}