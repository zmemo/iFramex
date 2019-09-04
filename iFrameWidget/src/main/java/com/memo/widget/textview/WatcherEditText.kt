package com.memo.widget.textview

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText

/**
 * title:和EditText一样 只是在退出的时候可以方便的清除所有的TextWatcher
 * describe:
 *
 * @author zhou
 * @date 2019-09-04 09:30
 */
class WatcherEditText : EditText {

    private val list = arrayListOf<TextWatcher>()

    constructor(context: Context)
            : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun addTextChangedListener(watcher: TextWatcher) {
        super.addTextChangedListener(watcher)
        list.add(watcher)
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        super.removeTextChangedListener(watcher)
        list.remove(watcher)
    }

    fun removeAllWatcher() {
        list.forEach {
            super.removeTextChangedListener(it)
        }
        list.clear()
    }

}