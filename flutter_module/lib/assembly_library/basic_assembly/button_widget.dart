import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

///
/// button 分为三类：
/// 1、OutlineButton 默认带有一个边框，我们可以通过属性指定正常状态，跟用户点击状态下的边框颜色。
/// 2、RaisedButton、FlatButton、MaterialButton、RawMaterialButton
/// 3、FloatingActionButton
///
class ButtonWidgetPage extends StatelessWidget {
  const ButtonWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Button Widget"),
      ),
      body: CustomScrollView(
        physics: BouncingScrollPhysics(),
        slivers: [
          SliverList(
            delegate: SliverChildBuilderDelegate(
              (BuildContext content, int index) {
                return Column(
                  children: <Widget>[
                    OutlineButton(
                      onPressed: () {
                        print("OutlineButton");
                      },
                      child: Text("OutlineButton"),
                    ),
                    OutlineButton(
                      onPressed: () {},
                      textColor: Colors.blue,
                      highlightedBorderColor: Colors.deepOrange,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20.0)),
                      borderSide: BorderSide(color: Colors.blueAccent),
                      child: Text("OutlineButton"),
                    ),
                    FloatingActionButton(
                      onPressed: () {
                        print("FloatingActionButton");
                      },
                      child: Text("FAB"),
                      backgroundColor: Colors.blueAccent,
                      foregroundColor: Colors.red,
                    ),
                    RaisedButton(
                      onPressed: () {},
                      child: Text("RaisedButton"),
                      padding: EdgeInsets.all(15.0),
                    ),
                    SizedBox(height: 10.w),
                    Container(
                      height: 70.w,
                      width: 1.sw * 0.8,
                      child: RaisedButton(
                        onPressed: () {},
                        child: Text("RaisedButton"),
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(20.0),
                            side: BorderSide(
                                width: 2.0, color: Color(0xFF000000))),
                      ),
                    ),
                    MaterialButton(
                      onPressed: () {},
                      child: Text("MaterialButton"),
                    ),
                    MaterialButton(
                      onPressed: () {},
                      child: Text("MaterialButton"),
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20.0),
                          side:
                              BorderSide(width: 2.0, color: Color(0xFF000000))),
                    ),
                    MaterialButton(
                      onPressed: () {},
                      child: Text(
                        "MaterialButton",
                        style: TextStyle(color: Colors.white, fontSize: 28.sp),
                      ),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20.0),
                      ),
                      color: Colors.blue,
                    ),
                    Container(
                      height: 180.h,
                      child: MaterialButton(
                        onPressed: () {},
                        child: Text("MaterialButton",
                            style:
                                TextStyle(fontSize: 14, color: Colors.white)),
                      ),
                      decoration: BoxDecoration(
                        color: Colors.red,
                        border: Border(
                          top: BorderSide(
                              width: 2.0,
                              color: Color(0xFF000000),
                              style: BorderStyle.solid),
                          left:
                              BorderSide(width: 2.0, color: Color(0xFF000000)),
                          right:
                              BorderSide(width: 2.0, color: Color(0xFF0D47A1)),
                          bottom:
                              BorderSide(width: 2.0, color: Color(0xFF0D47A1)),
                        ),
                        //borderRadius: BorderRadius.all(Radius.circular(15)),
                      ),
                    ),
                    Container(
                      child: IconButton(
                          onPressed: () {}, icon: Icon(Icons.settings)),
                      color: Colors.blue,
                    ),
                    TextButton(
                      onPressed: () {},
                      child: Text("TextButton"),
                      style: ButtonStyle(
                          backgroundColor:
                              MaterialStateProperty.all<Color>(Colors.yellow),
                          shape: MaterialStateProperty.all<OutlinedBorder>(
                            RoundedRectangleBorder(
                                borderRadius:
                                    BorderRadius.all(Radius.circular(10))),
                          )),
                    ),
                    TextButton(
                      onPressed: () {},
                      child: Text("TextButton",
                          style: TextStyle(fontSize: 14, color: Colors.white)),
                      style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all<Color>(Colors.deepOrange),
                        shape: MaterialStateProperty.all<OutlinedBorder>(
                          RoundedRectangleBorder(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(50.w))),
                        ),
                        padding: MaterialStateProperty.all(
                            EdgeInsets.only(left: 40.w, right: 40.w)),
                      ),
                    ),
                    Container(
                      height: 60.w,
                      width: 120.w,
                      child: TextButton(
                        onPressed: () {},
                        child: Text("确定",
                            style: TextStyle(
                                fontSize: 22.sp, color: Colors.white)),
                        style: ButtonStyle(
                          backgroundColor:
                              MaterialStateProperty.all<Color>(Colors.blue),
                          shape: MaterialStateProperty.all<OutlinedBorder>(
                            RoundedRectangleBorder(
                                borderRadius:
                                    BorderRadius.all(Radius.circular(50.w))),
                          ),
                        ),
                      ),
                    ),
                    SizedBox(height: 10.w),
                    Container(
                      height: 100.w,
                      width: 1.sw,
                      margin: EdgeInsets.only(left: 20.w, right: 20.w),
                      child: TextButton(
                        onPressed: () {},
                        child: Text("TextButton",
                            style:
                                TextStyle(fontSize: 14, color: Colors.white)),
                        style: ButtonStyle(
                          backgroundColor:
                              MaterialStateProperty.all<Color>(Colors.green),
                          shape: MaterialStateProperty.all<OutlinedBorder>(
                            RoundedRectangleBorder(
                                borderRadius:
                                    BorderRadius.all(Radius.circular(50.w))),
                          ),
                          padding: MaterialStateProperty.all(
                              EdgeInsets.only(left: 40.w, right: 40.w)),
                        ),
                      ),
                    ),
                    SizedBox(height: 10.w),
                    Row(
                      children: [
                        Expanded(
                          child: Container(
                            height: 100.w,
                            margin: EdgeInsets.only(left: 20.w, right: 20.w),
                            child: TextButton(
                              onPressed: () {},
                              child: Text("TextButton",
                                  style: TextStyle(
                                      fontSize: 14, color: Colors.white)),
                              style: ButtonStyle(
                                backgroundColor:
                                    MaterialStateProperty.all<Color>(
                                        Colors.deepOrange),
                                shape:
                                    MaterialStateProperty.all<OutlinedBorder>(
                                  RoundedRectangleBorder(
                                      borderRadius: BorderRadius.all(
                                          Radius.circular(50.w))),
                                ),
                                padding: MaterialStateProperty.all(
                                    EdgeInsets.only(left: 40.w, right: 40.w)),
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 10),
                    IconButton(
                      icon: Icon(Icons.thumb_up),
                      onPressed: () {},
                    ),
                    SizedBox(height: 10),
                    RaisedButton.icon(
                        onPressed: () {},
                        icon: Icon(Icons.send),
                        label: Text("发送")),
                  ],
                );
              },
              childCount: 1,
            ),
          ),
        ],
      ),
    );
  }
}

