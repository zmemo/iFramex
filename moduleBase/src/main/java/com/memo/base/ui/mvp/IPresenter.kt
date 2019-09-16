package com.memo.base.ui.mvp

/**
 * title: 基础Presenter接口
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:38
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()
}