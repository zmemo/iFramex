package com.memo.base.api

import com.memo.base.ui.mvp.IView
import com.memo.tool.helper.RxHelper
import com.memo.tool.http.exception.ApiException
import com.memo.tool.utils.toastCenter
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
 * @param showLoading 是否显示加载框
 * @param loadingMessage 加载提示文字
 * @param disposeOnDestroy 是否绑定生命周期直到销毁
 * @param checkResult 是否检查结果
 * @param onSuccess 成功回调
 * @param onFailure 失败回调
 */
fun <T> Observable<BaseResponse<T>>.execute(
    mView: IView?,
    firstLoad: Boolean = false,
    showLoading: Boolean = true,
    loadingMessage: String = "加载中",
    disposeOnDestroy: Boolean = true,
    checkResult: Boolean = true,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    this.convert()
        .subscribe(
            ResponseObserver(
                mView,
                firstLoad,
                showLoading,
                loadingMessage,
                disposeOnDestroy,
                checkResult,
                onSuccess,
                onFailure)
        )
}

/**
 * 用于界面刷新 加载 加载界面数据
 */
fun <T> Observable<BaseResponse<T>>.execute(
    mView: IView?,
    firstLoad: Boolean,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    this.convert()
        .subscribe(
            ResponseObserver(
                mView,
                firstLoad = firstLoad,
                showLoading = false,
                loadingMessage = "",
                disposeOnDestroy = true,
                checkResult = true,
                onSuccess = onSuccess,
                onFailure = onFailure)
        )
}


/**
 * 处理页面逻辑 一般用于点击事件提交数据
 */
fun <T> Observable<BaseResponse<T>>.executeSubmit(
    mView: IView?,
    loadingMessage: String,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    execute(
        mView = mView,
        firstLoad = false,
        showLoading = true,
        loadingMessage = loadingMessage,
        disposeOnDestroy = true,
        checkResult = true,
        onSuccess = onSuccess,
        onFailure = onFailure)
}

/**
 * 对结果不进行任何处理 主要用于通知后台 例如点赞
 */
fun <T> Observable<BaseResponse<T>>.executeNotCheck() {
    execute(null,
        firstLoad = false,
        showLoading = false,
        loadingMessage = "",
        disposeOnDestroy = false,
        checkResult = false,
        onSuccess = {},
        onFailure = {})
}


/**
 * 网络请求处理 已经对数据源进行转化 适合刚进入页面多个请求zip合并
 * @param mView View引用
 * @param onSuccess 成功回调
 * @param onFailure 失败回调
 */
fun <T> Observable<T>.executeZip(
    mView: IView?,
    onSuccess: (T) -> Unit,
    onFailure: (code: Int) -> Unit
) {
    this.subscribe(
        ResponseObserver(
            mView,
            firstLoad = true,
            showLoading = false,
            loadingMessage = "加载中",
            disposeOnDestroy = true,
            checkResult = true,
            onSuccess = onSuccess,
            onFailure = onFailure)
    )
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
    }.compose(RxHelper.io2Main())
}

private class ResponseObserver<T>(
    private val mView: IView?,
    private val firstLoad: Boolean = false,
    private val showLoading: Boolean = true,
    private val loadingMessage: String = "加载中",
    private val disposeOnDestroy: Boolean = true,
    private val checkResult: Boolean = true,
    private val onSuccess: (T) -> Unit,
    private val onFailure: (code: Int) -> Unit
) : Observer<T> {
    override fun onSubscribe(d: Disposable) {
        //显示加载框
        if (showLoading) {
            mView?.showLoading(loadingMessage)
        }
        //绑定生命周期直到销毁
        if (disposeOnDestroy) {
            mView?.addDispose(d)
        }
    }

    override fun onNext(response: T) {
        //成功回调
        if (checkResult) {
            onSuccess(response)
        }
    }

    override fun onError(e: Throwable) {
        //错误解析
        val exception: ApiException = ExceptionHandler.handleException(e)
        //是否检查结果
        if (checkResult) {
            //显示弹窗
            toastCenter(exception.message)
            //错误回调
            onFailure(exception.code)
            //如果是第一次加载失败
            //加载成功后再去调用接口失败了就不算第一次 这个时候界面已经显示 更改不太好
            //一直失败从未成功仍然算第一次
            if (firstLoad) {
                //显示错误界面
                mView?.showError(exception.code)
            }
        }
    }

    override fun onComplete() {
        //英藏全部
        mView?.hideAll()
    }
}
