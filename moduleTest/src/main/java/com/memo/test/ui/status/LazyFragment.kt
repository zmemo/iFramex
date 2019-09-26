package com.memo.test.ui.status


import com.kingja.loadsir.core.LoadService
import com.memo.base.ui.fragment.BaseFragment
import com.memo.test.R
import com.memo.tool.helper.toast
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

    override fun bindLayoutResId(): Int = R.layout.fragment_lazy

    override fun initialize() {
        mLoadService = LoaderHelper.register(mTvContainer) {
            toast("点击重试")
            mLoadService?.showSuccess()
        }
    }

    override fun lazyInitialize() {
        mTvContainer.postDelayed({
            mTvContainer.text = "懒加载后获取内容"
            mLoadService?.showSuccess()
        }, 2000)
    }

}
