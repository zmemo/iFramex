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
     * 隐藏加载框
     * 第一次失败显示自定义错误
     */
    fun showError(firstLoad: Boolean, code: Int)

    /**
     * 隐藏全部其他界面图层
     * 显示主内容
     */
    fun hideAll()

    /**
     * 将请求放入队列
     */
    fun addDispose(dispose: Disposable?)
}