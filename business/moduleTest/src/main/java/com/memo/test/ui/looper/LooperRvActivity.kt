package com.memo.test.ui.looper

import android.widget.TextView
import androidx.recyclerview.widget.PagerSnapHelper
import com.memo.base.base.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.widget.recyclerview.layoutmanager.LooperLayoutManager
import kotlinx.android.synthetic.main.activity_looper_rv.*

/**
 * title:LooperRecyclerView
 * describe:循环的RecyclerView
 *
 * @author memo
 * @date 2020-03-22 13:32
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LooperRvActivity : BaseActivity() {

    private val data = listOf("床前明月光", "疑是地上霜", "举头望明月", "低头思故乡")

    private val mAdapter by lazy {
        object : BaseRecyclerAdapter<String>(R.layout.layout_item_looper_text) {
            override fun converts(helper: ViewHolder, item: String) {
                (helper.itemView as TextView).text = item
            }
        }
    }

    override fun bindLayoutRes(): Int = R.layout.activity_looper_rv

    override fun initialize() {
        mLooperRv.run {
            layoutManager = LooperLayoutManager()
            mAdapter.setNewData(data)
            adapter = mAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

}
