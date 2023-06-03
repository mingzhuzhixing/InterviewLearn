import 'package:flutter/material.dart';

///1、Tooltip
///Tooltip支持用户传入任意一个child作为显示的Widget，并且在用户长按Widget时，会在上方或者下方出现类似Toast的提示，隔一段时间自动消失，
class TooltipWidgetPage extends StatelessWidget {
  const TooltipWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Tooltip widget"),
      ),
      body: const Center(
        child: Tooltip(
          //提示的内容
          message: "显示提示内容",
          // 设置高度
          //height: 60.0,
          //具体内部child widget竖直方向的距离
          //verticalOffset: 50.0,
          //是否显示在下面
          //preferBelow: false,
          //padding
          //padding: EdgeInsets.all(20.0),
          child: Icon(
            Icons.android,
            size: 100.0,
            color: Colors.green,
          ),
        ),
      ),
    );
  }
}
