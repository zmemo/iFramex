package com.memo.tool.dialog.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.memo.tool.R
import com.memo.tool.adapter.BaseRecyclerAdapter
import com.memo.tool.dialog.listener.OnListItemClickListener
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.dialog_bottom_list.view.*
import razerdp.basepopup.BasePopupWindow


/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 14:23
 */
class BottomListDialog constructor(context: Context, private val data: ArrayList<String> = arrayListOf()) :
    BasePopupWindow(context) {

    /**
     * 适配器
     */
    private val mAdapter: BaseRecyclerAdapter<String> =
        object : BaseRecyclerAdapter<String>(R.layout.dialog_bottom_list_item) {
            override fun converts(helper: ViewHolder, item: String) {
                helper.setText(R.id.mTvContent, item)
                    .setGone(R.id.mLine, helper.adapterPosition != mData.size)
            }
        }

    /**
     * 点击监听
     */
    private var mListener: OnListItemClickListener? = null

    /**
     * 初始化
     */
    init {
        popupGravity = Gravity.BOTTOM
        initialize()
    }

    /**
     * 显示动画
     */
    override fun onCreateShowAnimation(): Animation {
        return getTranslateVerticalAnimation(1f, 0f, 300)
    }

    /**
     * 消失动画
     */
    override fun onCreateDismissAnimation(): Animation {
        return getTranslateVerticalAnimation(0f, 1f, 300)
    }

    /**
     * 创建布局
     */
    override fun onCreateContentView(): View {
        return createPopupById(com.memo.tool.R.layout.dialog_bottom_list)
    }

    /**
     * 初始化
     */
    private fun initialize() {
        contentView.mTvClose.onClick { dismiss() }
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
    fun setData(data: ArrayList<String>): BottomListDialog {
        mAdapter.setNewData(data)
        return this
    }

    /**
     * 在末尾添加一个数据
     */
    fun addData(data: String): BottomListDialog {
        mAdapter.addData(data)
        return this
    }

    /**
     * 可以在中间插入一个数据
     */
    fun addData(position: Int, data: String): BottomListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.addData(position, data)
        }
        return this
    }

    /**
     * 删除一个数据
     */
    fun removeData(position: Int): BottomListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.remove(position)
        }
        return this
    }

    /**
     * 更新某一项的数据
     */
    fun updateData(position: Int, data: String): BottomListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.data[position] = data
            mAdapter.notifyItemChanged(position)
        }
        return this
    }

    /**
     * 设置点击监听
     */
    fun setOnItemClickListener(method: (position: Int, item: String) -> Unit): BottomListDialog {
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
     * 简化展示方法
     */
    fun show() {
        showPopupWindow()
    }
}