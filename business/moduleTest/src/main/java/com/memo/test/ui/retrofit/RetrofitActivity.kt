package com.memo.test.ui.retrofit

import android.annotation.SuppressLint
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.test.R
import com.memo.test.entity.Wan
import com.memo.test.entity.ZhiHuNews
import com.memo.tool.ext.onClick
import com.memo.tool.helper.GsonHelper
import kotlinx.android.synthetic.main.activity_retrofit.*

@SuppressLint("SetTextI18n")
class RetrofitActivity : BaseMvpActivity<RetrofitContract.View, RetrofitPresenter>(),
    RetrofitContract.View {

    private var mPresenterA: RetrofitPresenter? = null
    private var mPresenterB: RetrofitPresenter? = null

    override fun buildPresenter(): RetrofitPresenter {
        mPresenterA = RetrofitPresenter()
        mPresenterB = RetrofitPresenter()
        return RetrofitPresenter()
    }

    override fun bindLayoutResId(): Int = R.layout.activity_retrofit

    override fun initialize() {
        initListener()
    }

    private fun initListener() {
        mBtnZhiHu.onClick {
	        mLoadDialog.show()
	        mPresenter.requestZhiHu()
        }
        mBtnDouBan.onClick {
	        mLoadDialog.show()
	        mPresenter.requestWan()
        }
    }

    /**
     * 知乎新闻
     * @param response 新闻
     */
    override fun getZhiHu(response: ZhiHuNews) {
	    mLoadDialog.dismiss()
        mTvJson.text = GsonHelper.parse2Json(response)
    }

    /**
     * 玩安卓
     * @param response 新闻
     */
    override fun getWan(response: Wan) {
	    mLoadDialog.dismiss()
        mTvJson.text = GsonHelper.parse2Json(response)
    }
}
