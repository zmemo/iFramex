package com.memo.tool.adapter.recyclerview

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder

/**
 * title:自定义ViewHolder
 * describe:
 *
 * @author zhou
 * @date 2019-08-05 13:43
 */
class ViewHolder(view: View) : BaseViewHolder(view) {

    override fun setText(viewId: Int, value: CharSequence?): BaseViewHolder {
        return super.setText(viewId, value ?: "")
    }
    
    override fun setText(viewId : Int, strId : Int) : BaseViewHolder {
        return try {
            super.setText(viewId, strId)
        } catch (e : Exception) {
            super.setText(viewId, strId.toString())
        }
    }

}