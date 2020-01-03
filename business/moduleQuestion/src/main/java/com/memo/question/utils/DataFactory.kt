package com.memo.question.utils

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-01-02 15:31
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object DataFactory {

    const val imgUrl = "https://ac-q.static.booking.cn/images/hotel/max1280x900/890/89083962.jpg"

    fun getData(num: Int): ArrayList<Entity> {
        val data = arrayListOf<Entity>()
        for (index in 0..num) {
            val entity = Entity()
            entity.images = imgUrl
            entity.title = "标题 $index"
            entity.content = "内容 $index"
            data.add(entity)
        }
        return data
    }

}