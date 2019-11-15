package com.memo.map.infowindow

import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-15 14:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object InfoWindowOverlay {

    fun showInfoWindow(aMap: AMap, lat: Double, lng: Double, content: String) {
        //隐藏logo
        aMap.uiSettings.setLogoBottomMargin(-100)
        //隐藏缩放
        aMap.uiSettings.isZoomControlsEnabled = false
        //禁止缩放
        aMap.uiSettings.setAllGesturesEnabled(false)
        val latLng = LatLng(lat, lng)
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        val option = MarkerOptions()
        option.position(latLng)
            .title(content)
            .icon(null)
        val marker = aMap.addMarker(option)
        marker.showInfoWindow()
    }
}