package com.memo.test.bottom

import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.helper.toast
import kotlinx.android.synthetic.main.activity_bottom.*


class BottomActivity : BaseActivity() {


    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_bottom

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mBottomBar.setOnItemChangeListener { menuItem, position ->
            toast("下标 = $position 标题 = ${menuItem.title}")
        }

    }

}
