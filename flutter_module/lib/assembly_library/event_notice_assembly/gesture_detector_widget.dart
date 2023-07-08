// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/base/base_widget.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';

//GestureDetector在Flutter中负责处理跟用户的简单手势交互，GestureDetector控件没有图像展示，
// 只是检测用户输入的手势，并作出相应的处理，包括点击、拖动和缩放。许多控件使用GestureDetector为其他控件提供回调，
// 比如IconButton、RaisedButton和FloatingActionButton控件有onPressed回调，当用户点击控件时触发回调，当用户点击控件时触发回调。
class GestureDetectorHomePage extends StatelessWidget {
  const GestureDetectorHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: CommonAppBar(context, "GestureDetector Widget"),
      ),
      body: Column(
        children: [
          ItemButton("点击、双击、长按", BasicTapEventPage(), index: 0),
          ItemButton("拖动、滑动", DragEventPage(), index: 0),
          ItemButton("单一方向拖动", DragVerticaPage(), index: 0),
          ItemButton("缩放", ScaleEventPage(), index: 0),
        ],
      ),
    );
  }
}

/**
 * 基本事件  点击、双击、长按
 */
class BasicTapEventPage extends BaseTitleBarWidget {
  String _operation = "No Gesture detected!"; //保存事件名

  @override
  Widget childWidget() {
    return Center(
      child: GestureDetector(
        child: Container(
          alignment: Alignment.center,
          color: Colors.blue,
          width: 200.0,
          height: 100.0,
          child: Text(
            _operation,
            style: TextStyle(color: Colors.white),
          ),
        ),
        onTap: () {
          setState(() {
            _operation = "onTap";
          });
        },
        onDoubleTap: () {
          setState(() {
            _operation = "onDoubleTap";
          });
        },
        onLongPress: () {
          setState(() {
            _operation = "onLongPress";
          });
        },
      ),
    );
  }

  @override
  String title() {
    return "点击、双击、长按";
  }
}

/**
 * 拖动、滑动
 * DragDownDetails.globalPosition：当用户按下时，此属性为用户按下的位置相对于屏幕（而非父组件）原点(左上角)的偏移。
 * DragUpdateDetails.delta：当用户在屏幕上滑动时，会触发多次Update事件，delta指一次Update事件的滑动的偏移量。
 * DragEndDetails.velocity：该属性代表用户抬起手指时的滑动速度(包含x、y两个轴的），示例中并没有处理手指抬起时的速度，常见的效果是根据用户抬起手指时的速度做一个减速动画。
 */
class DragEventPage extends BaseTitleBarWidget {
  double _top = 0.0; //距顶部的偏移
  double _left = 0.0; //距左边的偏移

  @override
  Widget childWidget() {
    return Stack(
      children: <Widget>[
        Positioned(
          top: _top,
          left: _left,
          child: GestureDetector(
            child: CircleAvatar(child: Text("A")),
            //手指按下时会触发此回调
            onPanDown: (DragDownDetails e) {
              //打印手指按下的位置(相对于屏幕)
              print("用户手指按下：${e.globalPosition}");
            },
            //手指滑动时会触发此回调
            onPanUpdate: (DragUpdateDetails e) {
              //用户手指滑动时，更新偏移，重新构建
              setState(() {
                _left += e.delta.dx;
                _top += e.delta.dy;
              });
            },
            onPanEnd: (DragEndDetails e) {
              //打印滑动结束时在x、y轴上的速度
              print(e.velocity);
            },
          ),
        )
      ],
    );
  }

  @override
  String title() {
    return "拖动、滑动";
  }
}

/**
 * 单一方向上拖动
 * onVerticalDragUpdate：垂直方向拖动
 */
class DragVerticaPage extends BaseTitleBarWidget {
  double _top = 0.0;
  double _left = 0.0;

  @override
  Widget childWidget() {
    return Stack(
      children: <Widget>[
        Positioned(
          top: _top,
          left: _left,
          child: GestureDetector(
            child: CircleAvatar(child: Text("A")),
            //垂直方向拖动事件
            onVerticalDragUpdate: (DragUpdateDetails details) {
              setState(() {
                _top += details.delta.dy;
              });
            },
            //水平方向拖动事件
            onHorizontalDragUpdate: (DragUpdateDetails details) {
              setState(() {
                _left += details.delta.dx;
              });
            },
          ),
        )
      ],
    );
  }

  @override
  String title() {
    return "单一方向上拖动";
  }
}

/**
 * 缩放
 */
class ScaleEventPage extends BaseTitleBarWidget {
  double _width = 100.0; //通过修改图片宽度来达到缩放效果

  @override
  Widget childWidget() {
    return Center(
      child: GestureDetector(
        //指定宽度，高度自适应
        child: Image.asset("assets/images/cover_img.jpg", width: _width),
        onScaleUpdate: (ScaleUpdateDetails details) {
          setState(() {
            //缩放倍数在0.8到10倍之间
            _width = 200 * details.scale.clamp(0.8, 10.0);
          });
        },
      ),
    );
  }

  @override
  String title() {
    return "缩放";
  }
}

///GestureDetector 属性
// GestureDetector({
// Key key,
// this.child,
// this.onTapDown,
// this.onTapUp,
// this.onTap,
// this.onTapCancel,
// this.onDoubleTap,
// this.onLongPress,
// this.onLongPressUp,
// this.onVerticalDragDown,
// this.onVerticalDragStart,
// this.onVerticalDragUpdate,
// this.onVerticalDragEnd,
// this.onVerticalDragCancel,
// this.onHorizontalDragDown,
// this.onHorizontalDragStart,
// this.onHorizontalDragUpdate,
// this.onHorizontalDragEnd,
// this.onHorizontalDragCancel,
// this.onForcePressStart,
// this.onForcePressPeak,
// this.onForcePressUpdate,
// this.onForcePressEnd,
// this.onPanDown,
// this.onPanStart,
// this.onPanUpdate,
// this.onPanEnd,
// this.onPanCancel,
// this.onScaleStart,
// this.onScaleUpdate,
// this.onScaleEnd,
// this.behavior,
// this.excludeFromSemantics = false
// })
