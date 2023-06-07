import 'package:flutter/material.dart';

/**
 * SizedBox是具有固定宽高的组件，直接指定具体的宽高，
 * 1、也可以设置尺寸无限大
 * 2、SizedBox可以没有子组件，但仍然会占用空间，所以SizedBox非常适合控制2个组件之间的空隙，
 */
class SizeBoxWidgetPage extends StatefulWidget {
  const SizeBoxWidgetPage({Key? key}) : super(key: key);

  @override
  State<SizeBoxWidgetPage> createState() => _SizeBoxWidgetPageState();
}

class _SizeBoxWidgetPageState extends State<SizeBoxWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("sizebox widget"),
      ),
      body: Column(
        children: [
          SizedBox(
            height: 60,
            width: 200,
            child: MaterialButton(
              onPressed: () {  },
              child: Text('this is SizedBox'),
            ),
          ),
          Text("sizebox1"),
          SizedBox(height: 10),
          Text("sizebox2"),
          SizedBox(height: 20),
          Text("sizebox3"),
          SizedBox(height: 30),
          Text("sizebox4"),

          Row(
            children: [
              Text("sizebox5"),
              SizedBox(width: 10),
              Text("sizebox6"),
              SizedBox(width: 20),
              Text("sizebox7"),
              SizedBox(width: 30),
              Text("sizebox8"),
            ],
          )
        ],
      ),
    );
  }
}
