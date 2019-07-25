package com.memo.ui.web

import android.view.KeyEvent
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.just.agentweb.AgentWeb
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.activity.BaseActivity
import com.memo.tool.ext.gone
import com.memo.ui.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * title:网页界面
 * describe:
 *
 * @author zhou
 * @date 2019-07-23 17:38
 */
@Route(path = RouterPath.Ui.WebActivity)
class WebActivity : BaseActivity() {

    /*** 网址 ***/
    private var url = ""

    /*** 页面标题 ***/
    private var title = ""

    private lateinit var mAgentWeb: AgentWeb


    override fun bindLayoutResId(): Int = R.layout.activity_web

    override fun initialize() {
        initData()
        initView()
    }

    private fun initData() {
        url = intent.getStringExtra("url") ?: url
        title = intent.getStringExtra("title") ?: title
    }

    private fun initView() {

        if (title.isEmpty()) {
            mTitleView.gone()
        } else {
            mTitleView.setTitle(title)
        }

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mFlContainer, FrameLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

}
