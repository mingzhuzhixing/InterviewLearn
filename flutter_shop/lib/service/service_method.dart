//获取首页主题内容
// ignore_for_file: avoid_print

import 'dart:io';
import 'package:dio/dio.dart';
import 'package:flutter_shop/service/service_url.dart';

Future getHomePageContent() async {
  try {
    print("开始获取首页数据。。。。。。。。。。。。。。。");
    Response response;
    Dio dio = new Dio();
    dio.options.contentType = ContentType.parse("application/x-www-form-url-urlencoded").toString();
    var formData = {'lon': '11502932', 'lat': '35.76189'};
    response = await dio.post(servicePath['homePageContent'], data: formData);
    print("response.statusCode:${response.statusCode}");
    if (response.statusCode == 200) {
      return response.data;
    } else {
      throw Exception("后端接口出现异常。");
    }
  } catch (e) {
    return print('error:======>${e}');
  }
}


void getHttp() async {
  try {
    print("getHttp1");
    // response = await Dio().get(
    //     "https://www.easy-mock.com/mock/5c60131a4bed3a6342711498/baixing/dabaojian?name=大胸美女");
    // response = await Dio().get(
    //     "http://yapi.qa.laidan.com:3031/mock/70/app/gongdu/choose_books");
    //Response response = await Dio().get("http://www.baidu.com/");
    //Response response = await Dio().get("https://www.easy-mock.com/mock/5c60131a4bed3a6342711498/baixing/dabaojian?name=大胸美女");
    Response response = await Dio().get("https://mock.mengxuegu.com/mock/6367601c88ed56479c201dac/zm_flutter/mock");
    print("getHttp2: $response");
  } catch (e) {
    return print(e);
  }
}
