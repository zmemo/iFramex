package com.memo.test.ui.status

import android.view.View
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:47
 */
object LoaderHelper {

    fun register(view: View, onRetry: () -> Unit): LoadService<*> {
        return LoadSir.getDefault().register(view) {
            onRetry()
        }
    }
}