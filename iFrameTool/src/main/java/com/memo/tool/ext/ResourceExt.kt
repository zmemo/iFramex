package com.memo.tool.ext

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.memo.tool.app.BaseApp

/**
 * title: 获取资源文件
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:43
 */

/**
 * 从Context中获取资源
 */
fun color(id: Int): Int = ContextCompat.getColor(BaseApp.app.applicationContext, id)

fun string(id: Int): String = BaseApp.app.applicationContext.resources.getString(id)

fun stringArray(id: Int): Array<String> =
    BaseApp.app.applicationContext.resources.getStringArray(id)

fun drawable(id: Int) = ContextCompat.getDrawable(BaseApp.app.applicationContext, id)

fun dimen(id: Int) = BaseApp.app.applicationContext.resources.getDimension(id)

fun dp2px(dp: Float): Int =
    (dp * BaseApp.app.applicationContext.resources.displayMetrics.density + 0.5f).toInt()

fun px2dp(px: Float): Int =
    (px / BaseApp.app.applicationContext.resources.displayMetrics.density + 0.5f).toInt()

fun sp2px(sp: Float): Int =
    (sp * BaseApp.app.applicationContext.resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun px2sp(px: Float): Int =
    (px / BaseApp.app.applicationContext.resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun Context.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
    View.inflate(this, layoutRes, parent)

fun Fragment.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
    View.inflate(context, layoutRes, parent)

fun Dialog.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
    View.inflate(context, layoutRes, parent)

fun View.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
    View.inflate(context, layoutRes, parent)

fun RecyclerView.ViewHolder.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
    View.inflate(itemView.context, layoutRes, parent)
