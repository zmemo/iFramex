package com.memo.base.manager.retrofit

import com.memo.base.api.BaseResponse
import com.memo.base.api.ExceptionHandler
import com.memo.base.ui.mvp.IView
import com.memo.tool.ext.toastCenter
import com.memo.tool.http.exception.ApiException
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-09 10:19
 */


/**
 * 建议为了简化请求方法，可以按照不同接口的具体逻辑进行简化传参，创建多个方法
 */

/**
 * 网络请求处理 对数据源还没有进行转化
 * @param mView View引用
 * @param firstLoad 是否是第一次调用加载界面
 * @param checkResult 是否检查结果
 * @param onSuccess 成功回调
 * @param onFailure 失败回调
 */
private fun <T> Observable<T>.execute(
    mView: IView?,
    firstLoad: Boolean,
    checkResult: Boolean,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    this.subscribe(
        ResponseObserver(
            mView = mView,
            firstLoad = firstLoad,
            checkResult = checkResult,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    )
}

/**
 * 用于界面刷新 加载 加载界面数据
 */
fun <T> Observable<T>.execute(
    mView: IView?,
    firstLoad: Boolean,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    this.subscribe(
        ResponseObserver(
            mView = mView,
            firstLoad = firstLoad,
            checkResult = true,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    )
}


/**
 * 处理页面逻辑 一般用于点击事件提交数据
 */
fun <T> Observable<T>.executeSubmit(
    mView: IView?,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    execute(
        mView = mView,
        firstLoad = false,
        checkResult = true,
        onSuccess = onSuccess,
        onFailure = onFailure
    )
}

/**
 * 对结果不进行任何处理 主要用于通知后台 例如点赞
 */
fun <T> Observable<T>.executeUncheck() {
    execute(
        mView = null,
        firstLoad = false,
        checkResult = false,
        onSuccess = {},
        onFailure = {})
}


/**
 * 数据源转化
 */
fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap {
        if (it.isSuccess()) {
            Observable.just(it.data)
        } else {
            Observable.error(ApiException(it.code, it.message))
        }
    }
}

private class ResponseObserver<T>(
    private val mView: IView?,
    private val firstLoad: Boolean = false,
    private val checkResult: Boolean = true,
    private val onSuccess: (T) -> Unit,
    private val onFailure: (code: Int) -> Unit
) : Observer<T> {
    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(response: T) {
        //成功回调
        if (checkResult) {
            onSuccess(response)
            //mView?.hideAll()
        }
    }

    override fun onError(e: Throwable) {
        //是否检查结果
        if (checkResult) {
            //错误解析
            val exception: ApiException = ExceptionHandler.handleException(e)

            //显示弹窗
	        toastCenter(exception.message)

            //错误回调
            onFailure(exception.code)

            //如果是第一次加载失败
            //加载成功后再去调用接口失败了就不算第一次 这个时候界面已经显示 更改不太好
            //一直失败从未成功仍然算第一次
            //显示错误界面 只有第一次加载的时候才会进行错误界面展示
            //mView?.showError(firstLoad, exception.code)
        }
    }

    override fun onComplete() {}
}
