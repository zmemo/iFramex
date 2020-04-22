package com.memo.test.ui.bottom

import android.view.MenuItem
import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.ext.toastCenter
import com.memo.test.R
import com.memo.widget.bottomnavigationbar.OnItemChangeListener
import kotlinx.android.synthetic.main.activity_bottom.*


class BottomActivity : BaseActivity() {


    /*** 绑定布局id ***/
    override fun bindLayoutRes(): Int = R.layout.activity_bottom

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mBottomBar.setOnItemChangeListener(object : OnItemChangeListener {
            override fun onItemChanged(menu: MenuItem, position: Int) {
                toastCenter("下标 = $position 标题 = ${menu.title}")
            }
        })

        mTabBar.setOnTabChangedListener {
            toastCenter("下标 = $it")
        }
    }

}
