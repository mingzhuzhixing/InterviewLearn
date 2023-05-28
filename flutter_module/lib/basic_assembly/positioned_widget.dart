import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

///Positioned布局
///
/// Positioned widget 用于定位 Stack 的子 widget。  Positioned 仅用作 Stack 的直接(或后代)子部件
/// 使用控制Widget的位置，通过他可以随意摆放一个组件，有点像绝对布局
class PositionedWidgetPage extends StatelessWidget {
  const PositionedWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("positioned widget"),
      ),
      body: Stack(
        children: <Widget>[
          Image.network(
            'https://img0.baidu.com/it/u=3801219024,3932149209&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800',
            scale: 0.5,
          ),
          const Positioned(
            left: 35.0,
            right: 35.0,
            top: 45.0,
            child: Text(
              "第二层内容区域",
              style: TextStyle(
                fontSize: 20.0,
                color: Colors.red,
                fontFamily: 'serif',
              ),
            ),
          ),
          const Positioned(
            left: 55.0,
            right: 55.0,
            top: 55.0,
            child: Text(
              '第三层 this is the third child',
              style: TextStyle(
                fontSize: 20.0,
                color: Colors.blue,
                fontFamily: 'serif',
              ),
            ),
          )
        ],
      ),
    );
  }
}
