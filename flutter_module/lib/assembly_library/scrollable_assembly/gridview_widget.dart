import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * GridView
 *
 * 网格布局是一种常见的布局类型，GridView 组件正是实现了网格布局的组件
 * https://book.flutterchina.club/chapter6/gridview.html#_6-6-1-%E9%BB%98%E8%AE%A4%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0
 *
 */
class GridViewWidgetPage extends StatefulWidget {
  const GridViewWidgetPage({Key? key}) : super(key: key);

  @override
  State<GridViewWidgetPage> createState() => _GridViewWidgetPageState();
}

class _GridViewWidgetPageState extends State<GridViewWidgetPage> {
  final ScrollController _controller = ScrollController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("GridView Widget"),
      ),
      body: SingleChildScrollView(
        controller: _controller,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            //1、SliverGridDelegateWithFixedCrossAxisCount 使用
            Text("SliverGridDelegateWithFixedCrossAxisCount 使用"),
            sliverGridDelegateWithFixedCrossAxisCount(),

            SizedBox(height: 20.w),

            Text("SliverGridDelegateWithMaxCrossAxisExtent 使用"),
            //2、SliverGridDelegateWithMaxCrossAxisExtent 使用
            sliverGridDelegateWithMaxCrossAxisExtent(),

            SizedBox(height: 20.w),

            Text("GridView.count 使用"),
            //3、GridView.count
            gridviewCount(),

            SizedBox(height: 20.w),

            Text("GridView.extent 使用"),
            //3、GridView.extent
            gridViewExtent(),
          ],
        ),
      ),
    );
  }

  // SliverGridDelegateWithFixedCrossAxisCount 使用
  Widget sliverGridDelegateWithFixedCrossAxisCount() {
    return GridView(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 3, //横轴三个子widget
        childAspectRatio: 1.0, //宽高比为1时，子widget
        mainAxisSpacing: 10.w, //item之间上下间距
        crossAxisSpacing: 20.w, //item之间左右间距
      ),
      children: <Widget>[
        _itemWidget(Icons.ac_unit),
        _itemWidget(Icons.airport_shuttle),
        _itemWidget(Icons.all_inclusive),
        _itemWidget(Icons.beach_access),
        _itemWidget(Icons.cake),
        _itemWidget(Icons.free_breakfast)
      ],
    );
  }

  // SliverGridDelegateWithMaxCrossAxisExtent 使用
  Widget sliverGridDelegateWithMaxCrossAxisExtent() {
    return GridView(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
        maxCrossAxisExtent: 120.0, // 计算的
        childAspectRatio: 2.0, //宽高比为2, //横轴三个子widget
        mainAxisSpacing: 10.w,
        crossAxisSpacing: 20.w,
      ),
      children: <Widget>[
        _itemWidget(Icons.ac_unit),
        _itemWidget(Icons.airport_shuttle),
        _itemWidget(Icons.all_inclusive),
        _itemWidget(Icons.beach_access),
        _itemWidget(Icons.cake),
        _itemWidget(Icons.free_breakfast)
      ],
    );
  }

  //GridView.count 使用
  Widget gridviewCount() {
    return GridView.count(
      physics: NeverScrollableScrollPhysics(),
      shrinkWrap: true,
      crossAxisCount: 3,
      childAspectRatio: 1.0,
      mainAxisSpacing: 10.w,
      crossAxisSpacing: 20.w,
      children: <Widget>[
        _itemWidget(Icons.ac_unit),
        _itemWidget(Icons.airport_shuttle),
        _itemWidget(Icons.all_inclusive),
        _itemWidget(Icons.beach_access),
        _itemWidget(Icons.cake),
        _itemWidget(Icons.free_breakfast),
      ],
    );
  }

  //GridView.extent 使用
  Widget gridViewExtent() {
    return GridView.extent(
      physics: NeverScrollableScrollPhysics(),
      shrinkWrap: true,
      maxCrossAxisExtent: 120.0,
      childAspectRatio: 2.0,
      mainAxisSpacing: 10.w,
      crossAxisSpacing: 20.w,
      children: <Widget>[
        _itemWidget(Icons.ac_unit),
        _itemWidget(Icons.airport_shuttle),
        _itemWidget(Icons.all_inclusive),
        _itemWidget(Icons.beach_access),
        _itemWidget(Icons.cake),
        _itemWidget(Icons.free_breakfast),
      ],
    );
  }

  Widget _itemWidget(IconData icon) {
    return Container(
      color: Colors.yellow,
      child: Icon(icon),
    );
  }
}
/**
 * 1、SliverGridDelegateWithFixedCrossAxisCount  该子类实现了一个横轴为固定数量子元素的layout算法
 *
 * SliverGridDelegateWithFixedCrossAxisCount({
 *   @required double crossAxisCount, 横轴子元素的数量。此属性值确定后子元素在横轴的长度就确定了，即ViewPort横轴长度除以crossAxisCount的商。
 *   double mainAxisSpacing = 0.0, 主轴方向的间距。
 *   double crossAxisSpacing = 0.0, 横轴方向子元素的间距。
 *   double childAspectRatio = 1.0, 子元素在横轴长度和主轴长度的比例。由于crossAxisCount指定后，子元素横轴长度就确定了，然后通过此参数值就可以确定子元素在主轴的长度。
 * })
 *
 * 2、SliverGridDelegateWithMaxCrossAxisExtent  该子类实现了一个横轴子元素为固定最大长度的layout算法
 * SliverGridDelegateWithMaxCrossAxisExtent({
 *   double maxCrossAxisExtent,
 *   double mainAxisSpacing = 0.0,
 *   double crossAxisSpacing = 0.0,
 *   double childAspectRatio = 1.0,
 * })
 * maxCrossAxisExtent为子元素在横轴上的最大长度，之所以是“最大”长度，是因为横轴方向每个子元素的长度仍然是等分的，
 * 举个例子，如果ViewPort的横轴长度是450，那么当maxCrossAxisExtent的值在区间[450/4，450/3)内的话，子元素最终实际长度都为112.5，
 * 而childAspectRatio所指的子元素横轴和主轴的长度比为最终的长度比。
 */
