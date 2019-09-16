package com.memo.base.ui.activity

import com.memo.base.ui.mvp.IPresenter
import com.memo.base.ui.mvp.IView
import io.reactivex.disposables.Disposable

/**
 * title:基础的Mvp模式Activity
 * describe: 具体的逻辑还是要根据需求进行改变
 *
 * @author zhou
 * @date 2019-01-24 14:08
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    protected var mPresenter: P? = null

    override fun baseInit() {
        super.baseInit()
        mPresenter = buildPresenter()
        mPresenter?.attachView(this as V)
    }

    /*** 绑定Presenter ***/
    protected abstract fun buildPresenter(): P

    /*** 将请求放入队列 ***/
    override fun addDispose(dispose: Disposable?) {
        addDisposable(dispose)
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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }
}