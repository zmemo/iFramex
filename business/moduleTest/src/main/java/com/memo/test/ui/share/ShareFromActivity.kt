package com.memo.test.ui.share

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.memo.base.base.activity.BaseActivity
import com.memo.base.tool.ext.onClick
import com.memo.test.R
import kotlinx.android.synthetic.main.activity_share_from.*

class ShareFromActivity : BaseActivity() {
	
	override fun bindLayoutRes() : Int = R.layout.activity_share_from

    override fun initialize() {
        mFlSearch.onClick {
            val intent = Intent(mContext, ShareToActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val pairFl = Pair<View, String>(mFlSearch, mFlSearch.transitionName)
                val pairTv = Pair<View, String>(mTvSearch, mTvSearch.transitionName)
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    mContext, pairFl, pairTv
                ).toBundle()
                mContext.startActivity(intent, bundle)
            } else {
                mContext.startActivity(intent)
            }
        }
    }

}
