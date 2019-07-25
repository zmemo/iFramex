package com.memo.base.ui.mvp

import io.reactivex.disposables.Disposable

/**
 * title: 基础View接口
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:05
 */
interface IView {

    /**
     * 显示加载框
     */
    fun showLoading(message: String = "加载中")

    /**
     * 显示自定义错误
     */
    fun showError(code: Int)

    /**
     * 隐藏全部
     */
    fun hideAll()

    /**
     * 将请求放入队列
     */
    fun addDispose(dispose: Disposable?)
}