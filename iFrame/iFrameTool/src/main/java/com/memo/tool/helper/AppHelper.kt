package com.memo.tool.helper

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.memo.tool.app.BaseApp
import java.util.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-03 15:54
 */
object AppHelper {

    /*** QQ ***/
    private const val PACKAGE_NAME_QQ = "com.tencent.mobileqq"
    /*** 微信 ***/
    private const val PACKAGE_NAME_WECHAT = "com.tencent.mm"
    /*** 支付宝 ***/
    private const val PACKAGE_NAME_ALI_PAY = "com.eg.android.AlipayGphone"
    /*** 应用宝 ***/
    private const val PACKAGE_NAME_TECENT_MARKET = "com.tencent.android.qqdownloader"
    /*** 百度地图 ***/
    private const val PACKAGE_NAME_BAIDU_MAP = "com.baidu.BaiduMap"
    /*** 高德地图 ***/
    private const val PACKAGE_NAME_GAODE_MAP = "com.autonavi.minimap"
    /*** 腾讯地图 ***/
    private const val PACKAGE_NAME_TENCENT_MAP = "com.tencent.map"

    /**
     * 判断微信是否可以用
     *
     * @return true 微信可用 false 微信不可用
     */
    @JvmStatic
    val isWeChatAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_WECHAT)

    /**
     * 判断qq是否可用
     *
     * @return qq可用
     */
    @JvmStatic
    val isQQAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_QQ)

    /**
     * 判断有没有高德地图
     *
     * @return 是否存在高德地图
     */
    @JvmStatic
    val isGaodeMapAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_GAODE_MAP)

    /**
     * 判断百度地图是否可用
     *
     * @return 是否可用
     */
    @JvmStatic
    val isBaiduMapAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_BAIDU_MAP)

    /**
     * 判断腾讯地图是否可用
     *
     * @return 腾讯地图是否可用
     */
    @JvmStatic
    val isTencentMapAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_TENCENT_MAP)

    /**
     * 判断支付宝是否可用
     */
    @JvmStatic
    val isAliPayAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_ALI_PAY)

    /**
     * 判断腾讯应用宝是否可用
     */
    @JvmStatic
    val isTencentMarketAvailable: Boolean
        get() = isAvailable(PACKAGE_NAME_TECENT_MARKET)

    /**
     * 检查手机上是否安装了指定的软件
     */
    private fun isAvailable(packageName: String): Boolean {
        // 获取PackageManager
        val packageManager: PackageManager = BaseApp.app.applicationContext.packageManager
        // 获取所有已安装程序的包信息
        val packageInfoLists: List<PackageInfo> = packageManager.getInstalledPackages(0)
        // 用于存储所有已安装程序的包名
        val packageNames = ArrayList<String>()
        // 从packageInfoLists中将包名字逐一取出，压入packageNames中
        for (packageInfo: PackageInfo in packageInfoLists) {
            packageNames.add(packageInfo.packageName)
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName)
    }
}
