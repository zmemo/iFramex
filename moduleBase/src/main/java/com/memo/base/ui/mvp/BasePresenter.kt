package com.memo.base.ui.mvp

/**
 * title:BasePresenter
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:31
 */
abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V> {

    var mView: V? = null

    var mModel: M? = null

    /*** 判断是否是第一次加载数据 只有在请求成功之后才会进行变化***/
    protected var isFirstLoad = true

    /**
     * 判断是否mView还存在
     * Kotlin总直接使用？符来进行判断就好
     */
    protected val isViewAttached: Boolean get() = mView != null

    /**
     * 绑定 View
     */
    override fun attachView(mView: V) {
        this.mView = mView
        mModel = buildModel()
    }

    /**
     * 绑定Model
     */
    protected abstract fun buildModel(): M

    /**
     * 解绑 View
     */
    override fun detachView() {
        this.mView = null
        this.mModel = null
    }
}