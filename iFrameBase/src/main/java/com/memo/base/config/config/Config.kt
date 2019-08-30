package com.memo.base.config.config

/**
 * title:App全局配置 控制器
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 16:44
 */
object Config {
    /**
     * 是否开启日志打印
     */
    const val isOpenLog: Boolean = true

    /**
     * 是否是测试版本
     * 注意 如果不是测试 不是预发布 那么就是线上发布版本
     */
    const val isDebug: Boolean = true

    /**
     * 是否是预发布版本
     */
    const val isPreRelease: Boolean = false

    /**
     * 导航页更新的版本 对应更新导航页的app版本
     */
    const val guideCode = 1000
}