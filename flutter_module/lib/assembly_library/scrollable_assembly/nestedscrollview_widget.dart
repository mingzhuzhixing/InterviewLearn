import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/customscrollview_vidget.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * 嵌套可滚动组件 NestedScrollView
 *
 * https://book.flutterchina.club/chapter6/nestedscrollview.html#_6-12-1-nestedscrollview
 * NestedScrollView({
 *   ... //省略可滚动组件的通用属性
 *   //header，sliver构造器
 *   required this.headerSliverBuilder,
 *   //可以接受任意的可滚动组件
 *   required this.body,
 *   this.floatHeaderSlivers = false,
 * })
 */
class NestedScrollViewHomePage extends StatelessWidget {
  const NestedScrollViewHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "NestedScrollView widget"),
      body: Column(
        children: [
          ItemButton("NestedScrollViewPage", NestedScrollViewWidgetPage(), index: 0),
          ItemButton("NestedScrollViewPage2", NestedScrollViewWidgetPage2(), index: 1),
          ItemButton("NestedTabBarViewPage", NestedTabBarViewPage(), index: 0),
        ],
      ),
    );
  }
}

/**
 * 基本用法
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

/**
 *
 */
class NestedScrollViewWidgetPage2 extends StatefulWidget {
  const NestedScrollViewWidgetPage2({Key? key}) : super(key: key);

  @override
  State<NestedScrollViewWidgetPage2> createState() => _NestedScrollViewWidgetPage2State();
}

class _NestedScrollViewWidgetPage2State extends State<NestedScrollViewWidgetPage2> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "NestedScrollView Widget"),
      body: NestedScrollView(
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
          return <Widget>[
            // 此子view都是sliver
            SliverToBoxAdapter(
              child: Column(
                children: [
                  Container(
                    alignment: Alignment.center,
                    height: 100.w,
                    width: 1.sw,
                    child: Text("data"),
                    color: Colors.red,
                  ),
                  Container(
                    alignment: Alignment.center,
                    height: 100.w,
                    width: 1.sw,
                    child: Text("data"),
                    color: Colors.blue,
                  )
                ],
              ),
            )
          ];
        },
        body: ListView.builder(
          padding: EdgeInsets.all(8),
          physics: ClampingScrollPhysics(), //重要
          // physics: NeverScrollableScrollPhysics(), //重要
          itemCount: 30,
          itemBuilder: (BuildContext context, int index) {
            return SizedBox(
              height: 50,
              child: Center(child: Text('Item $index')),
            );
          },
        ),
      ),
    );
  }
}

/**
 * 嵌套 TabBarView
 * 用户向上滑动时 导航栏能够滑出屏幕，当用户向下滑动时，导航栏能迅速回到屏幕，因为向下滑动时可能是用户想看之前的商品，也可能是用户向找到导航栏返回
 */
class NestedTabBarViewPage extends StatelessWidget {
  const NestedTabBarViewPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final _tabs = <String>['猜你喜欢', '今日特价', '发现更多'];
    // 构建 tabBar
    return DefaultTabController(
      length: _tabs.length, // tab的数量.
      child: Scaffold(
        body: NestedScrollView(
          headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
            return <Widget>[
              SliverOverlapAbsorber(
                handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
                sliver: SliverAppBar(
                  title: const Text('商城'),
                  floating: true,
                  snap: true,
                  forceElevated: innerBoxIsScrolled,
                  bottom: TabBar(
                    tabs: _tabs.map((String name) => Tab(text: name)).toList(),
                  ),
                ),
              ),
            ];
          },
          body: TabBarView(
            children: _tabs.map((String name) {
              return Builder(
                builder: (BuildContext context) {
                  return CustomScrollView(
                    key: PageStorageKey<String>(name),
                    slivers: <Widget>[
                      SliverOverlapInjector(
                        handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
                      ),
                      SliverPadding(
                        padding: const EdgeInsets.all(8.0),
                        sliver: buildSliverList(50),
                      ),
                    ],
                  );
                },
              );
            }).toList(),
          ),
        ),
      ),
    );
  }
}

