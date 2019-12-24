package com.memo.base.base.fragment

import com.memo.base.base.mvp.IPresenter
import com.memo.base.base.mvp.IView

/**
 * title:基础的MVP模式Fragment
 * describe:
 *
 * @author zhou
 * @date 2019-01-28 13:58
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    protected lateinit var mPresenter: P

    /*** 绑定Presenter ***/
    protected abstract fun buildPresenter(): P

    override fun baseInitialize() {
        super.baseInitialize()
        mPresenter = buildPresenter()
	    mPresenter.attach(this as V, this)
    }

}