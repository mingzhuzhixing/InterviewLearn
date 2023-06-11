import 'package:flutter/material.dart';

///ListView属性
// ListView({
//   Key key,
//   Axis scrollDirection: Axis.vertical,//滚动方向
//   bool reverse: false,//是否反向显示数据
//   ScrollController controller,
//   bool primary,
//   ScrollPhysics physics,//物理滚动
//   bool shrinkWrap: false,
//   EdgeInsetsGeometry padding,
//   this.itemExtent,//item有效范围
//   bool addAutomaticKeepAlives: true,//自动保存视图缓存
//   bool addRepaintBoundaries: true,//添加重绘边界
//   List<Widget> children: const <Widget>[],
// })
class ListViewWidgetPage extends StatelessWidget {
  const ListViewWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("ListView"),
      ),
      body: ListView(
        scrollDirection: Axis.vertical,
        children: <Widget>[
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.lightBlue,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.red,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.deepOrange,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.yellow,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.lightBlue,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.green,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.grey,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.indigoAccent,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.deepOrange,
          ),
          Container(
            width: 100.0,
            height: 100.0,
            color: Colors.orangeAccent,
          ),
        ],
      ),
    );
  }
}
