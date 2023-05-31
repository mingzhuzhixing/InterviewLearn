import 'package:flutter/material.dart';

class VerticalText extends StatelessWidget {
  final String text;
  final TextStyle? textStyle;

  const VerticalText(this.text, {Key? key, this.textStyle}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    //分割字符串为单字，每个字包裹一个Text组件，作为Wrap组件的子组件
    List<Text> texts = text
        .split("")
        .map(
          (string) => Text(string, style: textStyle),
        )
        .toList();
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: Wrap(
        //排列方式，vertical代表竖着排，排不够就往旁边排
        direction: Axis.vertical,
        //主轴方向上间距
        spacing: textStyle?.letterSpacing ?? 1,
        //交叉轴方向上间距
        runSpacing: textStyle?.wordSpacing ?? 1,
        alignment: WrapAlignment.center,
        children: texts,
      ),
    );
  }
}
