package com.memo.base.ui.mvp

import androidx.lifecycle.LifecycleOwner

/**
 * title: 基础Presenter接口
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:38
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定View和LifecycleOwner
     */
    fun attachView(mView: V, mLifeOwner: LifecycleOwner)

}