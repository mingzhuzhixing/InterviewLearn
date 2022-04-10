import 'package:flutter/widgets.dart';

/// 自定义页面切换动画 - 平移切换
/// 进从右向左 出从左向右
class SlidePageRouteBuilder extends PageRouteBuilder {
  // 跳转的页面
  final Widget widget;

  SlidePageRouteBuilder(this.widget)
      : super(
      transitionDuration: const Duration(milliseconds: 300),
      pageBuilder: (BuildContext context, Animation animation, Animation secondaryAnimation) {
        return widget;
      },
      transitionsBuilder: (BuildContext context, Animation<double> animation,
          Animation<double> secondaryAnimation, Widget child) {
        return SlideTransition(
          position: Tween(begin: const Offset(1.0, 0.0), end: const Offset(0.0, 0.0))
              .animate(CurvedAnimation(parent: animation, curve: Curves.fastOutSlowIn)),
          child: child,
        );
      });
}
