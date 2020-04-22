package com.memo.test.ui.rxjava

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-02-06 15:13
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object RxJavaDataUtils {

	fun getData(callback: (String) -> Unit) {
		callback("这就是数据")
	}

}