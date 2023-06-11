import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * GridView.build
 *
 * 网格布局是一种常见的布局类型，GridView 组件正是实现了网格布局的组件
 * https://book.flutterchina.club/chapter6/gridview.html#_6-6-1-%E9%BB%98%E8%AE%A4%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0
 *
 */
class GridViewWidgetBuildPage extends StatefulWidget {
  const GridViewWidgetBuildPage({Key? key}) : super(key: key);

  @override
  State<GridViewWidgetBuildPage> createState() => _GridViewWidgetBuildPageState();
}

class _GridViewWidgetBuildPageState extends State<GridViewWidgetBuildPage> {
  final List<Widget> _icons = []; //保存Icon数据

  @override
  void initState() {
    super.initState();
    // 初始化数据
    _retrieveIcons();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("GridView Widget build"),
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 3, //每行三列
          childAspectRatio: 1.0, //显示区域宽高相等
          crossAxisSpacing: 10.w,
          mainAxisSpacing: 10.w,
        ),
        itemCount: _icons.length,
        itemBuilder: (context, index) {
          //如果显示到最后一个并且Icon总数小于200时继续获取数据
          if (index == _icons.length - 1 && _icons.length < 200) {
            _retrieveIcons();
          }
          return _icons[index];
        },
      ),
    );
  }

  //模拟异步获取数据
  void _retrieveIcons() {
    Future.delayed(Duration(milliseconds: 200)).then((e) {
      setState(() {
        _icons.addAll([
          _itemWidget(Icons.ac_unit),
          _itemWidget(Icons.airport_shuttle),
          _itemWidget(Icons.all_inclusive),
          _itemWidget(Icons.beach_access),
          _itemWidget(Icons.cake),
          _itemWidget(Icons.free_breakfast),
        ]);
      });
    });
  }

  Widget _itemWidget(IconData icon) {
    return Container(
      color: Colors.yellow,
      child: Icon(icon),
    );
  }
}
