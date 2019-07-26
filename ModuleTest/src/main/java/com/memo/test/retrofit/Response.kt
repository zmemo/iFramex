package com.memo.test.retrofit

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 17:29
 */

data class ZhiHuNews(
    var date: String? = "", // 20190129
    var stories: List<Story?>? = listOf(),
    var top_stories: List<TopStory?>? = listOf()
)

data class TopStory(
    var ga_prefix: String? = "", // 012816
    var id: Int? = 0, // 9707019
    var image: String? = "", // https://pic4.zhimg.com/v2-1a339893d67301af1dd6111aeb96e1fb.jpg
    var title: String? = "", // 用加速包买火车票，买出了竞价排名的错觉
    var type: Int? = 0 // 0
)

data class Story(
    var ga_prefix: String? = "", // 012906
    var id: Int? = 0, // 9706928
    var images: List<String?>? = listOf(),
    var multipic: Boolean? = false, // true
    var title: String? = "", // 瞎扯 · 如何正确地吐槽
    var type: Int? = 0 // 0
)

data class Wan(
    val `data`: List<Data> = listOf(),
    val errorCode: Int = 0, // 0
    val errorMsg: String = ""
)

data class Data(
    val desc: String = "",
    val id: Int = 0, // 5
    val imagePath: String = "", // https://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png
    val isVisible: Int = 0, // 1
    val order: Int = 0, // 3
    val title: String = "", // 微信文章合集
    val type: Int = 0, // 1
    val url: String = "" // http://www.wanandroid.com/blog/show/6
)