package com.memo.test.share

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_share_from.*

class ShareFromActivity : BaseActivity() {

    override fun bindLayoutResId(): Int = R.layout.activity_share_from

    override fun initialize() {
        mFlSearch.onClick {
            val intent = Intent(mActivity, ShareToActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val pairFl = Pair<View, String>(mFlSearch, mFlSearch.transitionName)
                val pairTv = Pair<View, String>(mTvSearch, mTvSearch.transitionName)
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    mActivity, pairFl, pairTv
                ).toBundle()
                mActivity.startActivity(intent, bundle)
            } else {
                mActivity.startActivity(intent)
            }
        }
    }

}
