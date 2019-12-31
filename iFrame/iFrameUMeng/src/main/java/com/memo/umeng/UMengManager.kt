package com.memo.umeng

import android.content.Context
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-14 16:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class UMengManager {

    // 文档地址 https://developer.umeng.com/docs/66632/detail/66639
	// QQ   https://connect.qq.com/
	// 微信 https://open.weixin.qq.com/
	// 微博 https://open.weibo.com/
	// 支付宝 https://open.alipay.com/platform/home.htm


    private object Holder {
        val instance = UMengManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 初始化
     * @param context ApplicationContext
     * @param umengKey 友盟的key
     */
    fun init(context: Context, umengKey: String): UMengManager {
        UMConfigure.init(context, umengKey, "umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        return this
    }

    /**
     * 注册微信
     * @param appId 例子：wxdc1e388c3822c80b
     * @param appKey 例子：3baf1193c85774b3fd9d18447d76cab0
     */
    fun registerWeChat(appId: String, appKey: String): UMengManager {
        PlatformConfig.setWeixin(appId, appKey)
        return this
    }

    /**
     * 注册QQ
     * @param appId 例子：100424468
     * @param appKey 例子：c7394704798a158208a74ab60104f0ba
     */
    fun registerQQ(appId: String, appKey: String): UMengManager {
        PlatformConfig.setQQZone(appId, appKey)
        return this
    }

    /**
     * 注册新浪微博
     * @param appId 例子：3921700954
     * @param appKey 例子：04b48b094faeb16683c32669824ebdad
     * @param callbackUrl 例子：http://sns.whalecloud.com
     */
    fun registerWeiBo(appId: String, appKey: String, callbackUrl: String): UMengManager {
        PlatformConfig.setSinaWeibo(appId, appKey, callbackUrl)
        return this
    }

}