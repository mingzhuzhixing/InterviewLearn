// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/base/base_widget.dart';
import 'package:flutter_module/bean/card_button.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_module/widget/ys_card_button_view.dart';
import 'package:flutter_module/widget/ys_image_style.dart';
import 'package:flutter_module/widget/ys_text_direction.dart';
import 'package:flutter_module/widget/ys_text_image.dart';
import 'package:flutter_module/widget/ys_text_style.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * button 分为三类：
 *  1、OutlineButton 默认带有一个边框，我们可以通过属性指定正常状态，跟用户点击状态下的边框颜色。
 *  2、RaisedButton、FlatButton、MaterialButton、RawMaterialButton
 *  3、FloatingActionButton
 *
 * RaisedButton是一个material风格”凸起“的按钮
 * DropdownButton DropdownButton为下拉选择按钮
 */
class ButtonWidgetPage extends StatefulWidget {
  const ButtonWidgetPage({Key? key}) : super(key: key);

  @override
  State<ButtonWidgetPage> createState() => _ButtonWidgetPageState();
}

class _ButtonWidgetPageState extends State<ButtonWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Button Widget"),
      ),
      body: Column(
        children: [
          ItemButton("下拉选择按钮", DropdownButtonPage(), index: 0),
          ItemButton("下拉菜单按钮", PopupMenuButtonPage(), index: 0),
          ItemButton("MaterialButton 按钮", MaterialButtonPage(), index: 0),
          ItemButton("FloatingActionButton 按钮", FloatingActionButtonPage(), index: 0),
          ItemButton("TextButton 按钮", TextButtonPage(), index: 0),
          ItemButton("IconButton 按钮", IconButtonPage(), index: 0),
          ItemButton("其他按钮", OtherButtonPage(), index: 0),
        ],
      ),
    );
  }
}

/**
 * 下拉选择按钮
 */
class DropdownButtonPage extends BaseTitleBarWidget {
  String _dropValue = '语文';

  @override
  Widget childWidget() {
    return DropdownButton(
      value: _dropValue,
      items: [
        DropdownMenuItem(child: Text('语文'), value: '语文'),
        DropdownMenuItem(child: Text('数学'), value: '数学'),
        DropdownMenuItem(child: Text('英语'), value: '英语'),
      ],
      onChanged: (value) {
        setState(() {
          _dropValue = value.toString();
        });
      },
    );
  }

  @override
  String title() {
    return "DropdownButton 下拉按钮";
  }
}

/**
 * 下拉菜单按钮
 */
class PopupMenuButtonPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return PopupMenuButton<String>(
      initialValue: '语文',
      itemBuilder: (context) {
        return <PopupMenuEntry<String>>[
          PopupMenuItem<String>(
            value: '语文',
            child: Text('语文'),
          ),
          PopupMenuItem<String>(
            value: '数学',
            child: Text('数学'),
          ),
          PopupMenuItem<String>(
            value: '英语',
            child: Text('英语'),
          ),
          PopupMenuItem<String>(
            value: '生物',
            child: Text('生物'),
          ),
          PopupMenuItem<String>(
            value: '化学',
            child: Text('化学'),
          ),
        ];
      },
    );
  }

  @override
  String title() {
    return "下拉菜单按钮 PopupMenuButton";
  }
}

/**
 * MaterialButton 按钮
 */
class MaterialButtonPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Row(),
          MaterialButton(
            onPressed: () {
              print("MaterialButton");
            },
            child: Text("MaterialButton"),
          ),
          MaterialButton(
            onPressed: () {},
            textColor: Colors.white,
            highlightColor: Colors.deepOrange,
            shape: RoundedRectangleBorder(
              //圆角矩形
              borderRadius: BorderRadius.circular(5.0),
            ),
            child: Text("MaterialButton"),
            color: Colors.blue,
            //背景颜色
            splashColor: Colors.purple, //点击按钮时水波纹的颜色
          ),
          MaterialButton(
            onPressed: () {},
            textColor: Colors.white,
            highlightColor: Colors.deepOrange,
            shape: BeveledRectangleBorder(
              // 带斜角的长方形边框
              borderRadius: BorderRadius.circular(20.0),
            ),
            child: Text("MaterialButton"),
            color: Colors.blue, //背景颜色
          ),
          MaterialButton(
            onPressed: () {},
            textColor: Colors.white,
            highlightColor: Colors.deepOrange,
            shape: CircleBorder(
                //圆形边框
                ),
            child: Text("MaterialButton"),
            color: Colors.blue, //背景颜色
          ),
          MaterialButton(
            onPressed: () {},
            textColor: Colors.white,
            highlightColor: Colors.deepOrange,
            shape: RoundedRectangleBorder(
              //圆角矩形
              borderRadius: BorderRadius.circular(5.0),
              side: BorderSide(
                color: Colors.red,
                width: 1,
              ),
            ),
            child: Text("MaterialButton"),
            color: Colors.blue,
            //背景颜色
            splashColor: Colors.purple, //点击按钮时水波纹的颜色
          ),
          MaterialButton(
            onPressed: () {},
            textColor: Colors.blue,
            splashColor: Colors.transparent,
            highlightColor: Colors.transparent,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(5.0),
              side: BorderSide(
                color: Colors.red,
                width: 1,
              ),
            ),
            child: Text("MaterialButton"),
          ),
          MaterialButton(
            height: 50.w,
            padding: EdgeInsets.only(left: 10.w, right: 10.w),
            minWidth: 20.w,
            onPressed: () {},
            textColor: Colors.white,
            splashColor: Colors.transparent,
            highlightColor: Colors.transparent,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(5.0),
            ),
            child: Text(
              "去学习",
              style: TextStyle(fontSize: 24.sp),
            ),
            color: Colors.blue,
          ),
          MaterialButton(
            height: 50.w,
            padding: EdgeInsets.only(left: 10.w, right: 10.w),
            minWidth: 20.w,
            onPressed: () {},
            textColor: Colors.green,
            splashColor: Colors.transparent,
            highlightColor: Colors.transparent,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(5.0),
              side: BorderSide(color: Colors.green),
            ),
            child: Text(
              "去学习",
              style: TextStyle(fontSize: 24.sp),
            ),
          ),
          Container(
            height: 70.w,
            width: 1.sw * 0.8,
            child: MaterialButton(
              onPressed: () {},
              child: Text("RaisedButton"),
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20.0),
                side: BorderSide(width: 2.0, color: Color(0xFF000000)),
              ),
            ),
          ),
          MaterialButton(
            onPressed: () {},
            child: Text("MaterialButton"),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(20.0),
              side: BorderSide(width: 2.0, color: Color(0xFF000000)),
            ),
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
        ],
      ),
    );
  }

  @override
  String title() {
    return "MaterialButton Widget";
  }
}

class FloatingActionButtonPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return Column(
      children: [
        FloatingActionButton(
          onPressed: () {
            print("FloatingActionButton");
          },
          child: Text("FAB"),
          backgroundColor: Colors.blueAccent,
          foregroundColor: Colors.red,
        )
      ],
    );
  }

  @override
  String title() {
    return 'FloatingActionButton Widget';
  }
}

/**
 * TextButton 按钮
 */
class TextButtonPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return SingleChildScrollView(
      child: Column(
        children: [
          TextButton(
            onPressed: () {},
            child: Text("TextButton"),
          ),
          TextButton(
            onPressed: () {},
            child: Text("TextButton"),
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all<Color>(Colors.yellow),
              shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                ),
              ),
            ),
          ),
          TextButton(
            onPressed: () {},
            child: Text("TextButton", style: TextStyle(fontSize: 14, color: Colors.white)),
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all<Color>(Colors.deepOrange),
              shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.all(Radius.circular(50.w)),
                ),
              ),
              padding: MaterialStateProperty.all(EdgeInsets.only(left: 40.w, right: 40.w)),
            ),
          ),
          Container(
            height: 60.w,
            width: 120.w,
            child: TextButton(
              onPressed: () {},
              child: Text("确定", style: TextStyle(fontSize: 22.sp, color: Colors.white)),
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                shape: MaterialStateProperty.all<OutlinedBorder>(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.all(Radius.circular(50.w)),
                  ),
                ),
              ),
            ),
          ),
          SizedBox(height: 10.w),
          TextButton(
            onPressed: () {},
            child: Text("确定2", style: TextStyle(fontSize: 22.sp, color: Colors.white)),
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all<Color>(Colors.blue),
              shape: MaterialStateProperty.all<OutlinedBorder>(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.all(Radius.circular(50.w)),
                ),
              ),
              padding: MaterialStateProperty.all(EdgeInsets.zero),
              //minimumSize: MaterialStateProperty.all(Size(120, 50)),
              maximumSize: MaterialStateProperty.all(Size(120, 50)),
            ),
          ),
          SizedBox(height: 10.w),
          Container(
            height: 100.w,
            width: 1.sw,
            margin: EdgeInsets.only(left: 20.w, right: 20.w),
            child: TextButton(
              onPressed: () {},
              child: Text("TextButton", style: TextStyle(fontSize: 14, color: Colors.white)),
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.green),
                shape: MaterialStateProperty.all(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.all(Radius.circular(50.w)),
                  ),
                ),
                padding: MaterialStateProperty.all(EdgeInsets.only(left: 40.w, right: 40.w)),
              ),
            ),
          ),
          SizedBox(height: 10.w),
          TextButton(
            onPressed: () {},
            child: Text("TextButton", style: TextStyle(fontSize: 14, color: Colors.green)),
            style: ButtonStyle(
              //backgroundColor: MaterialStateProperty.all(Colors.deepOrange),
              shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.all(Radius.circular(50.w)),
                  side: BorderSide(color: Colors.green),
                ),
              ),
              padding: MaterialStateProperty.all(EdgeInsets.only(left: 40.w, right: 40.w)),
            ),
          ),
          TextButton(
            onPressed: () {},
            child: Text("TextButton", style: TextStyle(fontSize: 14, color: Colors.green)),
            style: ButtonStyle(
              //backgroundColor: MaterialStateProperty.all(Colors.deepOrange),
              shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.all(Radius.circular(10.w)),
                  side: BorderSide(color: Colors.green),
                ),
              ),
              padding: MaterialStateProperty.all(EdgeInsets.only(left: 40.w, right: 40.w)),
            ),
          ),
        ],
      ),
    );
  }

  @override
  String title() {
    return 'TextButton Widget';
  }
}

/**
 * IconButton
 */
class IconButtonPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return Column(
      children: [
        Row(),
        Container(
          child: IconButton(onPressed: () {}, icon: Icon(Icons.settings)),
          color: Colors.blue,
        ),
        SizedBox(height: 10),
        IconButton(
          icon: Icon(Icons.thumb_up),
          onPressed: () {},
        ),
        SizedBox(height: 10),
        Container(
          child: IconButton(
            onPressed: () {},
            icon: Icon(
              Icons.settings,
              color: Colors.red,
            ),
            padding: EdgeInsets.zero,
            iconSize: 50.w,
          ),
          color: Colors.blue,
        ),
        SizedBox(height: 10),
        Container(
          height: 50.w,
          width: 50.w,
          child: IconButton(
            onPressed: () {},
            icon: Icon(
              Icons.settings,
              color: Colors.red,
            ),
            padding: EdgeInsets.zero,
            iconSize: 50.w,
          ),
          color: Colors.blue,
        ),
      ],
    );
  }

  @override
  String title() {
    return 'IconButton Widget';
  }
}

/**
 * 其他按钮
 */
class OtherButtonPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return CustomScrollView(
      physics: BouncingScrollPhysics(),
      slivers: [
        SliverList(
          delegate: SliverChildBuilderDelegate(
            (BuildContext content, int index) {
              return Column(
                children: <Widget>[
                  BackButton(),
                  CloseButton(),
                  Container(
                    child: Text(
                      "取消",
                      style: TextStyle(fontSize: 32.sp),
                    ),
                    padding: EdgeInsets.only(left: 40.w, top: 8.w, right: 40.w, bottom: 8.w),
                    decoration: BoxDecoration(
                        border: Border.all(color: Colors.red, width: 1.w),
                        borderRadius: BorderRadius.all(Radius.circular(20))),
                  ),
                  SizedBox(height: 20.w),
                  _buttonImageTextWidget(),
                  SizedBox(height: 20.w),
                  _cardButton1Widget(),
                  SizedBox(height: 20.w),
                  _cardButton2Widget(),
                  SizedBox(height: 20.w),
                  _cardButton3Widget(),
                  SizedBox(height: 20.w),
                  _cardButton4Widget(),
                  SizedBox(height: 20.w),
                  _cardButton5Widget(),
                  SizedBox(height: 20.w),
                  _cardButton6Widget()
                ],
              );
            },
            childCount: 1,
          ),
        ),
      ],
    );
  }

  @override
  String title() {
    return '其他Button Widget';
  }

  Widget _buttonImageTextWidget() {
    return Container(
      color: Colors.yellow,
      height: 80.w,
      child: YsTextImage(
        "wode_baiding_dengji",
        "文本文本文本文本文本文本文文本文本文本文本文文本文本文本文本文文本文本文本文本文本文",
        direction: YsTextDirection.il_tr,
        textStyle: YsTextStyle(maxLines: 1, overflow: TextOverflow.ellipsis),
        imageStyle: YsImageStyle(width: 64.w, height: 25.w),
        matchParent: true,
      ),
    );
  }

  Widget _cardButton1Widget() {
    CardButton cardButton = CardButton();
    cardButton.label = "去学习1122";
    return Container(
      color: Colors.yellow,
      child: TextCardButton(
        cardButton,
      ),
    );
  }

  Widget _cardButton2Widget() {
    CardButton cardButton = CardButton();
    cardButton.label = "去学习";
    return Container(
      margin: EdgeInsets.all(20.w),
      child: TextCardButton(
        cardButton,
        decoration: BoxDecoration(
          color: Colors.teal,
          borderRadius: BorderRadius.circular(8.w),
        ),
        padding: EdgeInsets.only(left: 15.w, top: 4.w, right: 15.w, bottom: 9.w),
        textStyle: YsTextStyle(color: Colors.white),
      ),
    );
  }

  Widget _cardButton3Widget() {
    CardButton cardButton = CardButton();
    cardButton.label = "去学习";
    return Container(
      margin: EdgeInsets.all(20.w),
      child: TextCardButton(
        cardButton,
        decoration: BoxDecoration(
          border: Border.all(color: Colors.purple, width: 1.w),
          borderRadius: BorderRadius.circular(8.w),
        ),
        padding: EdgeInsets.only(left: 15.w, top: 4.w, right: 15.w, bottom: 9.w),
        textStyle: YsTextStyle(color: Colors.purple),
      ),
    );
  }

  Widget _cardButton4Widget() {
    CardButton cardButton = CardButton();
    cardButton.icon = "ic_nav_discover_actived";
    return Container(
      margin: EdgeInsets.all(20.w),
      color: Colors.red,
      child: ImageCardButton(
        cardButton,
        imageStyle: YsImageStyle(width: 68.w, height: 68.w),
      ),
    );
  }

  Widget _cardButton5Widget() {
    CardButton cardButton = CardButton();
    cardButton.label = "去学习去学";
    cardButton.icon = "ic_nav_discover_actived";
    return UnconstrainedBox(
      child: Container(
        margin: EdgeInsets.all(20.w),
        color: Colors.red,
        child: ImageAndTextCardButton(
          cardButton,
          imageStyle: YsImageStyle(width: 68.w, height: 68.w),
          textStyle: YsTextStyle(color: Colors.white, overflow: TextOverflow.ellipsis, maxLines: 1),
          matchParent: false,
        ),
      ),
    );
  }

  Widget _cardButton6Widget() {
    CardButton cardButton = CardButton();
    cardButton.label = "去学习去学去学习去学去学习去学去学习去学去学习去学去学习去学去学习去学";
    cardButton.icon = "ic_nav_discover_actived";
    return Container(
      width: 1.sw,
      margin: EdgeInsets.all(20.w),
      color: Colors.red,
      child: ImageAndTextCardButton(
        cardButton,
        imageStyle: YsImageStyle(width: 68.w, height: 68.w),
        textStyle: YsTextStyle(color: Colors.white, overflow: TextOverflow.ellipsis, maxLines: 1),
        matchParent: true,
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
