import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

///Row布局
class RowWidgetPage extends StatelessWidget {
  const RowWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Layout Widget"),
      ),
      body: Row(
        //start：将children放置在主轴的起点；
        //center：将children放置在主轴的中心；
        //end：将children放置在主轴的末尾；
        //spaceAround：将主轴方向上的空白区域均分，使得children之间的空白区域相等，但是首尾child的空白区域为1/2；
        //spaceBetween：将主轴方向上的空白区域均分，使得children之间的空白区域相等，首尾child都靠近首尾，没有间隙；
        //spaceEvenly：将主轴方向上的空白区域均分，使得children之间的空白区域相等，包括首尾child；
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: <Widget>[
          RaisedButton(
            onPressed: () {
              print("红色按钮");
            },
            color: const Color(0xffff0000),
            child: Text("红色按钮"),
          ),
          RaisedButton(
            onPressed: () {
              print("点击蓝色按钮");
            },
            color: const Color(0xff000099),
            child: Text(
              "蓝色按钮",
              style: TextStyle(color: const Color(0xffffffff)),
            ),
          ),
          RaisedButton(
            onPressed: () {
              print("点击粉色按钮");
            },
            color: const Color(0xffee9999),
            child: Text("粉色按钮"),
          )
        ],
      ),
    );
  }
}

// Flutter中容器：
//    Row、Column
//    Stack
//    Center
//    Container
//    ListView
//    Align
//    Padding
//    SizedBox
//    AspectRadio
//    DecoratedBox
//    Opactity

// Row({
// Key key,
// MainAxisAlignment mainAxisAlignment = MainAxisAlignment.start,
// MainAxisSize mainAxisSize = MainAxisSize.max,
// CrossAxisAlignment crossAxisAlignment = CrossAxisAlignment.center,
// TextDirection textDirection,
// VerticalDirection verticalDirection = VerticalDirection.down,
// TextBaseline textBaseline,
// List<Widget> children = const <Widget>[],
// })
