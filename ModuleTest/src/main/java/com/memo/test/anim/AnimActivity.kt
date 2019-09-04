package com.memo.test.anim

import android.os.Build
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.ext.onClick
import com.memo.tool.helper.AnimHelper
import kotlinx.android.synthetic.main.activity_anim.*


class AnimActivity : BaseActivity() {

    private var isShown = false
    private var translate = 0
    private var isOpen = false

    private val mOpenAnim by lazy {
        AnimHelper.createRotate3dAnimGo(
            mFlContainer,
            mIv,
            mIvOther,
            200f,
            1000
        )
    }
    private val mCloseAnim by lazy {
        AnimHelper.createRotate3dAnimBack(
            mFlContainer,
            mIv,
            mIvOther,
            200f,
            1000
        )
    }

    override fun bindLayoutResId(): Int = R.layout.activity_anim

    override fun initialize() {
        mBtnCircle.onClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mIv.post {
                    mIv.clearAnimation()
                    isShown = !isShown
                    if (isShown) {
                        AnimHelper.hideCircleRevealAnim(mIv)
                    } else {
                        AnimHelper.showCircleRevealAnim(mIv)
                    }
                }
            }
        }

        mBtnAlpha.onClick {
            mIv.clearAnimation()
            mIv.startAnimation(AnimHelper.createAlphaAnim(0f, 1f, 1500))
        }

        mBtnRotate.onClick {
            mIv.clearAnimation()
            mIv.startAnimation(AnimHelper.createRotateAnim(1500))
        }

        mBtnScale.onClick {
            mIv.clearAnimation()
            mIv.startAnimation(AnimHelper.createScaleAnim(0f, 1f, 1500))
        }

        mBtnTranslate.onClick {
            mIv.clearAnimation()
            translate++
            when (translate % 8) {
                1 -> mIv.startAnimation(AnimHelper.createHTranslateAnim(-1f, 0f, 1500))
                2 -> mIv.startAnimation(AnimHelper.createHTranslateAnim(0f, -1f, 1500))
                3 -> mIv.startAnimation(AnimHelper.createHTranslateAnim(1f, 0f, 1500))
                4 -> mIv.startAnimation(AnimHelper.createHTranslateAnim(0f, 1f, 1500))
                5 -> mIv.startAnimation(AnimHelper.createVTranslateAnim(-1f, 0f, 1500))
                6 -> mIv.startAnimation(AnimHelper.createVTranslateAnim(0f, -1f, 1500))
                7 -> mIv.startAnimation(AnimHelper.createVTranslateAnim(1f, 0f, 1500))
                0 -> mIv.startAnimation(AnimHelper.createVTranslateAnim(0f, 1f, 1500))
            }
        }

        mBtnRotate3D.onClick {
            // 动画开始 还没结束
            if ((mOpenAnim.hasStarted() && mOpenAnim.hasEnded().not()) ||
                (mCloseAnim.hasStarted() && mCloseAnim.hasEnded().not())
            ) {
                return@onClick
            }
            if (isOpen) {
                mFlContainer.startAnimation(mOpenAnim)
            } else {
                mFlContainer.startAnimation(mCloseAnim)
            }
            isOpen = !isOpen
        }
    }
}
