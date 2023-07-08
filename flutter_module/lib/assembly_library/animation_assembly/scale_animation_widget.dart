import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * 缩放动画
 */
class ScaleAnimationPage extends StatefulWidget {
  const ScaleAnimationPage({Key? key}) : super(key: key);

  @override
  State<ScaleAnimationPage> createState() => _ScaleAnimationPageState();
}

//需要继承TickerProvider，如果有多个AnimationController，则应该使用TickerProviderStateMixin。
class _ScaleAnimationPageState extends State<ScaleAnimationPage>
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
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "缩放 动画"),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Image.asset(
            "assets/images/cover_img.jpg",
            width: animation.value > 80 ? animation.value : 80,
            height: animation.value > 80 ? animation.value : 80,
          ),
          Spacer(),
          Padding(
            padding: EdgeInsets.only(left: 10, right: 10, bottom: 20),
            child: Row(
              children: [
                TextButton(
                  onPressed: () {
                    //启动动画(正向执行)
                    controller.forward();
                  },
                  child: Text(
                    "开始动画",
                    style: TextStyle(color: Colors.white),
                  ),
                  style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Colors.red)),
                ),
                Spacer(),
                TextButton(
                  onPressed: () {
                    controller.stop();
                  },
                  child: Text(
                    "暂停动画",
                    style: TextStyle(color: Colors.white),
                  ),
                  style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Colors.red)),
                ),
                Spacer(),
                TextButton(
                  onPressed: () {
                    controller.forward();
                  },
                  child: Text(
                    "继续动画",
                    style: TextStyle(color: Colors.white),
                  ),
                  style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Colors.red)),
                ),
                Spacer(),
                TextButton(
                  onPressed: () {
                    controller.reverse();
                  },
                  child: Text(
                    "反向动画",
                    style: TextStyle(color: Colors.white),
                  ),
                  style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Colors.red)),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  @override
  dispose() {
    //路由销毁时需要释放动画资源
    controller.dispose();
    super.dispose();
  }
}
