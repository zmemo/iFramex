package com.memo.widget.ninegridview

import androidx.annotation.DrawableRes
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.utils.ImageLoadHelper
import com.memo.widget.R

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-05 15:59
 */
class NineGridViewAdapter : BaseRecyclerAdapter<Any>(R.layout.item_nine_grid_view) {

    private var mAddDrawableRes: Int = R.drawable.ic_pic_add
    private var mDelDrawableRes: Int = R.drawable.ic_pic_del

    override fun converts(helper: ViewHolder, item: Any) {

        ImageLoadHelper.loadImage(mContext, item, helper.getView(R.id.mIvPic))
        helper.setGone(R.id.mIvDel, item != mAddDrawableRes)
            .setImageResource(R.id.mIvDel, mDelDrawableRes)

        helper.addOnClickListener(R.id.mIvPic)
            .addOnClickListener(R.id.mIvDel)
    }

    /**
     * 设置图片资源
     * @param addRes Int 添加图片资源
     * @param delRes Int 删除图片资源
     */
    fun setDrawableRes(@DrawableRes addRes: Int, @DrawableRes delRes: Int) {
        mAddDrawableRes = addRes
        mDelDrawableRes = delRes
    }
}