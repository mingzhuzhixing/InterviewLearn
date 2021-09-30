package com.v.kotlin.bean

/**
 * ClassName: DataBean
 * Description:
 * @package_name com.v.kotlin.bean
 * @author zhuming
 * @date 2021/9/30 4:15 下午
 */

data class HomeDataBean(val ad_list: MutableList<AdListBean>,
    val company_list: MutableList<AdListBean>, val news_list: MutableList<NewListBean>)

data class AdListBean(val image: String, val link: String)

data class NewListBean(val title: String, val create_time: String, val link: String)