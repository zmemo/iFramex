package com.memo.test.ui.location

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.memo.base.ui.activity.BaseActivity
import com.memo.map.infowindow.InfoWindowOverlay
import com.memo.map.location.LocationHelper
import com.memo.test.R
import com.memo.tool.helper.toast
import kotlinx.android.synthetic.main.activity_location.*

/**
 * title:定位
 * describe:
 *
 * @author memo
 * @date 2019-11-15 09:32
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LocationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMap.onCreate(savedInstanceState)
    }

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_location

    /*** 进行初始化操作 ***/
    override fun initialize() {
        val aMap = mMap.map
        val adapter = InfoWindowAdapter(mContext)
        aMap.setInfoWindowAdapter(adapter)
        adapter.setOnItemClickListener(object : InfoWindowAdapter.OnItemClickListener {
            override fun onNavi() {
                toast("点击导航")
            }

            override fun onCall() {
                toast("拨打电话")
            }
        })

        mLoadDialog.show("定位中")
        LocationHelper.startOnceLocation(mContext, {
            mLoadDialog.dismiss()
            LogUtils.iTag("location", it.toString())
            mTvLocation.text = it.toString()
            InfoWindowOverlay.showInfoWindow(aMap, it.latitude, it.longitude, it.address)
        })
    }

    override fun onResume() {
        super.onResume()
        mMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMap.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMap.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMap.onSaveInstanceState(outState)
    }

}
