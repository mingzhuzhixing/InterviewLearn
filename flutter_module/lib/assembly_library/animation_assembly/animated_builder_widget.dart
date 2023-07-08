import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

class AnimatedBuilderWidgetpage extends StatefulWidget {
  const AnimatedBuilderWidgetpage({Key? key}) : super(key: key);

  @override
  State<AnimatedBuilderWidgetpage> createState() => _AnimatedBuilderWidgetpageState();
}

class _AnimatedBuilderWidgetpageState extends State<AnimatedBuilderWidgetpage>
    with SingleTickerProviderStateMixin {
  late Animation<double> animation;
  late AnimationController controller;

  @override
  void initState() {
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
      appBar: CommonAppBar(context, "AnimatedBuilder widget 实现动画"),
      body: AnimatedBuilder(
        animation: animation,
        child: Image.asset("assets/images/cover_img.jpg"),
        builder: (BuildContext ctx, child) {
          return Center(
            child: SizedBox(
              height: animation.value,
              width: animation.value,
              child: child,
            ),
          );
        },
      ),
    );
  }
}
