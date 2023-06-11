import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 *
 * Flutter中提供了一些剪裁组件，用于对组件进行剪裁。
 *  ClipOval	子组件为正方形时剪裁成内贴圆形；为矩形时，剪裁成内贴椭圆
 *  ClipRRect	将子组件剪裁为圆角矩形
 *  ClipRect	默认剪裁掉子组件布局空间之外的绘制内容（溢出部分剪裁）
 *  ClipPath	按照自定义的路径剪裁
 */
class ClipWidgetPage extends StatefulWidget {
  const ClipWidgetPage({Key? key}) : super(key: key);

  @override
  State<ClipWidgetPage> createState() => _ClipWidgetPageState();
}

class _ClipWidgetPageState extends State<ClipWidgetPage> {

  // 头像
  Widget avatar = Image.asset("assets/images/cover_img.jpg", width: 60.0);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Clip Widget"),
      body:Container(
        child: Column(
          children: <Widget>[
            avatar, //不剪裁
            SizedBox(height: 20.w),

            ClipOval(child: avatar), //剪裁为圆形
            SizedBox(height: 20.w),

            ClipRRect( //剪裁为圆角矩形
              borderRadius: BorderRadius.circular(5.0),
              child: avatar,
            ),
            SizedBox(height: 20.w),

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Align(
                  alignment: Alignment.topLeft,
                  widthFactor: .5,//宽度设为原来宽度一半，另一半会溢出
                  child: avatar,
                ),
                Text("你好世界", style: TextStyle(color: Colors.green),)
              ],
            ),
            SizedBox(height: 20.w),

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                ClipRect(//将溢出部分剪裁
                  child: Align(
                    alignment: Alignment.topLeft,
                    widthFactor: .5,//宽度设为原来宽度一半
                    child: avatar,
                  ),
                ),
                Text("你好世界",style: TextStyle(color: Colors.green))
              ],
            ),
          ],
        ),
      ),
    );
  }
}
