package com.memo.map.location

import android.content.Context
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.blankj.utilcode.util.LogUtils
import com.memo.tool.app.BaseApp


/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-14 14:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object LocationHelper {

    /**
     * 开启单次定位
     * @param onSuccess 成功回调
     * @see AMapLocation https://lbs.amap.com/api/android-l｜ocation-sdk/guide/android-location/getlocation#result
     * 检查模块清单文件中是否填写了高德SDK的apiKey
     */
    @JvmStatic
    fun startOnceLocation(
        context: Context,
        onSuccess: (location: AMapLocation) -> Unit,
        onError: () -> Unit = {}
    ) {
        val client = AMapLocationClient(BaseApp.app.applicationContext)
        client.setLocationListener {
            if (it.errorCode == AMapLocation.LOCATION_SUCCESS) {
                onSuccess(it)
            } else {
                LogUtils.eTag("Location", it.errorInfo)
                onError()
            }
        }
        //设置使用HTTPS进行网络连接
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTPS)
        val option = AMapLocationClientOption()
        //设置单次定位
        option.isOnceLocation = true
        client.setLocationOption(option)
        client.startLocation()
    }
}