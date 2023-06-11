import 'package:flutter/material.dart';

/**
 * CustomScrollView
 *
 * https://book.flutterchina.club/chapter6/custom_scrollview.html#_6-10-1-customscrollview
 *
 * Flutter 中常用的 Sliver:
 *  Sliver名称	                         功能	                                           对应的组件
 *  SliverList	                       列表	                                           ListView
 *  SliverFixedExtentList	             高度固定的列表	                                   ListView，指定itemExtent时
 *  SliverAnimatedList	               添加/删除列表项可以执行动画	                         AnimatedList
 *  SliverGrid	                       网格	                                           GridView
 *  SliverPrototypeExtentList	         根据原型生成高度固定的列表	                         ListView，指定prototypeItem 时
 *  SliverFillViewport	               包含多个子组件，每个都可以填满屏幕	                   PageView
 *  SliverPadding	                                                                     Padding
    SliverVisibility、SliverOpacity	                                                   Visibility、Opacity
    SliverFadeTransition	                                                             FadeTransition
    SliverLayoutBuilder	                                                               LayoutBuilder
    SliverAppBar	                      对应 AppBar，主要是为了在 CustomScrollView 中使用。
    SliverToBoxAdapter	                一个适配器，可以将 RenderBox 适配为 Sliver，后面介绍。
    SliverPersistentHeader	            滑动到顶部时可以固定住，后面介绍。
 */
class CustomScrollViewPage extends StatefulWidget {
  const CustomScrollViewPage({Key? key}) : super(key: key);

  @override
  State<CustomScrollViewPage> createState() => _CustomScrollViewPageState();
}

class _CustomScrollViewPageState extends State<CustomScrollViewPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // AppBar，包含一个导航栏
          SliverAppBar(
            pinned: true,
            expandedHeight: 200.0,
            flexibleSpace: FlexibleSpaceBar(
              title: Text('CustomScrollView Demo'),
              background: Image(
                image: NetworkImage(
                    'https://tva1.sinaimg.cn/large/006y8mN6gy1g72j6nk1d4j30u00k0n0j.jpg'),
                fit: BoxFit.cover,
              ),
            ),
          ),
          SliverGrid(
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                return Container(
                  alignment: Alignment.center,
                  color: Colors.teal[100 * (index % 9)],
                  child: Text('grid item $index'),
                );
              },
              childCount: 10,
            ),
            gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
              maxCrossAxisExtent: 200.0,
              mainAxisSpacing: 10.0,
              crossAxisSpacing: 10.0,
              childAspectRatio: 4.0,
            ),
          ),

          // SliverFixedExtentList 是一个 Sliver，它可以生成高度相同的列表项。
          // 再次提醒，如果列表项高度相同，我们应该优先使用SliverFixedExtentList
          // 和 SliverPrototypeExtentList，如果不同，使用 SliverList.
          SliverFixedExtentList(
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                return Container(
                  alignment: Alignment.center,
                  color: Colors.lightBlue[100 * (index % 9)],
                  child: Text('list item $index'),
                );
              },
              childCount: 20,
            ),
            itemExtent: 50.0,
          ),
        ],
      ),
    );
  }
}
