package com.memo.test.status

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
object LoadSirService {
    fun service(view: View, onRetry: () -> Unit): LoadService<*> {
        return LoadSir.getDefault().register(view) {
            onRetry()
        }
    }
}