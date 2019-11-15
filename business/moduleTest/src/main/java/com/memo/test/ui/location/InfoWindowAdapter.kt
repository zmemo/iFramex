package com.memo.test.ui.location

import android.content.Context
import android.view.View
import com.amap.api.maps.model.Marker
import com.memo.map.infowindow.BaseInfoWindowAdapter
import com.memo.test.R
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.layout_info_window.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-15 11:14
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class InfoWindowAdapter(context: Context) : BaseInfoWindowAdapter(context) {

    private var mListener: OnItemClickListener? = null

    override fun bindLayoutResId(): Int = R.layout.layout_info_window

    override fun convert(marker: Marker, mRootView: View) {
        mRootView.mTvAddress.text = marker.title
        mRootView.mTvNavi.onClick { mListener?.onNavi() }
        mRootView.mTvCall.onClick { mListener?.onCall() }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    interface OnItemClickListener {
        fun onNavi()
        fun onCall()
    }

}