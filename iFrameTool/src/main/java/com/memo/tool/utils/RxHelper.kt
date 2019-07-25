package com.memo.tool.helper

import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * title:线程切换
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:10
 */
object RxHelper {

    /**
     * 进行子线程和主线程的切换
     *
     * @param <T> 返回类型的范型
     * @return ObservableTransformer
     */
    @JvmStatic
    fun <T> io2Main(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 进行子线程和主线程的切换
     *
     * @param <T> 返回类型的范型
     * @return ObservableTransformer
     */
    @JvmStatic
    fun <T> main2Io(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
        }
    }

    /**
     * 进行计算线程和主线程的切换
     *
     * @param <T> 返回类型的范型
     * @return ObservableTransformer
     */
    @JvmStatic
    fun <T> computation2Main(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 进行计算线程和主线程的切换
     *
     * @param <T> 返回类型的范型
     * @return ObservableTransformer
     */
    @JvmStatic
    fun <T> main2Computation(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.computation())
        }
    }

    /**
     * 绑定生命周期
     */
    @JvmStatic
    fun <T, E> lifecycle(lifecycleProvider: LifecycleProvider<E>, e: E): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.bindUntilEvent(lifecycleProvider, e)
        }
    }

    /**
     * 创建一个RxJava事件
     */
    @JvmStatic
    fun <T> create(func: () -> T): Observable<T> {
        return Observable.create<T> {
            it.onNext(func())
        }
    }

    /**
     * 创建一个RxJava事件
     */
    @JvmStatic
    fun <T> create(item: T): Observable<T> {
        return Observable.just(item)
    }
}
