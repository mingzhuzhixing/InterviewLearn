import 'package:flutter/material.dart';

///align 布局
/// Align即对齐控件，能将子控件按照所指定的方式对齐，并根据子控件的大小调整自己的大小
/// bottomCenter    (0.5, 1.0)    底部中心
/// bottomLeft    (0.0, 1.0)    左下角
/// bottomRight    (1.0, 1.0)    右下角
/// center    (0.5, 0.5)    水平垂直居中
/// centerLeft    (0.0, 0.5)    左边缘中心
/// centerRight    (1.0, 0.5)    右边缘中心
/// topCenter    (0.5, 0.0)    顶部中心
/// topLeft    (0.0, 0.0)    左上角
/// topRight    (1.0, 0.0)    右上角
class AlignWidgetPage extends StatelessWidget {
  const AlignWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Align Layout"),
        ),
        body: Stack(
          children: const [
            Align(
              alignment: FractionalOffset(0.0, 0.5),
              child: Text(
                '我在左边缘中心',
                style: TextStyle(fontSize: 35.0),
              ),
            ),
            Align(
              alignment: FractionalOffset(1.0, 1.0),
              child: Text(
                '我在右下角',
                style: TextStyle(fontSize: 30.0),
              ),
            )
          ],
        ));
  }
}
