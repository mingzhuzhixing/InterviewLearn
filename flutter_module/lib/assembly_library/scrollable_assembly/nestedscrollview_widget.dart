import 'package:flutter/material.dart';

/**
 * 嵌套可滚动组件 NestedScrollView
 *
 * https://book.flutterchina.club/chapter6/nestedscrollview.html#_6-12-1-nestedscrollview
 */
class NestedScrollViewWidgetPage extends StatefulWidget {
  const NestedScrollViewWidgetPage({Key? key}) : super(key: key);

  @override
  State<NestedScrollViewWidgetPage> createState() => _NestedScrollViewWidgetPageState();
}

class _NestedScrollViewWidgetPageState extends State<NestedScrollViewWidgetPage> {

  @override
  Widget build(BuildContext context) {
    return Material(

      child: NestedScrollView(
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
          // 返回一个 Sliver 数组给外部可滚动组件。
          return <Widget>[
            // SliverAppBar(
            //   title: Text('NestedScrollView widget'),
            //   pinned: true, // 固定在顶部
            //   forceElevated: innerBoxIsScrolled,
            // ),
            // buildSliverList(5), //构建一个 sliverList

            // 实现 snap 效果
            // SliverAppBar(
            //   floating: true,
            //   snap: true,
            //   //pinned: true, // appbar固定在顶部
            //   expandedHeight: 200,
            //   forceElevated: innerBoxIsScrolled,
            //   flexibleSpace: FlexibleSpaceBar(
            //     background: Image.asset(
            //       "assets/images/cover_img.jpg",
            //       fit: BoxFit.cover,
            //     ),
            //   ),
            // ),

            SliverOverlapAbsorber(
              handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
              sliver: SliverAppBar(
                floating: true,
                snap: true,
                expandedHeight: 200,
                flexibleSpace: FlexibleSpaceBar(
                  background: Image.asset(
                    "assets/images/cover_img.jpg",
                    fit: BoxFit.cover,
                  ),
                ),
                forceElevated: innerBoxIsScrolled,
              ),
            ),
          ];
        },
        // body: ListView.builder(
        //   padding: EdgeInsets.all(8),
        //   physics: ClampingScrollPhysics(), //重要
        //   itemCount: 30,
        //   itemBuilder: (BuildContext context, int index) {
        //     return SizedBox(
        //       height: 50,
        //       child: Center(child: Text('Item $index')),
        //     );
        //   },
        // ),
        body: Builder(builder: (BuildContext context) {
          return CustomScrollView(
            slivers: <Widget>[
              SliverOverlapInjector(
                handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
              ),
              buildSliverList(100)
            ],
          );
        }),
      ),
    );
  }

  // 构建固定高度的SliverList，count为列表项属相
  Widget buildSliverList([int count = 5]) {
    return SliverFixedExtentList(
      itemExtent: 50,
      delegate: SliverChildBuilderDelegate(
            (context, index) {
          return ListTile(title: Text('$index'));
        },
        childCount: count,
      ),
    );
  }
}

