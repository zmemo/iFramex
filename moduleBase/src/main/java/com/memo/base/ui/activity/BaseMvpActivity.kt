package com.memo.base.ui.activity

import com.memo.base.ui.mvp.IPresenter
import com.memo.base.ui.mvp.IView


/**
 * title:基础的Mvp模式Activity
 * describe: 具体的逻辑还是要根据需求进行改变
 *
 * @author zhou
 * @date 2019-01-24 14:08
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    protected lateinit var mPresenter: P

    override fun baseInit() {
        super.baseInit()
        mPresenter = buildPresenter()
        mPresenter.attachView(this as V, this)
    }

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    protected abstract fun buildPresenter(): P

}