import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

///TextFiled
class TextFieldWidgetPage extends StatelessWidget {

  const TextFieldWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("TextField Widget")),
      body: Center(
        child: Column(
          children: <Widget>[
            const Text(
              "简单文本输入框",
              style: TextStyle(color: Colors.redAccent, fontSize: 20.0),
            ),
            const TextField(
              keyboardType: TextInputType.number,
            ),
            TextField(
              onSubmitted: (value) {
                print("------------文字提交触发（键盘按键）--");
              },
              onChanged: (value) {
                print("----------------输入框中内容为:$value--");
              },
              onEditingComplete: () {
                print("----------------编辑完成--");
              },
              keyboardType: TextInputType.text,
            ),
            TextField(
              style: const TextStyle(height: 1.0, color: Colors.amber),
              keyboardType: TextInputType.text,
              decoration: InputDecoration(
                labelText: "请输入内容",
                helperText: "随便输入文字或数字",
                border:
                    OutlineInputBorder(gapPadding: 10.0, borderRadius: BorderRadius.circular(20.0)),
              ),
            ),
            Container(
              child: const TextField(
                style: TextStyle(color: Colors.green),
                keyboardType: TextInputType.text,
              ),
              margin: const EdgeInsets.fromLTRB(0, 20.0, 0, 0),
              color: Colors.redAccent,
            )
          ],
        ),
      ),
    );
  }
}

///TextField 属性
// const TextField({
// Key key,
// this.controller,           ////控制器，控制TextField文字
// this.focusNode,
// this.decoration = const InputDecoration(),    //输入器装饰
// TextInputType keyboardType,   ////输入的类型
// this.textInputAction,
// this.textCapitalization = TextCapitalization.none,
// this.style,
// this.textAlign = TextAlign.start,   //文字显示位置
// this.autofocus = false,
// this.obscureText = false,
// this.autocorrect = true,
// this.maxLines = 1,
// this.maxLength,
// this.maxLengthEnforced = true,
// this.onChanged,                //文字改变触发
// this.onEditingComplete,   //当用户提交可编辑内容时调用
// this.onSubmitted,   ////文字提交触发（键盘按键）
// this.inputFormatters,
// this.enabled,
// this.cursorWidth = 2.0,
// this.cursorRadius,
// this.cursorColor,
// this.keyboardAppearance,
// this.scrollPadding = const EdgeInsets.all(20.0),
// })

///InputDecoration 属性
// const InputDecoration({
// this.icon, //输入框左侧添加个图标
// this.labelText,//输入框获取焦点/有内容 会移动到左上角，否则在输入框内labelTex的位置
// this.labelStyle,
// this.helperText,
// this.helperStyle,
// this.hintText, //未输入文字时，输入框中的提示文字
// this.hintStyle,
// this.errorText,
// this.errorStyle,
// this.errorMaxLines,
// this.isDense,
// this.contentPadding,
// this.prefixIcon, //输入框内侧左面的控件
// this.prefix,
// this.prefixText,
// this.prefixStyle,
// this.suffixIcon,//输入框内侧右面的图标
// this.suffix,
// this.suffixText,
// this.suffixStyle,
// this.counterText,
// this.counterStyle,
// this.filled,
// this.fillColor,
// this.errorBorder,
// this.focusedBorder,
// this.focusedErrorBorder,
// this.disabledBorder,
// this.enabledBorder,
// this.border,  //增加一个边框
// this.enabled = true,
// this.semanticCounterText,
// })
