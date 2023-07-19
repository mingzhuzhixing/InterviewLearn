// ignore_for_file: must_be_immutable

import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_keyboard_size/flutter_keyboard_size.dart';
import 'package:flutter_keyboard_visibility/flutter_keyboard_visibility.dart';
import 'package:flutter_module/base/base_widget.dart';
import 'package:flutter_module/widget/KeyboardHeight.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';

///TextFiled 入口
class TextFieldHomePage extends BaseTitleBarWidget {
  @override
  String title() {
    return "TextFiled";
  }

  @override
  Widget childWidget() {
    return Column(
      children: [
        ItemButton("TextFiled 基本样式", TextFieldWidgetPage(), index: 0),
        ItemButton("TextFiled软键盘冲突", TextFieldKeyboardPage(), index: 0),
        ItemButton("TextFiled软键盘冲突2", TextFieldKeyboardPage2(), index: 0),
      ],
    );
  }
}

///TextFiled
class TextFieldWidgetPage extends BaseTitleBarWidget {
  ///编辑控制器
  late TextEditingController _searchController;

  ///用来控制  TextField 焦点的获取与关闭
  FocusNode focusNode = FocusNode();

  /// 是否显示删除按钮
  bool _hasDeleteIcon = false;

  @override
  void initState() {
    super.initState();
    _searchController = TextEditingController();

    ///添加获取焦点与失去焦点的兼听
    focusNode.addListener(() {
      ///当前兼听的 TextFeild 是否获取了输入焦点
      bool hasFocus = focusNode.hasFocus;

      ///当前 focusNode 是否添加了兼听
      bool hasListeners = focusNode.hasListeners;

      print("focusNode 兼听 hasFocus:$hasFocus  hasListeners:$hasListeners");
    });
  }

