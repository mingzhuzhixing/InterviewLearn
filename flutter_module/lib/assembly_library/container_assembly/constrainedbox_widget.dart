import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * ConstrainedBox组件约束子组件的最大宽高和最小宽高，假如一个组件宽高都是300，包裹在ConstrainedBox中，并给ConstrainedBox添加最大宽高约束，
 */
class ConstrainedBoxWidgetPage extends StatefulWidget {
  const ConstrainedBoxWidgetPage({Key? key}) : super(key: key);

  @override
  State<ConstrainedBoxWidgetPage> createState() => _ConstrainedBoxWidgetPageState();
}

class _ConstrainedBoxWidgetPageState extends State<ConstrainedBoxWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "ConstrainedBoxWidget"),
      body: Column(
        children: [
          ConstrainedBox(
            constraints: BoxConstraints(
              minHeight: 100.w,
              maxHeight: 500.w,
              minWidth: 100.w,
              maxWidth: 500.w,
            ),
            child: Container(
              color: Colors.red,
              child: Text("ConstrainedBoxWidgetConstrainedBox"),
            ),
          ),
          SizedBox(height: 10.w),
          ConstrainedBox(
            constraints: BoxConstraints(
              minHeight: 100.w,
              maxHeight: 400.w,
              minWidth: 100.w,
              maxWidth: 500.w,
            ),
            child: Container(
              color: Colors.red,
              child: Text(
                  "ConstrainedBoxWidget1ConstrainedBoxWidget2ConstrainedBoxWidget3ConstrainedBoxWidget4"
                  "ConstrainedBoxWidget5ConstrainedBoxWidget5ConstrainedBoxWidget6ConstrainedBoxWidget8"
                  "ConstrainedBoxWidget9ConstrainedBoxWidget10ConstrainedBoxWidget11ConstrainedBoxWidget12"
                  "ConstrainedBoxWidget13ConstrainedBoxWidget14ConstrainedBoxWidget15ConstrainedBoxWidget16"
                  "ConstrainedBoxWidget17ConstrainedBoxWidget18ConstrainedBoxWidget19ConstrainedBoxWidget20"),
            ),
          ),
          SizedBox(height: 10.w),
          DecoratedBox(
            decoration: BoxDecoration(
                gradient: LinearGradient(colors: [Colors.red, Colors.orange.shade700]), //背景渐变
                borderRadius: BorderRadius.circular(3.0), //3像素圆角
                boxShadow: [
                  //阴影
                  BoxShadow(color: Colors.black54, offset: Offset(2.0, 2.0), blurRadius: 4.0)
                ]),
            child: Padding(
              padding: EdgeInsets.symmetric(horizontal: 80.0, vertical: 18.0),
              child: Text(
                "Login",
                style: TextStyle(color: Colors.white),
              ),
            ),
          )
        ],
      ),
    );
  }
}
