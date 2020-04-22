package com.memo.base.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.helper.OOMHelper

/**
 * title:基础的Fragment
 * describe:
 * 注意添加 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
 * 建议配合 BaseFragmentPagerAdapter 和 FragmentHelper来进行使用
 *
 * @author zhou
 * @date 2019-09-26 15:38
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseFragment : Fragment() {
	
	/*** 根布局 ***/
	protected lateinit var mRootView : View
	
	/*** 上下文Activity ***/
	protected val mActivity by lazy { activity!! }
	
	/*** AutoDispose ***/
	protected val mLifecycleOwner : LifecycleOwner by lazy { this }
	
	/*** 标识 标识是否界面准备完毕 ***/
	private var isPrepared : Boolean = false
	
	override fun onCreateView(
		inflater : LayoutInflater,
		container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {
		return inflater.inflate(bindLayoutRes(), container, false)
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mRootView = view
		isPrepared = true
		baseInitialize()
		initialize()
		onVisibleToUser()
	}
	
	/*** 对于BaseMvpFragment的初始化 ***/
	protected open fun baseInitialize() {}
	
	private fun onVisibleToUser() {
		if (isPrepared && isResumed) {
			isPrepared = false
			lazyInitialize()
		}
	}
	
	override fun onResume() {
		if (isPrepared) {
			onVisibleToUser()
		}
		super.onResume()
	}
	
	protected fun showLoading(tip : String = "加载中") {
		if (mActivity is BaseActivity) {
			(mActivity as BaseActivity).showLoading(tip)
		}
	}
	
	protected fun hideLoading() {
		if (mActivity is BaseActivity) {
			(mActivity as BaseActivity).hideLoading()
		}
	}
	
	/*** 绑定布局 ***/
	@LayoutRes
	protected abstract fun bindLayoutRes() : Int
	
	/*** 在视图加载完毕的时候初始化 ***/
	protected abstract fun initialize()
	
	/*** 在界面可见的时候进行初始化 ***/
	protected abstract fun lazyInitialize()
	
	override fun onDestroyView() {
		super.onDestroyView()
		// 清除所有图片占用的内存
		OOMHelper.onDestroy(mRootView)
	}
}