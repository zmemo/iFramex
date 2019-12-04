package com.memo.iframex

import com.memo.tool.helper.GsonHelper
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
	@Test
	fun test() {
		val json = "{\"name\":null}"
		val person = GsonHelper.parse2Bean<Person>(json)
		println(person.name)
	}
	
	data class Person(
		val name : String = "12" // null
	)
}
