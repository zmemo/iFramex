package com.memo.base.tool.http.adapter

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
class LongTypeAdapter : TypeAdapter<Long>() {
	@Throws(IOException::class)
	override fun read(reader : JsonReader) : Long {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull()
			return 0L
		}
		return reader.nextLong()
	}
	
	@Throws(IOException::class)
	override fun write(writer : JsonWriter, value : Long?) {
		if (value == null) {
			writer.nullValue()
			return
		}
		writer.value(value)
	}
}