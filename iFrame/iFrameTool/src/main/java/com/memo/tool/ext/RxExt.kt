package com.memo.tool.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-25 15:24
 *
 * Talk is cheap, Show me the code.
 */

/**
 * 线程切换 io -> main
 */
fun <T> Observable<T>.io2Main(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * 绑定生命周期
 */
fun <T> Observable<T>.bindLifecycle(owner: LifecycleOwner, event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY): ObservableSubscribeProxy<T> {
    return this.`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner, event)))
}

/**
 * 切换线程并且绑定生命周期
 */
fun <T> Observable<T>.io2MainLifecycle(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.io2Main().bindLifecycle(owner)
}

/**
 * 在后台执行任务
 * @param lifecycleOwner 生命周期绑定
 * @param doInBackground 后台执行任务
 * @param onSuccess 成功回调
 * @param onError 失败回调
 */
fun <T> doInBackground(
    lifecycleOwner: LifecycleOwner,
    doInBackground: () -> T,
    onSuccess: (t: T) -> Unit,
    onError: (error: Throwable) -> Unit
) {
    Observable.just(doInBackground())
        .io2MainLifecycle(lifecycleOwner)
        .subscribe({
            onSuccess(it)
        }, {
            onError(it)
        })
}

/**
 * 在后台执行任务
 * @param lifecycleOwner 生命周期绑定
 * @param doInBackground 后台执行任务
 */
fun doInBackground(
    lifecycleOwner: LifecycleOwner,
    doInBackground: () -> Unit
) {
    Observable.just(doInBackground())
        .io2MainLifecycle(lifecycleOwner)
        .subscribe()
}