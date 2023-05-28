import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

///Text
class TextWidgetPage extends StatelessWidget {

  const TextWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Text Widget")),
      body: Center(
        child: Column(
          children: const <Widget>[
            Text(
              'inherit: 为 false 的时候不显示',
              style: TextStyle(inherit: true),
            ),
            Text(
              "color/fontSize: 字体颜色，字号等",
              style: TextStyle(color: Colors.red, fontSize: 20.0),
            ),
            Text(
              "fontWeight: 字重",
              style: TextStyle(fontSize: 20.0, color: Colors.blueGrey, fontWeight: FontWeight.w600),
            ),
            Text(
              "fontStyle: FontStyle.italic 斜体",
              style: TextStyle(fontStyle: FontStyle.italic),
            ),
            Text(
              "letterSpacing: 字符间距",
              style: TextStyle(
                letterSpacing: 10.0,
              ),
            ),
            Text(
              "wordSpacing: 字或单词间距",
              style: TextStyle(wordSpacing: 15.0),
            ),
            Text(
              "textBaseline:这一行的值为TextBaseline.alphabetic",
              style: TextStyle(textBaseline: TextBaseline.alphabetic),
            ),
            Text(
              "textBaseline:这一行的值为TextBaseline.ideographic",
              style: TextStyle(textBaseline: TextBaseline.ideographic),
            ),
            Text('height: 用在Text控件上的时候，会乘以fontSize做为行高,所以这个值不能设置过大',
                style: TextStyle(
                  height: 2.0,
                )),
            Text(
              "decoration: TextDecoration.overline 上划线",
              style: TextStyle(
                  fontSize: 18.0,
                  color: Colors.redAccent,
                  decoration: TextDecoration.overline,
                  decorationStyle: TextDecorationStyle.wavy),
            ),
            Text(
              "decoration: TextDecoration.lineThrough 删除线",
              style: TextStyle(
                  decoration: TextDecoration.lineThrough,
                  decorationStyle: TextDecorationStyle.dashed),
            ),
            Text(
              "decoration: TextDecoration.underline 下划线",
              style: TextStyle(
                  decoration: TextDecoration.underline,
                  decorationStyle: TextDecorationStyle.dotted),
            )
          ],
        ),
      ),
    );
  }
}

// class MyTextWidget extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return new Scaffold(
//       body: new Center(
//         child: new Text(
//           "This is Flutter Widget ---- Text ，is a StatelessWidget",
//           style: new TextStyle(
//               fontStyle: FontStyle.italic,
//               fontSize: 20.0,
//               color: Colors.red
//           ),
//           textAlign: TextAlign.center,
//         ),
//       ),
//     );
//   }
// }

///Text 属性
// const Text(this.data, {  //Text显示的内容
// Key key,
// this.style, //Text显示的样式
// this.textAlign,//文本应该如何水平对齐,TextAlign.start,end 或者center
// this.textDirection, //文本方向,TextDirection.ltr\TextDirection.rtl
// this.locale,
// this.softWrap,  //是否自动换行，若为false，文字将不考虑容器大小，单行显示，超出屏幕部分将默认截断处理
// this.overflow, //当文字超出屏幕的时候，如何处理,TextOverflow.clip(裁剪)\TextOverflow.fade(渐隐)\TextOverflow.ellipsis(省略号)
// this.textScaleFactor, //字体显示倍率，上面的例子使用的字体大小是20.0，将字体设置成10.0，然后倍率为2
// this.maxLines, //最大行数设置
// this.semanticsLabel,
// })

///TextStyle 属性
// const TextStyle({
// this.inherit: true,         // 为false的时候不显示
// this.color,                    // 颜色
// this.fontSize,               // 字号
// this.fontWeight,           // 字重，加粗也用这个字段  FontWeight.w700
// this.fontStyle,                // FontStyle.normal  FontStyle.italic斜体
// this.letterSpacing,        // 字符间距  就是单个字母或者汉字之间的间隔，可以是负数
// this.wordSpacing,        // 字间距 句字之间的间距
// this.textBaseline,        // 基线，两个值，字面意思是一个用来排字母的，一人用来排表意字的（类似中文）
// this.height,                // 当用来Text控件上时，行高（会乘以fontSize,所以不以设置过大）
// this.decoration,        // 添加上划线，下划线，删除线
// this.decorationColor,    // 划线的颜色
// this.decorationStyle,    // 这个style可能控制画实线，虚线，两条线，点, 波浪线等
// this.debugLabel,
// String fontFamily,    // 字体
// String package,
// })
