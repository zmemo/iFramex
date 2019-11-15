package com.memo.tool.helper

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import java.util.*

/**
 * title:Fragment展示帮助类
 * describe:
 *
 * @author zhou
 * @date 2019-03-29 16:09
 */
class FragmentHelper constructor(containerResId: Int, fragmentManager: FragmentManager) {

    /*** 布局容器id ***/
    private val mContainerResId: Int = containerResId

    /*** Fragment管理 ***/
    private val mFragmentManager: FragmentManager = fragmentManager

    /*** 存放Fragment容器 ***/
    private val mStack: Stack<Fragment> by lazy { Stack<Fragment>() }

    /**
     * 添加Fragment列表
     */
    fun add(vararg fragments: Fragment): FragmentHelper {
        for (fragment in fragments) {
            add(fragment)
        }
        return this
    }

    /**
     *  添加一个Fragment
     */
    @SuppressLint("CommitTransaction")
    fun add(fragment: Fragment): FragmentHelper {
        if (!mStack.contains(fragment)) {
            mStack.add(fragment)
        }
        if (!fragment.isAdded) {
            mFragmentManager.beginTransaction().add(mContainerResId, fragment)
                .setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                .commitAllowingStateLoss()
        }
        return this
    }


    /**
     * 改变Fragment
     */
    fun show(index: Int = 0) {
        val beginTransaction = mFragmentManager.beginTransaction()
        mStack.forEachIndexed { position, fragment ->
            if (position == index) {
                beginTransaction
                    .setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                    .show(fragment)
            } else {
                beginTransaction
                    .hide(fragment)
            }
        }
        beginTransaction.commitAllowingStateLoss()

    }
}