  @override
  Widget childWidget() {
    return SingleChildScrollView(
      padding: EdgeInsets.only(left: 20.w, right: 20.w),
      child: Column(
        children: <Widget>[
          SizedBox(height: 40.w),
          TextField(
            ///是否可编辑
            enabled: true,

            ///焦点获取  用来控制  TextField 焦点的获取与关闭
            focusNode: focusNode,

            ///用来配置 TextField 的样式风格
            decoration: InputDecoration(
              ///设置输入文本框的提示文字
              ///输入框获取焦点时 并且没有输入文字时
              hintText: "请输入用户名",

              ///设置输入文本框的提示文字的样式
              hintStyle: TextStyle(
                color: Colors.grey,
                textBaseline: TextBaseline.ideographic,
              ),

              ///输入框内的提示 输入框没有获取焦点时显示
              labelText: "用户名",
              labelStyle: TextStyle(color: Colors.blue),

              ///显示在输入框下面的文字
              helperText: "这里是帮助提示语",
              helperStyle: TextStyle(color: Colors.green),

              ///显示在输入框下面的文字
              ///会覆盖了 helperText 内容
              errorText: "这里是错误文本提示",
              errorStyle: TextStyle(color: Colors.red),

              ///输入框获取焦点时才会显示出来 输入文本的前面
              prefixText: "prefix",
              prefixStyle: TextStyle(color: Colors.deepPurple),

              ///输入框获取焦点时才会显示出来 输入文本的后面
              suffixText: "suf ",
              suffixStyle: TextStyle(color: Colors.black),

              ///文本输入框右下角显示的文本
              ///文字计数器默认使用
              counterText: "count",
              counterStyle: TextStyle(color: Colors.deepPurple[800]),

              ///输入文字前的小图标
              prefixIcon: Icon(Icons.phone),

              ///输入文字后面的小图标
              suffixIcon: Icon(Icons.close),

              ///与 prefixText 不能同时设置
//                prefix: Text("A") ,
              /// 与 suffixText 不能同时设置
//                suffix:  Text("B") ,
              ///设置边框
              ///   InputBorder.none 无下划线
              ///   OutlineInputBorder 上下左右 都有边框
              ///   UnderlineInputBorder 只有下边框  默认使用的就是下边框
              border: OutlineInputBorder(
                ///设置边框四个角的弧度
                borderRadius: BorderRadius.all(Radius.circular(10)),

                ///用来配置边框的样式
                borderSide: BorderSide(
                  ///设置边框的颜色
                  color: Colors.red,

                  ///设置边框的粗细
                  width: 2.0,
                ),
              ),

              ///设置输入框可编辑时的边框样式
              enabledBorder: OutlineInputBorder(
                ///设置边框四个角的弧度
                borderRadius: BorderRadius.all(Radius.circular(10)),

                ///用来配置边框的样式
                borderSide: BorderSide(
                  ///设置边框的颜色
                  color: Colors.blue,

                  ///设置边框的粗细
                  width: 2.0,
                ),
              ),
              disabledBorder: OutlineInputBorder(
                ///设置边框四个角的弧度
                borderRadius: BorderRadius.all(Radius.circular(10)),

                ///用来配置边框的样式
                borderSide: BorderSide(
                  ///设置边框的颜色
                  color: Colors.red,

                  ///设置边框的粗细
                  width: 2.0,
                ),
              ),

              ///用来配置输入框获取焦点时的颜色
              focusedBorder: OutlineInputBorder(
                ///设置边框四个角的弧度
                borderRadius: BorderRadius.all(Radius.circular(20)),

                ///用来配置边框的样式
                borderSide: BorderSide(
                  ///设置边框的颜色
                  color: Colors.green,

                  ///设置边框的粗细
                  width: 2.0,
                ),
              ),
            ),
          ),
          SizedBox(height: 40.w),
          TextField(
            style: TextStyle(height: 1.0, color: Colors.amber),
            keyboardType: TextInputType.text,
            decoration: InputDecoration(
              labelText: "请输入内容",
              helperText: "随便输入文字或数字",
              border: OutlineInputBorder(
                gapPadding: 10.0,
                borderRadius: BorderRadius.circular(80.w),
              ),
            ),
          ),
          SizedBox(height: 40.w),
          TextField(
            style: const TextStyle(height: 1.0, color: Colors.amber),
            keyboardType: TextInputType.text,
            decoration: InputDecoration(
              labelText: "请输入内容",
              helperText: "随便输入文字或数字",
              border: OutlineInputBorder(
                gapPadding: 10.0,
                borderRadius: BorderRadius.circular(10.0),
              ),
            ),
          ),
          SizedBox(height: 40.w),
          Container(
            //color: Colors.green,
            width: 400.w,
            child: TextField(
              style: TextStyle(height: 1.0, color: Colors.amber),
              keyboardType: TextInputType.text,
              decoration: InputDecoration(
                // 去掉输入框的边框
                border: OutlineInputBorder(
                  gapPadding: 10.0,
                  borderRadius: BorderRadius.circular(10.0),
                ),
                hintText: "请输入内容",
                // 输入框decoration属性
                contentPadding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 10.0),
                // 让文字垂直居中
                isCollapsed: true,
              ),
            ),
          ),
          SizedBox(height: 40.w),
          Container(
            //color: Colors.green,
            width: 400.w,
            child: TextField(
              style: TextStyle(height: 1.0, color: Colors.amber),
              keyboardType: TextInputType.text,
              decoration: InputDecoration(
                // 输入框的边框
                border: OutlineInputBorder(
                  gapPadding: 10.0,
                  borderRadius: BorderRadius.circular(20.0),
                  borderSide: BorderSide(color: Colors.red, width: 1),
                ),
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.purple, width: 1),
                  borderRadius: BorderRadius.circular(20.0),
                ),
                enabledBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.yellow, width: 1),
                  borderRadius: BorderRadius.circular(20.0),
                ),
                hintText: "请输入内容1",
                // 输入框decoration属性
                contentPadding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 10.0),
                // 让文字垂直居中
                isCollapsed: true,
              ),
            ),
          ),
          SizedBox(height: 40.w),
          Container(
            ///控制高度
            height: 68.w,
            child: TextField(
              controller: _searchController,
              onChanged: (value) {
                setState(() {});
              },

              ///输入内容文字样式, 设置该样式会和hintStyle样式相同，缩有对应不用的要设置
              style: TextStyle(
                color: Color(0xff75624B),
                fontWeight: FontWeight.w500,
                fontSize: 28.sp,
              ),

              ///输入框样式
              decoration: InputDecoration(
                ///提示文字
                hintText: "搜索你想读的书",
                hintStyle: TextStyle(
                  fontSize: 24.sp,
                  color: Color(0xff75624B),
                  fontWeight: FontWeight.w400,
                ),

                ///去掉输入框边框，设置角度为80，
                border: OutlineInputBorder(
                  borderSide: BorderSide.none,
                  borderRadius: BorderRadius.circular(80.w),
                ),

                ///设置背景色，可以修改背景色的只有fillColor这个属性。但是只设置fillColor属性是不行的，还必须同时设置filled为 true
                filled: true,
                fillColor: const Color(0xffF5F3F0),

                /// 输入框decoration属性
                //contentPadding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 10.0),
                contentPadding: EdgeInsets.only(left: 0, top: 10, right: 0, bottom: 9),

                /// 让文字垂直居中
                isCollapsed: true,

                ///输入框左边图片,只有设置了prefixIconConstraints后，width和height才生效
                prefixIcon: GestureDetector(
                  onTap: () {
                    Fluttertoast.showToast(msg: "点击了左边搜索图标");
                  },
                  child: Padding(
                    padding: EdgeInsets.only(left: 20.w, right: 10.w),
                    child: Image.asset(
                      "assets/images/icon_search.png",
                      width: 38.w,
                      height: 38.w,
                    ),
                  ),
                ),
                prefixIconConstraints: BoxConstraints(),

                ///输入框右边图片,只有设置了prefixIconConstraints后，width和height才生效
                suffixIcon: GestureDetector(
                  onTap: () {
                    if (_searchController.text.isEmpty) {
                      Fluttertoast.showToast(msg: "点击了扫描二维码");
                    } else {
                      setState(() {
                        _searchController.text = "";
                      });
                    }
                  },
                  child: Padding(
                    padding: EdgeInsets.only(left: 10.w, right: 20.w),
                    child: _searchController.text.isEmpty
                        ? Image.asset(
                            "assets/images/icon_scan_qr_code.png",
                            width: 40.w,
                            height: 40.w,
                          )
                        : Image.asset(
                            "assets/images/icon_search_close.png",
                            width: 28.w,
                            height: 28.w,
                          ),
                  ),
                ),
                suffixIconConstraints: BoxConstraints(),
              ),
            ),
          ),
          SizedBox(height: 1000.w),
        ],
      ),
    );
  }

  @override
  String title() {
    return "TextField Widget";
  }
}

