import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

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
      body: Column(
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
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
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
          MaterialButton(
            onPressed: () {},
            child: Text("MaterialButton"),
          ),
          TextButton(
            onPressed: () {},
            child: Text("TextButton"),
          )
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
