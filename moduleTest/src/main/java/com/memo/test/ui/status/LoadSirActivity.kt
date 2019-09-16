package com.memo.test.ui.status

import com.kingja.loadsir.core.LoadService
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.test.ui.status.callback.EmptyDataCallback
import com.memo.test.ui.status.callback.NetErrorCallback
import com.memo.test.ui.status.callback.ServerErrorCallback
import com.memo.tool.ext.onClick
import com.memo.tool.handler.WeakHandler
import kotlinx.android.synthetic.main.activity_load_sir.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:23
 */
class LoadSirActivity : BaseActivity() {

    private val mHandler = WeakHandler()
    private lateinit var mLoadService: LoadService<*>

    override fun bindLayoutResId(): Int = R.layout.activity_load_sir

    override fun initialize() {
        initView()
        initListener()
        start()
    }

    private fun initView() {
        mLoadService = LoaderHelper.register(mTvContent) {
            mLoadDialog.show()
            mHandler.postDelayed({
                mLoadDialog.dismiss()
                mLoadService.showSuccess()
            }, 2000)
        }
    }

    private fun initListener() {
        mBtnLoad.onClick {
            mLoadDialog.show()
            mHandler.postDelayed({
                mLoadDialog.dismiss()
            }, 2000)
        }
        mBtnEmpty.onClick { mLoadService.showCallback(EmptyDataCallback::class.java) }
        mBtnNetError.onClick { mLoadService.showCallback(NetErrorCallback::class.java) }
        mBtnServerError.onClick { mLoadService.showCallback(ServerErrorCallback::class.java) }
    }

    private fun start() {
        mHandler.postDelayed({
            mLoadService.showSuccess()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.onDestroy()
    }
}