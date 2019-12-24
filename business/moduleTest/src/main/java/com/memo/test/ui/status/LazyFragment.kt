package com.memo.test.ui.status


import com.kingja.loadsir.core.LoadService
import com.memo.base.base.fragment.BaseFragment
import com.memo.test.R
import com.memo.tool.ext.delay
import com.memo.tool.ext.toast
import kotlinx.android.synthetic.main.fragment_lazy.*

/**
 * title:懒加载页面Fragment
 * describe:
 *
 * @author zhou
 * @date 2019-09-26 15:00
 *
 * Talk is cheap, Show me the code.
 */
class LazyFragment : BaseFragment() {

    private var mLoadService: LoadService<*>? = null
	
	override fun bindLayoutRes() : Int = R.layout.fragment_lazy

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        mLoadService = LoaderHelper.register(mTvContainer) {
            toast("点击重试")
            mLoadService?.showSuccess()
        }
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
	    delay(mLifecycleOwner, 2000) {
            mTvContainer.text = "懒加载后获取内容"
            mLoadService?.showSuccess()
	    }
    }

}
