package com.memo.tool.dialog.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.blankj.utilcode.util.ScreenUtils
import com.memo.tool.R
import com.memo.tool.adapter.BaseRecyclerAdapter
import com.memo.tool.dialog.listener.OnListItemClickListener
import kotlinx.android.synthetic.main.dialog_locate_list.view.*
import razerdp.basepopup.BasePopupWindow

/**
 * title:水平垂直展示控件
 * describe:
 * @see showVertical
 * @see showHorizontal
 *
 * @author zhou
 * @date 2019-05-08 17:04
 */
class LocateListDialog(context: Context, private val data: ArrayList<String> = arrayListOf()) : BasePopupWindow(context) {

    private val mTopToBottomShowAnim by lazy { createVerticalAnimation(-1f, 0f) }
    private val mBottomToTopHideAnim by lazy { createVerticalAnimation(0f, -1f) }

    private val mBottomToTopShowAnim by lazy { createVerticalAnimation(1f, 0f) }
    private val mTopToBottomHideAnim by lazy { createVerticalAnimation(0f, 1f) }

    private val mLeftToRightShowAnim by lazy { createHorizontalAnimation(-1f, 0f) }
    private val mRightToLeftHideAnim by lazy { createHorizontalAnimation(0f, -1f) }

    private val mRightToLeftShowAnim by lazy { createHorizontalAnimation(1f, 0f) }
    private val mLeftToRightHideAnim by lazy { createHorizontalAnimation(0f, 1f) }

    private val mHalfScreenWidth by lazy { ScreenUtils.getScreenWidth() / 2 }
    private val mHalfScreenHeight by lazy { ScreenUtils.getScreenHeight() / 2 }


    /**
     * 点击监听
     */
    private var mListener: OnListItemClickListener? = null

    /**
     * 适配器
     */
    private val mAdapter: BaseRecyclerAdapter<String> =
        object : BaseRecyclerAdapter<String>(R.layout.dialog_locate_list_item) {
            override fun converts(helper: ViewHolder, item: String) {
                helper.setText(R.id.mTvContent, item)
                    .setGone(R.id.mLine, helper.adapterPosition != mData.size)
            }
        }

    init {
        isAutoLocatePopup = true
        initialize()
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_locate_list)
    }

    private fun createVerticalAnimation(fromY: Float, toY: Float): Animation {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            fromY,
            Animation.RELATIVE_TO_SELF,
            toY)
        animation.duration = 300
        animation.interpolator = DecelerateInterpolator()
        return animation
    }

    private fun createHorizontalAnimation(fromX: Float, toX: Float): Animation {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            fromX,
            Animation.RELATIVE_TO_SELF,
            toX,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f)
        animation.duration = 300
        animation.interpolator = DecelerateInterpolator()
        return animation
    }

    /**
     * 初始化
     */
    private fun initialize() {
        contentView.mRvContent.layoutManager = LinearLayoutManager(context)
        contentView.mRvContent.adapter = mAdapter
        contentView.mRvContent.setHasFixedSize(true)
        (contentView.mRvContent.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mAdapter.setNewData(data)
        mAdapter.setOnItemClickListener { _, _, position ->
            mListener?.onItemClick(position, mAdapter.data[position])
            dismiss()
        }
    }

    /**
     * 设置数据源
     */
    fun setData(data: ArrayList<String>): LocateListDialog {
        mAdapter.setNewData(data)
        return this
    }

    /**
     * 在末尾添加一个数据
     */
    fun addData(data: String): LocateListDialog {
        mAdapter.addData(data)
        return this
    }

    /**
     * 可以在中间插入一个数据
     */
    fun addData(position: Int, data: String): LocateListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.addData(position, data)
        }
        return this
    }

    /**
     * 删除一个数据
     */
    fun removeData(position: Int): LocateListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.remove(position)
        }
        return this
    }

    /**
     * 更新某一项的数据
     */
    fun updateData(position: Int, data: String): LocateListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.data[position] = data
            mAdapter.notifyItemChanged(position)
        }
        return this
    }

    /**
     * 设置点击监听
     */
    fun setOnItemClickListener(method: (position: Int, item: String) -> Unit): LocateListDialog {
        mListener = object : OnListItemClickListener {
            /**
             * 条目点击
             */
            override fun onItemClick(position: Int, item: String) {
                method(position, item)
            }
        }
        return this
    }

    /**
     * 垂直方向的显示
     * @param view View
     */
    fun showVertical(view: View) {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        if (location[1] < mHalfScreenHeight) {
            // 控件在上半部分 弹窗显示在下半部分
            showBottom(view)
        } else {
            // 控件在下半部分 弹窗显示在上半部分
            showTop(view)
        }
    }

    /**
     * 水平方向显示
     * @param view View
     */
    fun showHorizontal(view: View) {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        if (location[0] < mHalfScreenWidth) {
            // 控件在左半部分 弹窗显示在右半部分
            showEnd(view)
        } else {
            // 控件在右半部分 弹窗显示在左半部分
            showStart(view)
        }
    }

    private fun showBottom(view: View) {
        popupGravity = Gravity.CENTER or Gravity.BOTTOM
        showAnimation = mTopToBottomShowAnim
        dismissAnimation = mBottomToTopHideAnim
        showPopupWindow(view)
    }

    private fun showTop(view: View) {
        popupGravity = Gravity.CENTER or Gravity.TOP
        showAnimation = mBottomToTopShowAnim
        dismissAnimation = mTopToBottomHideAnim
        showPopupWindow(view)
    }

    private fun showStart(view: View) {
        popupGravity = Gravity.CENTER or Gravity.START
        showAnimation = mRightToLeftShowAnim
        dismissAnimation = mLeftToRightHideAnim
        showPopupWindow(view)
    }

    private fun showEnd(view: View) {
        popupGravity = Gravity.CENTER or Gravity.END
        showAnimation = mLeftToRightShowAnim
        dismissAnimation = mRightToLeftHideAnim
        showPopupWindow(view)
    }
}