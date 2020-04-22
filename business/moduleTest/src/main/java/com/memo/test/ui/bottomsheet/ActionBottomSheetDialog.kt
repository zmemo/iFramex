package com.memo.test.ui.bottomsheet

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.base.tool.adapter.recyclerview.ViewHolder
import com.memo.base.tool.dialog.dialog.BaseBottomSheetDialog
import com.memo.test.R
import kotlinx.android.synthetic.main.dialog_action_bottom_sheet.view.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 16:46
 */
class ActionBottomSheetDialog : BaseBottomSheetDialog() {

    override fun bindLayoutRes(): Int = R.layout.dialog_action_bottom_sheet

    private val mAdapter by lazy {
        object : BaseRecyclerAdapter<String>(R.layout.item_multi_text) {
            override fun converts(helper: ViewHolder, item: String) {
                helper.setText(R.id.tv_title, item)
            }
        }
    }

    override fun initialize() {
        contentView.mRvList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    fun setData(data: MutableList<String>): ActionBottomSheetDialog {
        mAdapter.setNewData(data)
        return this
    }


    fun setOnItemClickListener(onItemClick: (dialog: BaseBottomSheetDialog, position: Int, data: String) -> Unit): ActionBottomSheetDialog {
        mAdapter.setOnItemClickListener { _, _, position ->
            onItemClick(this, position, mAdapter.data[position])
        }
        return this
    }

}