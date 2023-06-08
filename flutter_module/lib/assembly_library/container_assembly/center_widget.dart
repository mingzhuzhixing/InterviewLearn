import 'package:flutter/material.dart';

///Center布局
///Center布局使用比较简单，场景也比较单一，一般用于协助其他子widget布局，包裹其child widget显示在上层布局的中心位置。
class CenterWidgetPage extends StatelessWidget {
  const CenterWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Center Layout'),
      ),
      body: const Center(
        child: Text('我在屏幕中央'),
      ),
    );
  }
}
