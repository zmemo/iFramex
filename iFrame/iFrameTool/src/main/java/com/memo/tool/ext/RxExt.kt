package com.memo.tool.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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
fun <T> Observable<T>.io2Main(): Observable<T> =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


/**
 * 绑定生命周期
 */
fun <T> Observable<T>.bindLifecycle(
    owner: LifecycleOwner,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): ObservableSubscribeProxy<T> =
    this.`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner, event)))


/**
 * 切换线程并且绑定生命周期
 */
fun <T> Observable<T>.io2MainLifecycle(owner: LifecycleOwner): ObservableSubscribeProxy<T> =
    this.io2Main().bindLifecycle(owner)


/**
 * 使用RxJava2进行延时操作
 * @param lifecycleOwner 传入生命周期
 * @param milliseconds 延时时间
 * @param onNext 延时操作
 */
fun delay(lifecycleOwner: LifecycleOwner, milliseconds: Long, onNext: (second: Long) -> Unit) {
    Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
        .io2MainLifecycle(lifecycleOwner)
        .subscribe(onNext)
}

/**
 * 在后台执行任务
 * @param lifecycleOwner 生命周期绑定
 * @param doInBackground 后台执行任务
 * @param onSuccess 成功回调
 * @param onFailure 失败回调
 */
fun <T> doInBackgroundExt(
    lifecycleOwner: LifecycleOwner,
    doInBackground: () -> T,
    onSuccess: (t: T) -> Unit,
    onFailure: (error: Throwable) -> Unit
) {
    Observable.create<T> { it.onNext(doInBackground()) }
        .io2MainLifecycle(lifecycleOwner)
        .subscribe(onSuccess, onFailure)
}

/**
 * 在后台执行任务不计较结果
 * @param lifecycleOwner 生命周期绑定
 * @param doInBackground 后台执行任务
 */
fun doInBackgroundExt(
    lifecycleOwner: LifecycleOwner,
    doInBackground: () -> Unit
) {
    Observable.create<Unit> { it.onNext(doInBackground()) }
        .io2MainLifecycle(lifecycleOwner)
        .subscribe()
}

/**
 * 在后台执行任务
 * @param doInBackground 后台执行任务
 * @param onSuccess 成功回调
 * @param onFailure 失败回调
 */
fun <T> LifecycleOwner.doInBackground(
    doInBackground: () -> T,
    onSuccess: (t: T) -> Unit,
    onFailure: (error: Throwable) -> Unit
) {
    doInBackgroundExt(this, doInBackground, onSuccess, onFailure)
}

/**
 * 在后台执行任务不计较结果
 * @param doInBackground 后台执行任务
 */
fun LifecycleOwner.doInBackground(doInBackground: () -> Unit) {
    doInBackgroundExt(this, doInBackground)
}

/**
 * 创建一个RxJava2输入流
 */
fun <T> T.toObservable() = Observable.create<T> {
    it.onNext(this)
}
