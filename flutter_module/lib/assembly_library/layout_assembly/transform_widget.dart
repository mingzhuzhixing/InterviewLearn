import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'dart:math' as math;

class TransformWidgetPage extends StatefulWidget {
  const TransformWidgetPage({Key? key}) : super(key: key);

  @override
  State<TransformWidgetPage> createState() => _TransformWidgetPageState();
}

class _TransformWidgetPageState extends State<TransformWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Transform Widget"),
      body: Column(
        children: [
          //Matrix4是一个4D矩阵
          Container(
            margin: EdgeInsets.only(top: 100.w, left: 50.w),
            color: Colors.black,
            child: Transform(
              alignment: Alignment.topRight, //相对于坐标系原点的对齐方式
              transform: Matrix4.skewY(0.3), //沿Y轴倾斜0.3弧度
              child: Container(
                padding: const EdgeInsets.all(8.0),
                color: Colors.deepOrange,
                child: const Text('Apartment for rent!'),
              ),
            ),
          ),

          SizedBox(height: 40.w),

          //平移
          Container(
            child: DecoratedBox(
              decoration: BoxDecoration(color: Colors.red),
              //默认原点为左上角，左移20像素，向上平移5像素
              child: Transform.translate(
                offset: Offset(-20.0, -5.0),
                child: Text("Hello world"),
              ),
            ),
          ),
          
          SizedBox(height: 90.w),

          //旋转:Transform.rotate可以对子组件进行旋转变换
          Container(
            child: DecoratedBox(
              decoration:BoxDecoration(color: Colors.red),
              child: Transform.rotate(
                //旋转90度
                angle:math.pi/2 ,
                child: Text("Hello world"),
              ),
            ),
          ),

          SizedBox(height: 80.w),

          //缩放:Transform.scale可以对子组件进行缩小或放大
          Container(
            child: DecoratedBox(
                decoration:BoxDecoration(color: Colors.red),
                child: Transform.scale(
                    scale: 1.5, //放大到1.5倍
                    child: Text("Hello world")
                )
            ),
          )
        ],
      ),
    );
  }
}
