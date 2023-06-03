import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

//GestureDetector在Flutter中负责处理跟用户的简单手势交互，GestureDetector控件没有图像展示，
// 只是检测用户输入的手势，并作出相应的处理，包括点击、拖动和缩放。许多控件使用GestureDetector为其他控件提供回调，
// 比如IconButton、RaisedButton和FloatingActionButton控件有onPressed回调，当用户点击控件时触发回调，当用户点击控件时触发回调。
class GestureDetectorWidgetPage extends StatelessWidget {

  const GestureDetectorWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      appBar:  AppBar(
        title:  const Text("GestureDetector Widget"),
      ),
      body: Center(
        child:  GestureDetector(
          child:  const Text("我被赋予了点击触摸能力..."),
          onTap: (){
            print("------onTap");
          },
          onDoubleTap: (){
            print("------onDoubleTap");
          },
          onLongPress: (){
            print("------onLongPress");
          },
          onVerticalDragStart: (details){
            print("在垂直方向开始位置:"+details.globalPosition.toString());
          },
          onVerticalDragEnd: (details){
            print("在垂直方向结束位置:"+details.primaryVelocity.toString());
          },
        ),
      ),
    );
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

