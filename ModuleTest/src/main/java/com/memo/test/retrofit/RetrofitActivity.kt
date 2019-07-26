package com.memo.test.retrofit

import android.annotation.SuppressLint
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.test.R
import com.memo.tool.ext.onClick
import com.memo.tool.utils.GsonHelper
import kotlinx.android.synthetic.main.activity_retrofit.*

@SuppressLint("SetTextI18n")
class RetrofitActivity : BaseMvpActivity<RetrofitContract.View, RetrofitPresenter>(),
    RetrofitContract.View {

    override fun buildPresenter(): RetrofitPresenter = RetrofitPresenter()

    override fun bindLayoutResId(): Int = R.layout.activity_retrofit

    override fun initialize() {
        initListener()
    }

    private fun initListener() {
        mBtnZhiHu.onClick {
            mPresenter?.requestZhiHu()
        }
        mBtnDouBan.onClick { mPresenter?.requestWan() }
    }

    /**
     * 知乎新闻
     * @param response 新闻
     */
    override fun getZhiHu(response: ZhiHuNews) {
        mTvJsonZhiHu.text = GsonHelper.parse2Json(response)
    }

    /**
     * 玩安卓
     * @param response 新闻
     */
    override fun getWan(response: Wan) {
        mTvJsonDouBan.text = GsonHelper.parse2Json(response)
    }
}
