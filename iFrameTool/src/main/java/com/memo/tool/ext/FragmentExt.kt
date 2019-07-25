package com.memo.tool.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * title:Fragment的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 15:00
 */

/**
 * 在Activity中进行Fragment的增加 显示 隐藏 显示隐藏 替换 移除
 */
fun FragmentActivity.addFragment(layoutId: Int, vararg fragments: Fragment) {
    val beginTransaction = supportFragmentManager.beginTransaction()
    for (fragment in fragments) {
        beginTransaction.add(layoutId, fragment)
    }
    beginTransaction.commitAllowingStateLoss()
}

fun FragmentActivity.showFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .show(fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.hideFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .hide(fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.showHideFragment(showFragment: Fragment, hideFragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .show(showFragment)
        .hide(hideFragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.replaceFragment(layoutId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(layoutId, fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .remove(fragment)
        .commitAllowingStateLoss()
}

/**
 * 在Fragment中进行Fragment的增加 显示 隐藏 显示隐藏 替换 移除
 */
fun Fragment.addFragment(layoutId: Int, vararg fragments: Fragment) {
    val beginTransaction = childFragmentManager.beginTransaction()
    for (fragment in fragments) {
        beginTransaction.add(layoutId, fragment)
    }
    beginTransaction.commitAllowingStateLoss()
}

fun Fragment.hideFragment(fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .hide(fragment)
        .commitAllowingStateLoss()

}

fun Fragment.showFragment(fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .show(fragment)
        .commitAllowingStateLoss()
}

fun Fragment.showHideFragment(showFragment: Fragment, hideFragment: Fragment) {
    childFragmentManager.beginTransaction()
        .show(showFragment)
        .hide(hideFragment)
        .commitAllowingStateLoss()
}

fun Fragment.replaceFragment(layoutId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .replace(layoutId, fragment)
        .commitAllowingStateLoss()
}

fun Fragment.removeFragment(fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .remove(fragment)
        .commitAllowingStateLoss()
}