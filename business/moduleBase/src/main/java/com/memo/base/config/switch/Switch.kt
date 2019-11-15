package com.memo.base.config.switch

/**
 * title:全局配置开关 一些处理操作之后设置为true 操作完成后设置为false
 * describe:
 *
 * @author zhou
 * @date 2019-09-11 17:48
 */
object Switch {

    /**
     * For Example
     * 应用更新在app打开之后直至行一次
     * 更新时候设置isUpdating为true
     * 完成后重置isUpdating为false
     */
    var isUpdating = false
}