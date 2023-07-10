import 'package:flutter/material.dart';

///Row布局
/**
 *  //start：将children放置在主轴的起点；
    //center：将children放置在主轴的中心；
    //end：将children放置在主轴的末尾；
    //spaceAround：将主轴方向上的空白区域均分，使得children之间的空白区域相等，但是首尾child的空白区域为1/2；
    //spaceBetween：将主轴方向上的空白区域均分，使得children之间的空白区域相等，首尾child都靠近首尾，没有间隙；
    //spaceEvenly：将主轴方向上的空白区域均分，使得children之间的空白区域相等，包括首尾child；
 */
class RowWidgetPage extends StatelessWidget {
  const RowWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Layout Widget"),
      ),
      body: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              MaterialButton(
                onPressed: () {
                  print("红色按钮");
                },
                color: const Color(0xffff0000),
                child: Text("红色按钮"),
              ),
              MaterialButton(
                onPressed: () {
                  print("点击蓝色按钮");
                },
                color: const Color(0xff000099),
                child: const Text(
                  "蓝色按钮",
                  style: TextStyle(color: Color(0xffffffff)),
                ),
              ),
              MaterialButton(
                onPressed: () {
                  print("点击粉色按钮");
                },
                color: const Color(0xffee9999),
                child: Text("粉色按钮"),
              )
            ],
          ),
          Stack(
            children: [
              Align(
                alignment: Alignment.centerLeft,
                child: Container(
                  width: 100,
                  height: 40,
                  alignment: Alignment.center,
                  color: Colors.red,
                  child: Text('left'),
                ),
              ),
              Align(
                alignment: Alignment.center,
                child: Container(
                  width: 100,
                  height: 40,
                  alignment: Alignment.center,
                  color: Colors.blue,
                  child: Text('middle'),
                ),
              ),
              Align(
                alignment: Alignment.centerRight,
                child: Container(
                  width: 100,
                  height: 40,
                  alignment: Alignment.center,
                  color: Colors.yellow,
                  child: Text('right'),
                ),
              )
            ],
          ),
          SizedBox(height: 20),
          Stack(
            children: [
              Align(
                alignment: Alignment.centerLeft,
                child: Container(
                  width: 100,
                  height: 40,
                  alignment: Alignment.center,
                  color: Colors.red,
                  child: Text('left'),
                ),
              ),
              Align(
                alignment: Alignment.center,
                child: Container(
                  width: 100,
                  height: 40,
                  alignment: Alignment.center,
                  color: Colors.blue,
                  child: Text('middle'),
                ),
              ),
            ],
          ),
          SizedBox(height: 20),
          Stack(
            children: [
              Align(
                alignment: Alignment.centerLeft,
                child: Container(
                  width: 100,
                  height: 40,
                  alignment: Alignment.center,
                  color: Colors.red,
                  child: Text('left'),
                ),
              ),
            ],
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
