package com.memo.test.retrofit

import com.memo.base.ui.mvp.IPresenter
import com.memo.base.ui.mvp.IView

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 17:07
 */
interface RetrofitContract {

    interface Presenter : IPresenter<View> {
        fun requestZhiHu()
        fun requestWan()
    }

    interface View : IView {
        /**
         * 知乎新闻
         * @param response 新闻
         */
        fun getZhiHu(response: ZhiHuNews)

        /**
         * 玩安卓
         * @param response 新闻
         */
        fun getWan(response: Wan)
    }
}