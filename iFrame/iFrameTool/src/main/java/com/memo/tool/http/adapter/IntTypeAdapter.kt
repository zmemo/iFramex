package com.memo.tool.http.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

/**
 * title:如果String类为null转为""
 * describe:
 *
 * @author zhou
 * @date 2019-05-31 16:21
 */
class IntTypeAdapter : TypeAdapter<Int>() {
	@Throws(IOException::class)
	override fun read(reader : JsonReader) : Int {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull()
			return 0
		}
		return reader.nextInt()
	}
	
	@Throws(IOException::class)
	override fun write(writer : JsonWriter, value : Int?) {
		if (value == null) {
			writer.nullValue()
			return
		}
		writer.value(value)
	}
}