///TextFiled 和底部软键盘遮挡处理
class TextFieldKeyboardPage extends StatefulWidget {
  const TextFieldKeyboardPage({Key? key}) : super(key: key);

  @override
  State<TextFieldKeyboardPage> createState() => _TextFieldKeyboardPageState();
}

class _TextFieldKeyboardPageState extends State<TextFieldKeyboardPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return KeyboardVisibilityBuilder(builder: (context, isKeyboardVisible) {
      print("zm1234 isKeyboardVisible:$isKeyboardVisible");
      return Scaffold(
        appBar: CommonAppBar(context, "TextFiled和底部软键盘遮挡"),
        body: Column(
          children: [
            Expanded(
              child: Container(
                color: Color(0xFFFFCDD2),
                width: 200.w,
                alignment: Alignment.center,
                child: Text(
                  "上部内容",
                  style: TextStyle(fontSize: 50.sp, color: Colors.black),
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(bottom: isKeyboardVisible ? 540.w : 0.w),
              color: Colors.yellow,
              child: TextField(),
            )
          ],
        ),
      );
    });
  }
}

///TextFiled 和底部软键盘遮挡处理
class TextFieldKeyboardPage2 extends StatefulWidget {
  const TextFieldKeyboardPage2({Key? key}) : super(key: key);

  @override
  State<TextFieldKeyboardPage2> createState() => _TextFieldKeyboardPage2State();
}

class _TextFieldKeyboardPage2State extends State<TextFieldKeyboardPage2>
    with WidgetsBindingObserver, KeyboardHeight {
  late StreamSubscription<bool> keyboardSubscription;
  bool isKeyboardVisible = false;
  double keyBoardHeight = 0;

  @override
  void initState() {
    super.initState();
    // Subscribe
    keyboardSubscription = KeyboardVisibilityController().onChange.listen((bool visible) {
      print('Keyboard visibility update. Is visible: $visible');
      setState(() {
        isKeyboardVisible = visible;
      });
    });
  }

  @override
  keyboardHeight(double height) {
    // 高度
    print('zm1234 keyboardHeight: $height');
    setState(() {
      keyBoardHeight = height;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "TextFiled和底部软键盘遮挡2"),
      body: Column(
        children: [
          Expanded(
            child: Container(
              color: Color(0xFFFFCDD2),
              width: 200.w,
              alignment: Alignment.center,
              child: Text(
                "上部内容",
                style: TextStyle(fontSize: 50.sp, color: Colors.black),
              ),
            ),
          ),
          Container(
            margin: EdgeInsets.only(
                bottom: isKeyboardVisible ? keyBoardHeight : 0.w),
            color: Colors.yellow,
            child: TextField(),
          )
        ],
      ),
    );
  }
}

