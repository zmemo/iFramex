package com.memo.test.dialog

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.test.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.dialog.dialog.BaseBottomSheetDialog
import kotlinx.android.synthetic.main.layout_action_dialog.view.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 16:46
 */
class ActionBottomSheetDialog : BaseBottomSheetDialog() {

    override fun bindLayoutRes(): Int = R.layout.layout_action_dialog

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
}