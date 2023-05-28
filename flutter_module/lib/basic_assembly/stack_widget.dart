import 'package:flutter/material.dart';

///stack
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
