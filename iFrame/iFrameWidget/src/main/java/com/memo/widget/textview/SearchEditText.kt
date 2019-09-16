package com.memo.widget.textview

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText


import com.blankj.utilcode.util.StringUtils

/**
 * title: 搜索使用的EditText 用于一边输入一边搜索
 * describe:
 *
 * @author zhou
 * @date 2019-09-11 11:35
 */
class SearchEditText : EditText {

    private val LIMIT: Long = 1000

    private var mListener: OnTextChangedListener? = null
    // 记录开始输入前的文本内容
    private var mStartText = ""

    private val mAction = Runnable {
        if (mListener != null) {
            // 判断最终和开始前是否一致
            if (!StringUtils.equals(mStartText, text!!.toString())) {
                mStartText = text!!.toString() // 更新 mStartText
                mListener!!.onTextChanged(mStartText)
            }
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet)
            : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    /**
     * 在 LIMIT 时间内连续输入不触发文本变化
     */
    fun setOnTextChangedListener(onTextChanged: (text: String) -> Unit) {
        mListener = object : OnTextChangedListener {
            override fun onTextChanged(text: String) {
                onTextChanged(text)
            }
        }
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        // 移除上一次的回调
        removeCallbacks(mAction)
        postDelayed(mAction, LIMIT)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(mAction)
    }

    interface OnTextChangedListener {
        fun onTextChanged(text: String)
    }
}