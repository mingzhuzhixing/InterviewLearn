import 'package:flutter/material.dart';

/**
 * Stack
 *
 * Stack类似Android中的FrameLayout，子Widget根据到四个角的位置来确定本身的位置，允许子Widget堆叠。
 */
class StackWidgetPage extends StatelessWidget {
  const StackWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("stack widget"),
      ),
      body: Stack(
        alignment: Alignment.center,
        children: <Widget>[
          Container(
            width: 300.0,
            height: 300.0,
            color: Colors.yellowAccent,
          ),
          const Text("我是一个文本"),
          const Text("我是一个文本1"),
          const Text("我是一个文本2"),
        ],
      ),
    );
  }
}
