import 'package:flutter/material.dart';

/**
 * SplashScreenWidgetPage
 *
 * 启动画面的一些基本功能：
    它主要用于应用程序的品牌或身份识别，并将品牌印象或播发广告给用户。
    它还用于显示正在加载到内存中以向用户展示软件的应用程序的进度。
    当启动屏幕的加载完成时，用户将获得另一个功能屏幕，该功能屏幕可能是主屏幕或仪表板，然后被遗忘了。加载完成后，无法按返回按钮返回启动画面。

    https://www.yiibai.com/flutter/flutter-splashscreen.html
 */
class SplashScreenWidgetPage extends StatefulWidget {
  const SplashScreenWidgetPage({Key? key}) : super(key: key);

  @override
  State<SplashScreenWidgetPage> createState() => _SplashScreenWidgetPageState();
}

class _SplashScreenWidgetPageState extends State<SplashScreenWidgetPage> {
  @override
  Widget build(BuildContext context) {
    // return SplashScreen(
    //   seconds: 4,
    //   navigateAfterSeconds: OsChinaBody(),
    //   backgroundColor: Colors.white,
    //   title: const Text(
    //     'yiibai.com',
    //     textScaleFactor: 2,
    //   ),
    //   image: Image.network('https://s3.o7planning.com/images/triceratops/image1.png'),
    //   loadingText: const Text("Loading"),
    //   photoSize: 110.0,
    //   loaderColor: Colors.red,
    // );
    return Spacer();
  }
}
