package com.memo.tool.helper

import android.view.View

/**
 * title:控件显示隐藏工具
 * tip:
 *
 * @author zhou
 * @date 2018-09-29 上午10:54
 */
object ViewHelper {

    /**
     * 设置控件可见
     *
     * @param views 控件集合
     */
    @JvmStatic
    fun setVisible(vararg views: View?) {
        for (view in views) {
            if (view != null) {
                view.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 设置控件不可见
     *
     * @param views 控件集合
     */
    @JvmStatic
    fun setInvisible(vararg views: View?) {
        for (view in views) {
            if (view != null) {
                view.visibility = View.INVISIBLE
            }
        }
    }

    /**
     * 设置控件消失
     *
     * @param views 控件集合
     */
    @JvmStatic
    fun setGone(vararg views: View?) {
        for (view in views) {
            if (view != null) {
                view.visibility = View.GONE
            }
        }
    }

    /**
     * 判断控件是否可见
     *
     * @param views 控件集合
     * @return true 全部可见 false 有不可见
     */
    @JvmStatic
    fun isVisible(vararg views: View): Boolean {
        var isVisible = true
        for (view in views) {
            if (view.visibility != View.VISIBLE) {
                isVisible = false
                break
            }
        }
        return isVisible
    }

    /**
     * 判断控件是否不可见
     *
     * @param views 控件集合
     * @return true 全部不可见 false 有可见或者消失
     */
    @JvmStatic
    fun isInVisible(vararg views: View): Boolean {
        var isInVisible = true
        for (view in views) {
            if (view.visibility != View.INVISIBLE) {
                isInVisible = false
                break
            }
        }
        return isInVisible
    }

    /**
     * 判断控件是否全部消失
     *
     * @param views 控件集合
     * @return true 全部消失 false 有未消失
     */
    @JvmStatic
    fun isGone(vararg views: View): Boolean {
        var isGone = true
        for (view in views) {
            if (view.visibility != View.GONE) {
                isGone = false
                break
            }
        }
        return isGone
    }
}
