import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/*
 * 流式布局
 */
class WrapWidgetPage extends StatelessWidget {
  const WrapWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("Flutter wrap流式布局"),
        ),
        body: Container(
          width: 1.sw,
          height: 500,
          color: Colors.red,
          child: Wrap(
            runSpacing: 10.0,
            spacing: 10.0,
            children: const [
              MyButton("第一个"),
              MyButton("第二个二个二个按钮"),
              MyButton("第三个"),
              MyButton("第四个"),
              MyButton("第五个第五个第五个第五个第五个个"),
              MyButton("第六个"),
              MyButton("第七个"),
              MyButton("第八个按钮按钮"),
              MyButton("第九个"),
              MyButton("第十个"),
              MyButton("第")
            ],
          ),
        ),
      ),
    );
  }
}

/*
 * 自定义按钮
 */
class MyButton extends StatelessWidget {
  final String content;

  const MyButton(this.content, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialButton(
      onPressed: () {},
      child: Text(content),
      color: Colors.yellow,
      textColor: Theme.of(context).primaryColor,
    );
  }
}
