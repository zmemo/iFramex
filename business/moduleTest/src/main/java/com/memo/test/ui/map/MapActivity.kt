package com.memo.test.ui.map

import com.memo.base.base.activity.BaseActivity
import com.memo.base.manager.router.RouterManager
import com.memo.base.tool.ext.onClick
import com.memo.base.tool.helper.NavigationHelper
import com.memo.test.R
import kotlinx.android.synthetic.main.activity_map.*


class MapActivity : BaseActivity() {

    private val lon = 120.1828765869
    private val lat = 30.2430523593
    private val address = "杭州东站"

    /**
     * 绑定布局id
     */
    override fun bindLayoutRes() : Int = R.layout.activity_map

    /**
     * 进行初始化操作
     */
    override fun initialize() {
        mBtnGaode.onClick { NavigationHelper.naviGaodeMap(lat, lon, address) }
        val latLon = NavigationHelper.gcj02_To_Bd09(lat, lon)
        mBtnBaidu.onClick { NavigationHelper.naviBaiduMap(latLon[0], latLon[1], address) }
        mBtnTencent.onClick { NavigationHelper.naviTencentMap(lat, lon, address) }
        mBtnWeb.onClick {
	        RouterManager.startWebActivity(NavigationHelper.getGaodeWebUrl(lat, lon, address), address)
        }
    }
}
