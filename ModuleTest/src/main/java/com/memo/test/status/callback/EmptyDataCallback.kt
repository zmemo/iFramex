package com.memo.test.status.callback

import com.kingja.loadsir.callback.Callback
import com.memo.test.R

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:32
 */
class EmptyDataCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_status_data_empty_view
}