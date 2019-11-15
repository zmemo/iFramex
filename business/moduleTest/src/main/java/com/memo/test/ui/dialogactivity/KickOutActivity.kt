package com.memo.test.ui.dialogactivity

import com.blankj.utilcode.util.ActivityUtils
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_kick_out.*

/**
 * title:踢出账号的页面
 * describe:
 *
 * @author memo
 * @date 2019-11-14 10:15
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class KickOutActivity : BaseActivity() {

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_kick_out

    /*** 进行初始化操作 ***/
    override fun initialize() {
        overridePendingTransition(R.anim.scale_in, 0)
        mTvNegative.onClick {
            finish()
        }
        mTvPositive.onClick {
            ActivityUtils.finishAllActivities()
        }
    }


    override fun onBackPressed() {}

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.scale_out)
    }
}
