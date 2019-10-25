package com.memo.test.ui.recyclerview.resume

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.tool.DataFactory
import com.memo.test.ui.recyclerview.SectionItem
import kotlinx.android.synthetic.main.activity_expand.*

class RecyclerViewResumeActivity : BaseActivity() {

    private val mAdapter by lazy { RootAdapter() }

    private val image = DataFactory.provideImage()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_recycler_resume

    /*** 进行初始化操作 ***/
    override fun initialize() {

        val data = arrayListOf<ArrayList<SectionItem>>()
        (0..10).forEach { _ ->
            val list = arrayListOf<SectionItem>()
            (0..10).forEachIndexed { index, item ->
                list.add(SectionItem("内容$index", image))
            }
            data.add(list)
        }

        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            mAdapter.bindToRecyclerView(mRvList)
            mAdapter.setNewData(data)
            adapter = mAdapter
        }
    }

}
