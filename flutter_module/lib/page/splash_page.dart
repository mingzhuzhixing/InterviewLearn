import 'package:flutter/material.dart';
import 'package:flutter_module/widget/skip_down_time_progress.dart';
import 'dart:ui' as ui show window;

import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * 广告开屏页
 *
 * https://www.jianshu.com/p/7693a192682b
 */
class SplashPage extends StatefulWidget {
  const SplashPage({Key? key}) : super(key: key);

  @override
  State<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  String netImgUrl = '';

  @override
  void initState() {
    super.initState();
    //模拟广告图接口请求
    Future.delayed(Duration(microseconds: 1000), () {
      netImgUrl =
          'https://img2.baidu.com/it/u=1170834292,3580907519&fm=253&fmt=auto&app=138&f=JPG?w=500&h=889';
      //有广告页：netImgUrl不为空
      if (netImgUrl.isNotEmpty) {
        /**刷新页面使广告图显示**/
        setState(() {});
        //无广告页：netImgUrl为空""
      } else {
        /**跳转到主页**/
        Future.delayed(Duration(microseconds: 10), goIndexPage);
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Column(
          children: [
            Expanded(
              child: Container(
                constraints: BoxConstraints.expand(),
                color: Colors.white,
                //未请求回来之前，用启动页图片作为占位图
                child: netImgUrl.isNotEmpty ? Image.network(netImgUrl, fit: BoxFit.fill) : null,
              ),
            ),
            Container(
                color: Colors.white,
                child: Image.asset("assets/images/slogan.png", height: 109.w, fit: BoxFit.contain)),
          ],
        ),
        //倒计时：跳过
        Visibility(
          visible: netImgUrl.isNotEmpty,
          child: Positioned(
            child: SkipDownTimeProgress(
              color: Colors.red,
              radius: 22.0,
              duration: Duration(seconds: 5),
              size: Size(25.0, 25.0),
              skipText: "跳过",
              onTap: () => goIndexPage(),
              onFinishCallBack: (bool value) {
                if (value) {
                  goIndexPage();
                }
              },
            ),
            top: MediaQueryData.fromView(ui.window).padding.top + 20,
            right: 30,
          ),
        )
      ],
    );
  }

  // 跳转主页
  void goIndexPage() {
    Navigator.of(context).pushReplacementNamed('/index');
  }
}
