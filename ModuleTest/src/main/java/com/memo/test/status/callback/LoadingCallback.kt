package com.memo.test.status.callback

import com.kingja.loadsir.callback.Callback
import com.memo.test.R

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:28
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int = R.layout.status_loading_view
}