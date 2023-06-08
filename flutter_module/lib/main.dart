// 必须在dart文件的第一行,可以加在任何dart文件中
import 'package:flutter/material.dart';
import 'package:flutter_module/oschinabody.dart';
import 'package:flutter_module/page/splash_page.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
        designSize: Size(750, 1334), //设计稿中设备的尺寸(单位随意,建议dp,但在使用过程中必须保持一致)
        minTextAdapt: true, //是否根据宽度/高度中的最小值适配文字
        splitScreenMode: true, //支持分屏尺寸
        builder: (
          BuildContext? context,
          Widget? child,
        ) {
          return MaterialApp(
            title: 'Flutter Demo',
            debugShowCheckedModeBanner: false,
            theme: ThemeData(
              primarySwatch: Colors.blue,
            ),
            home: SplashPage(),
            // 初始化路由
            routes: <String, WidgetBuilder>{'/index': (BuildContext context) => OsChinaBody()},
          );
        });
  }
}
