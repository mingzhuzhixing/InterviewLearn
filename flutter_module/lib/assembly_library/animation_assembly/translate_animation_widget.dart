import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

class TranslateWidgetPage extends StatefulWidget {
  const TranslateWidgetPage({Key? key}) : super(key: key);

  @override
  State<TranslateWidgetPage> createState() => _TranslateWidgetPageState();
}

class _TranslateWidgetPageState extends State<TranslateWidgetPage>
    with SingleTickerProviderStateMixin {
  late Animation<double> animation;
  late AnimationController controller;

  @override
  initState() {
    super.initState();
    controller = AnimationController(
      duration: const Duration(seconds: 2),
      vsync: this,
    );

    //匀速
    //图片宽高从0变到300
    animation = Tween(begin: 0.0, end: 300.0).animate(controller)
      ..addListener(
        () {
          setState(() => {});
        },
      );

    //启动动画(正向执行)
    controller.forward();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "移动 动画"),
      body: Container(
        margin: EdgeInsets.only(top: animation.value),
        child: Image.asset(
          "assets/images/cover_img.jpg",
          width: 80,
          height: 80,
        ),
      ),
    );
  }
}