///TextField 属性
// TextField({
//   Key key,
//   this.controller,//控制器
//   this.focusNode,//焦点
//   this.obscureText = false,//是否隐藏文本，即显示密码类型
//   this.maxLines = 1,//最多行数，高度与行数同步
//   this.autofocus = false,//自动聚焦
//   this.decoration = const InputDecoration(),//装饰
//   TextInputType keyboardType,//键盘类型，即输入类型
//   this.onChanged,//输入改变回调
//
//   //以下属性不常用,用到再来查看
//   this.textInputAction,//键盘按钮
//   this.textCapitalization = TextCapitalization.none,//大小写
//   this.style,//样式
//   this.strutStyle,
//   this.textAlign = TextAlign.start,//对齐方式
//   this.textDirection,
//   this.autocorrect = true,//自动更正
//   this.minLines,//最小行数
//   this.expands = false,
//   this.maxLength,//最多输入数，有值后右下角就会有一个计数器
//   this.maxLengthEnforced = true,
//   this.onEditingComplete,//输入完成时，配合TextInputAction.done使用
//   this.onSubmitted,//提交时,配合TextInputAction
//   this.inputFormatters,//输入校验
//   this.enabled,//是否可用
//   this.cursorWidth = 2.0,//光标宽度
//   this.cursorRadius,//光标圆角
//   this.cursorColor,//光标颜色
//   this.keyboardAppearance,
//   this.scrollPadding = const EdgeInsets.all(20.0),
//   this.dragStartBehavior = DragStartBehavior.start,
//   this.enableInteractiveSelection,
//   this.onTap,//点击事件
//   this.buildCounter,
//   this.scrollPhysics,
// })

///InputDecoration 属性
// InputDecoration({
//   this.icon,                  // 装饰器外小图标
//   this.labelText,             // 文本框描述标签
//   this.labelStyle,            // 文本框描述标签样式
//   this.helperText,            // 文本框辅助标签
//   this.helperStyle,           // 文本框辅助标签样式
//   this.hintText,              // 文本框默认提示信息
//   this.hintStyle,             // 文本框默认提示信息样式
//   this.hintMaxLines,          // 文本框默认提示信息最大行数
//   this.errorText,             // 文本框错误提示信息
//   this.errorStyle,            // 文本框错误提示信息样式
//   this.errorMaxLines,         // 文本框错误提示信息最大行数
//   this.hasFloatingPlaceholder = true,     // 文本框获取焦点后 labelText 是否向上浮动
//   this.isDense,               // 是否问紧凑型文本框
//   this.contentPadding,        // 文本内边距
//   this.prefixIcon,            // 前置图标
//   this.prefix,                // 前置预填充 Widget
//   this.prefixText,            // 前置预填充文本
//   this.prefixStyle,           // 前置预填充样式
//   this.suffixIcon,            // 后置图标
//   this.suffix,                // 后置预填充 Widget
//   this.suffixText,            // 后置预填充文本
//   this.suffixStyle,           // 后置预填充样式
//   this.counter,               // 输入框右下角 Widget
//   this.counterText,           // 输入框右下角文本
//   this.counterStyle,          // 输入框右下角样式
//   this.filled,                // 是否颜色填充文本框
//   this.fillColor,             // 填充颜色
//   this.errorBorder,           // errorText 存在时未获取焦点边框
//   this.focusedBorder,         // 获取焦点时边框
//   this.focusedErrorBorder,    // errorText 存在时获取焦点边框
//   this.disabledBorder,        // 不可用时边框
//   this.enabledBorder,         // 可用时边框
//   this.border,                // 边框
//   this.enabled = true,        // 输入框是否可用
//   this.semanticCounterText,
//   this.alignLabelWithHint,    // 覆盖将标签与 TextField 的中心对齐
// })
