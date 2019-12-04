package com.memo.tool.helper

import android.view.View
import android.widget.EditText
import com.memo.tool.ext.value
import com.memo.tool.simple.SimpleTextWatcher
import java.util.*

/**
 * title: 控制多个输入框和提交按钮
 * describe:
 *
 * @author zhou
 * @date 2019-05-21 09:43
 */
class TextWatcherHelper : SimpleTextWatcher() {
	
	/*** 是否可以点击的按钮 ***/
	private var mSubmit : View? = null
	
	/*** 输入框集合 ***/
	private var mStack : Stack<EditText>? = null
	
	/**
	 * 绑定提交按钮
	 * @param submit View 提交按钮
	 * @return TextWatchHelper
	 */
	fun attach(submit : View) : TextWatcherHelper {
		mSubmit = submit
		return this
	}
	
	/**
	 * 添加输入框
	 * @param editTexts Array<out EditText> 输入框列表
	 * @return TextWatchHelper
	 */
	fun bindEditText(vararg editTexts : EditText) : TextWatcherHelper {
		if (mStack.isNullOrEmpty()) {
			mStack = Stack()
		}
		for (et : EditText in editTexts) {
			et.addTextChangedListener(this)
			mStack!!.add(et)
		}
		return this
	}
	
	/**
	 * 移除控件和监听
	 */
	fun detach() {
		mSubmit = null
		mStack?.let {
			for (et in it) {
				et.removeTextChangedListener(this)
			}
			it.clear()
		}
		mStack = null
	}
	
	override fun onTextChanged(s : CharSequence?, start : Int, before : Int, count : Int) {
		super.onTextChanged(s, start, before, count)
		mStack?.let {
			run loop@{
				for (et in it) {
					if (et.value.isEmpty()) {
						mSubmit?.isEnabled = false
						return@loop
					}
				}
				mSubmit?.isEnabled = true
			}
		}
	}
}