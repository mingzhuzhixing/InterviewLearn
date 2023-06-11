import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class CircularProgressIndicatorWidgetPage extends StatefulWidget {
  const CircularProgressIndicatorWidgetPage({Key? key}) : super(key: key);

  @override
  State<CircularProgressIndicatorWidgetPage> createState() =>
      _CircularProgressIndicatorWidgetPageState();
}

class _CircularProgressIndicatorWidgetPageState extends State<CircularProgressIndicatorWidgetPage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "CircularProgressIndicator widget"),
      body: Column(
        children: [
          // 模糊进度条(会执行一个旋转动画)
          CircularProgressIndicator(
            backgroundColor: Colors.grey[200],
            valueColor: AlwaysStoppedAnimation(Colors.blue),
          ),

          SizedBox(height: 40.w),

          //进度条显示50%，会显示一个半圆
          CircularProgressIndicator(
            backgroundColor: Colors.grey[200],
            valueColor: AlwaysStoppedAnimation(Colors.blue),
            value: .6,
          ),

          SizedBox(height: 40.w),

          // 圆形进度条直径指定为100
          SizedBox(
            height: 100,
            width: 100,
            child: CircularProgressIndicator(
              backgroundColor: Colors.grey[200],
              valueColor: AlwaysStoppedAnimation(Colors.blue),
              value: .7,
            ),
          ),

          SizedBox(height: 40.w),

          // 宽高不等
          SizedBox(
            height: 100,
            width: 130,
            child: CircularProgressIndicator(
              backgroundColor: Colors.grey[200],
              valueColor: AlwaysStoppedAnimation(Colors.blue),
              value: .7,
            ),
          ),
        ],
      ),
    );
  }
}
