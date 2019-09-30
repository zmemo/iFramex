package com.memo.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.memo.tool.dialog.dialog.LoadingDialog
import com.memo.tool.helper.OOMHelper

/**
 * title:基础的Fragment
 * describe:
 * 注意添加 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
 *
 * @author zhou
 * @date 2019-09-26 15:38
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseFragment : Fragment() {

    /*** 根布局 ***/
    protected lateinit var mRootView: View

    /*** 上下文Activity ***/
    protected val mActivity by lazy { activity!! }

    /*** AutoDispose ***/
    protected val mLifecycleOwner: LifecycleOwner by lazy { this }

    /*** 加载弹窗 ***/
    protected val mLoadDialog: LoadingDialog by lazy { LoadingDialog(activity!!) }

    /*** 标识 标识是否界面准备完毕 ***/
    private var isPrepare: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(bindLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRootView = view
        isPrepare = true
        baseInitialize()
        onVisibleToUser()
        initialize()
    }


    private fun onVisibleToUser() {
        if (isPrepare && isResumed) {
            isPrepare = false
            lazyInitialize()
        }
    }

    override fun onResume() {
        if (isPrepare) {
            onVisibleToUser()
        }
        super.onResume()
    }


    /*** 对于BaseMvpFragment的初始化 ***/
    protected open fun baseInitialize() {}

    /*** 绑定布局 ***/
    @LayoutRes
    protected abstract fun bindLayoutResId(): Int

    /*** 在视图加载完毕的时候初始化 ***/
    protected abstract fun initialize()

    /*** 在界面可见的时候进行初始化 ***/
    protected abstract fun lazyInitialize()

    override fun onDestroyView() {
        // 清除所有图片占用的内存
        OOMHelper.onDestroy(mRootView)
        super.onDestroyView()
    }
}