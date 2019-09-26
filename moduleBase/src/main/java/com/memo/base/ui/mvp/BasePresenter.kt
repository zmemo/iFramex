package com.memo.base.ui.mvp

import androidx.lifecycle.LifecycleOwner

/**
 * title:BasePresenter
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:31
 */
abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V> {

    lateinit var mView: V

    lateinit var mModel: M

    /*** AutoDispose ***/
    lateinit var mLifeOwner: LifecycleOwner

    /*** 判断是否是第一次加载数据 只有在请求成功之后才会进行变化***/
    protected var isFirstLoad = true

    /**
     * 绑定 View
     */
    override fun attachView(mView: V, mLifeOwner: LifecycleOwner) {
        this.mView = mView
        this.mLifeOwner = mLifeOwner
        mModel = buildModel()
    }

    /**
     * 绑定Model
     */
    protected abstract fun buildModel(): M

}