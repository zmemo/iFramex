package com.memo.question.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.base.activity.BaseActivity
import com.memo.question.R
import com.memo.question.q1.Q1Activity
import com.memo.question.q2.OneActivity
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.ext.startActivity
import kotlinx.android.synthetic.main.fragment_q1.*

class QuestionMainActivity : BaseActivity() {

    /*** 绑定布局id ***/
    override fun bindLayoutRes(): Int = R.layout.activity_question_main

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
            mAdapter.setNewData(mData)
        }

        mAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> startActivity<Q1Activity>()
                1 -> startActivity<OneActivity>()
            }
        }
    }

    private val mAdapter = object : BaseRecyclerAdapter<String>(R.layout.item_question_main) {
        override fun converts(helper: ViewHolder, item: String) {
            helper.setText(R.id.mTvTitle, item)
        }
    }

    private val mData = arrayListOf(
        "Fragment销毁与恢复",
        "singleInstance的尝试"
    )


}
