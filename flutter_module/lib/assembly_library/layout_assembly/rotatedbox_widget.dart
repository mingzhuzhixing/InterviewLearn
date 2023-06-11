import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * 选择组件
 * 由于RotatedBox是作用于layout阶段，所以子组件会旋转90度（而不只是绘制的内容），decoration会作用到子组件所占用的实际空间上，
 */
class RotatedBoxWidgetPage extends StatefulWidget {
  const RotatedBoxWidgetPage({Key? key}) : super(key: key);

  @override
  State<RotatedBoxWidgetPage> createState() => _RotatedBoxWidgetPageState();
}

class _RotatedBoxWidgetPageState extends State<RotatedBoxWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context,"RotatedBox Widget"),
      body: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          DecoratedBox(
            decoration: BoxDecoration(color: Colors.red),
            //将Transform.rotate换成RotatedBox
            child: RotatedBox(
              quarterTurns: 1, //旋转90度(1/4圈)
              child: Text("Hello world"),
            ),
          ),
          Text("你好", style: TextStyle(color: Colors.green, fontSize: 18.0),)
        ],
      ),
    );
  }
}
