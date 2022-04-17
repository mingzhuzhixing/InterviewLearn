package com.v.kotlin.bean

/**
 * ClassName: DataBean
 * Description:
 * @package_name com.v.kotlin.bean
 * @author zhuming
 * @date 2021/9/30 4:15 下午
 */

data class HomeDataBean(val datas: MutableList<ItemDataBean>, val curPage: Int, val total: Int, val pageCount: Int)

data class ItemDataBean(val title: String, val niceShareDate: String, val shareUser: String, val link: String)


/**
 * {
"desc": "我们支持订阅啦~",
"id": 30,
"imagePath": "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
"isVisible": 1,
"order": 0,
"title": "我们支持订阅啦~",
"type": 0,
"url": "https://www.wanandroid.com/blog/show/3352"
},
 */
data class BannerDataBean(val imagePath: String, val title: String, val url: String)