// const OutlineButton({
// Key key,
// @required VoidCallback onPressed,
// ButtonTextTheme textTheme,  //按钮上字体主题
// Color textColor,  //字体颜色
// Color disabledTextColor, //按钮禁用时候文字的颜色
// Color color,  //按钮背景颜色
// Color highlightColor,//点击或者toch控件高亮的时候显示在控件上面，水波纹下面的颜色
// Color splashColor, //水波纹的颜色
// double highlightElevation,//高亮时候的阴影
// this.borderSide,//按钮边框
// this.disabledBorderColor, //按钮禁用时边框的颜色
// this.highlightedBorderColor,//高亮时边框的颜色
// EdgeInsetsGeometry padding,//边距
// ShapeBorder shape, //设置shape
// Clip clipBehavior = Clip.none,
// Widget child,
// })

///RaisedButton、FlatButton、OutlineButton、MaterialButton类似
// new RaisedButton(
// color: Colors.blueAccent,
// //按钮的背景颜色
// padding: EdgeInsets.all(15.0),
// //按钮距离里面内容的内边距
// textColor: Colors.white,
// //文字的颜色
// textTheme: ButtonTextTheme.normal,
// //按钮的主题
// onHighlightChanged: (bool b) {
// //水波纹高亮变化回调
// },
// disabledTextColor: Colors.black54,
// //按钮禁用时候文字的颜色
// disabledColor: Colors.black54,
// //按钮被禁用的时候显示的颜色
// highlightColor: Colors.green,
// //点击或者toch控件高亮的时候显示在控件上面，水波纹下面的颜色
// splashColor: Colors.amberAccent,
// //水波纹的颜色
// colorBrightness: Brightness.light,
// //按钮主题高亮
// elevation: 10.0,
// //按钮下面的阴影
// highlightElevation: 10.0,
// //高亮时候的阴影
// disabledElevation: 10.0,
// //按下的时候的阴影
// shape: new RoundedRectangleBorder(
// borderRadius: BorderRadius.circular(20.0)),
// //设置形状
// onPressed: () {},
// child: new Text("RaisedButton"),
// ),
