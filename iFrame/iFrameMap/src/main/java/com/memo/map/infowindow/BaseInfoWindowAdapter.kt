package com.memo.map.infowindow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import com.amap.api.maps.AMap
import com.amap.api.maps.model.Marker

/**
 * title:自定义InfoWindow
 * describe:
 * 注意点
 * a.最外层布局的宽度 wrap_content
 * b.最外层的背景必须是透明色
 *
 * @author memo
 * @date 2019-11-15 11:06
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseInfoWindowAdapter(val context: Context) : AMap.InfoWindowAdapter {

    override fun getInfoWindow(maker: Marker): View {
        val view = LayoutInflater.from(context).inflate(bindLayoutResId(), null)
        convert(maker, view)
        return view
    }

    override fun getInfoContents(maker: Marker): View? = null

    @LayoutRes
    abstract fun bindLayoutResId(): Int

    abstract fun convert(marker: Marker, mRootView: View)


}