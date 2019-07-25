package com.memo.tool.dialog.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.memo.tool.adapter.BaseRecyclerAdapter
import com.memo.tool.dialog.listener.OnGridItemClickListener
import com.memo.tool.R
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.dialog_bottom_list.view.*
import razerdp.basepopup.BasePopupWindow

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-03 17:34
 */
class BottomGridDialog constructor(context: Context, data: ArrayList<GridItem> = arrayListOf()) :
    BasePopupWindow(context) {

    class GridItem(var drawableSrc: Int, var name: String, var key: Any)

    /**
     * 数据源 不过不用 一般都是用mAdapter.data
     */
    private var mData: ArrayList<GridItem> = data

    /**
     * 适配器
     */
    private val mAdapter: BaseRecyclerAdapter<GridItem> =
        object : BaseRecyclerAdapter<GridItem>(R.layout.dialog_bottom_grid_item) {
            override fun converts(helper: ViewHolder, item: GridItem) {
                val itemView: TextView = helper.itemView as TextView
                itemView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, item.drawableSrc, 0, 0)
                itemView.text = item.name
            }
        }

    /**
     * 点击监听
     */
    private var mListener: OnGridItemClickListener? = null

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
        return createPopupById(R.layout.dialog_bottom_list)
    }

    /**
     * 初始化
     */
    private fun initialize() {
        contentView.mTvClose.onClick { dismiss() }
        contentView.mRvContent.layoutManager = GridLayoutManager(context, 3)
        contentView.mRvContent.adapter = mAdapter
        mAdapter.setNewData(mData)
        mAdapter.setOnItemClickListener { _, _, position ->
            mListener?.onItemClick(position, mAdapter.data[position])
            dismiss()
        }
    }

    /**
     * 设置数据源
     */
    fun setData(data: ArrayList<GridItem>): BottomGridDialog {
        mAdapter.setNewData(data)
        return this
    }

    /**
     * 在末尾添加一个数据
     */
    fun addData(data: GridItem): BottomGridDialog {
        mAdapter.addData(data)
        return this
    }

    /**
     * 可以在中间插入一个数据
     */
    fun addData(position: Int, data: GridItem): BottomGridDialog {
        if (position < mAdapter.data.size) {
            mAdapter.addData(position, data)
        }
        return this
    }

    /**
     * 删除一个数据
     */
    fun removeData(position: Int): BottomGridDialog {
        if (position < mAdapter.data.size) {
            mAdapter.remove(position)
        }
        return this
    }

    /**
     * 更新某一项的数据
     */
    fun updateData(position: Int, data: GridItem): BottomGridDialog {
        if (position < mAdapter.data.size) {
            mAdapter.data[position] = data
            mAdapter.notifyItemChanged(position)
        }
        return this
    }

    /**
     * 设置点击监听
     */
    fun setOnItemClickListener(method: (position: Int, item: GridItem) -> Unit): BottomGridDialog {
        mListener = object : OnGridItemClickListener {
            /**
             * 条目点击
             */
            override fun onItemClick(position: Int, item: GridItem) {
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