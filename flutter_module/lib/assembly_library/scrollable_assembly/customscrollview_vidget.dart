import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';

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
    SliverFillRemaining                 组件充满视口剩余空间                               Expanded
 */
class CustomScrollViewHomePage extends StatefulWidget {
  const CustomScrollViewHomePage({Key? key}) : super(key: key);

  @override
  State<CustomScrollViewHomePage> createState() => _CustomScrollViewHomePageState();
}

class _CustomScrollViewHomePageState extends State<CustomScrollViewHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "CustomScrollView Widget"),
      body: Column(
        children: [
          ItemButton("CustomScrollViewPage", CustomScrollViewPage(), index: 0),
          ItemButton("CustomScrollViewPage2", CustomScrollViewSearchPage(), index: 0),
          ItemButton("SliverPersistentHeader", SliverPersistentHeaderPage(), index: 0),
          ItemButton("书城", BookCityPage(), index: 0),
        ],
      ),
    );
  }
}

/**
 * 自定义滑动scrollview
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

/**
 * CustomScrollView 实现搜索页
 */
class CustomScrollViewSearchPage extends StatefulWidget {
  const CustomScrollViewSearchPage({Key? key}) : super(key: key);

  @override
  State<CustomScrollViewSearchPage> createState() => _CustomScrollViewSearchPageState();
}

class _CustomScrollViewSearchPageState extends State<CustomScrollViewSearchPage> {
  List<String> list = [];
  List<Widget> childrens = [];

  @override
  void initState() {
    super.initState();
    for (int i = 0; i < 15; i++) {
      list.add("第一个--->$i");
      childrens.add(_getBottomItem());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xfff7f7f7),
      appBar: CommonAppBar(context, "CustomScrollView 实现搜索页"),
      body: CustomScrollView(
        slivers: [
          SliverToBoxAdapter(
            child: Container(
              padding: EdgeInsets.only(left: 30.w, right: 30.w),
              width: 1.sw,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.only(
                  topLeft: Radius.zero,
                  topRight: Radius.zero,
                  bottomLeft: Radius.circular(20.w),
                  bottomRight: Radius.circular(20.w),
                ),
                color: Colors.white,
              ),
              child: Column(
                children: [
                  SizedBox(height: 30.w),
                  Row(
                    children: [
                      Text(
                        "搜索历史",
                        style: TextStyle(fontSize: 34.sp, fontWeight: FontWeight.w600),
                      ),
                      Spacer(),
                      Icon(Icons.delete)
                    ],
                  ),
                  SizedBox(height: 30.w),
                  Wrap(
                    runSpacing: 10.0,
                    spacing: 10.0,
                    children: [
                      itemWarp("第一个"),
                      itemWarp("第二个二个二个按钮"),
                      itemWarp("第三个"),
                      itemWarp("第四个"),
                      itemWarp("第五个第五个第"),
                      itemWarp("第六个"),
                      itemWarp("第七个第七个第七个第七个"),
                    ],
                  ),
                  SizedBox(height: 30.w),
                ],
              ),
            ),
          ),
          SliverPadding(
            padding: EdgeInsets.only(top: 20.w),
            sliver: SliverToBoxAdapter(
              child: Container(
                width: 1.sw,
                padding: EdgeInsets.only(left: 20, top: 20.w, bottom: 30.w),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.only(
                    bottomRight: Radius.zero,
                    bottomLeft: Radius.zero,
                    topLeft: Radius.circular(20.w),
                    topRight: Radius.circular(20.w),
                  ),
                  color: Colors.white,
                ),
                child: Text(
                  "搜索历史",
                  style: TextStyle(fontSize: 34.sp, fontWeight: FontWeight.w600),
                ),
              ),
            ),
          ),
          SliverFillRemaining(
            hasScrollBody: false,
            child: Container(
              color: Colors.white,
              child: Column(
                children: childrens,
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget getSliverList() {
    return SliverList(
      delegate: SliverChildBuilderDelegate(
        (ctx, int index) {
          return _getBottomItem();
        },
        childCount: list.length,
      ),
    );
  }

  Widget itemWarp(String text) {
    return Stack(
      alignment: Alignment(1.03, -1.3),
      children: [
        Container(
          padding: EdgeInsets.only(left: 30.w, right: 30.w, top: 12.w, bottom: 12.w),
          child: Text(text),
          decoration: BoxDecoration(
            color: Color(0xffF5F3F0),
            borderRadius: BorderRadius.circular(16.w),
          ),
        ),
        Positioned(
          child: Container(
            width: 26.w,
            height: 26.w,
            decoration: BoxDecoration(
              color: Color(0xffDBDAD7),
              borderRadius: BorderRadius.circular(16.w),
              border: Border.all(color: Colors.white, width: 2.w),
            ),
            child: Icon(Icons.close, color: Colors.white, size: 24.w),
          ),
        )
      ],
    );
  }

  Widget _getBottomItem() {
    return Container(
      color: Colors.white,
      padding: EdgeInsets.only(left: 30.w, bottom: 20.w, right: 30.w),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          ClipRRect(
            child: Image.asset(
              "assets/images/icon_cover.png",
              width: 148.w,
              height: 208.w,
              fit: BoxFit.cover,
            ),
            borderRadius: BorderRadius.circular(8.w),
          ),
          SizedBox(width: 20.w),
          Expanded(
            child: SizedBox(
              height: 208.w,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "打造超级人脉",
                    style: TextStyle(
                      fontSize: 30.sp,
                      fontWeight: FontWeight.w400,
                      color: Color(0xff1F1F1F),
                    ),
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                  SizedBox(height: 20.w),
                  Text(
                    "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和未来。",
                    style: TextStyle(
                      fontSize: 26.sp,
                      fontWeight: FontWeight.w400,
                      color: Color(0xff969696),
                    ),
                    maxLines: 2,
                    overflow: TextOverflow.ellipsis,
                  ),
                  Spacer(),
                  Text(
                    "作者：贾斯汀比伯",
                    style: TextStyle(
                      fontSize: 24.sp,
                      fontWeight: FontWeight.w400,
                      color: Color(0xffb3b3b3),
                    ),
                    maxLines: 2,
                    overflow: TextOverflow.ellipsis,
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}

/**
 * SliverPersistentHeader
 * 功能是当滑动到 CustomScrollView 的顶部时，可以将组件固定在顶部。
 * SliverPersistentHeader({
 *   Key? key,
 *  required SliverPersistentHeaderDelegate delegate, // 构造 header 组件的委托
 *  this.pinned = false, // header 滑动到可视区域顶部时是否固定在顶部
 *  this.floating = false, // 正文部分介绍
 *  })
 */
class SliverPersistentHeaderPage extends StatefulWidget {
  const SliverPersistentHeaderPage({Key? key}) : super(key: key);

  @override
  State<SliverPersistentHeaderPage> createState() => _SliverPersistentHeaderPageState();
}

class _SliverPersistentHeaderPageState extends State<SliverPersistentHeaderPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "SliverPersistentHeader Widget"),
      body: CustomScrollView(
        slivers: [
          buildSliverList(),
          SliverPersistentHeader(
            pinned: true,
            delegate: SliverHeaderDelegate(
              //有最大和最小高度
              maxHeight: 80,
              minHeight: 50,
              child: buildHeader(1),
            ),
          ),
          buildSliverList(),
          SliverPersistentHeader(
            pinned: true,
            delegate: SliverHeaderDelegate.fixedHeight(
              //固定高度
              height: 50,
              child: buildHeader(2),
            ),
          ),
          buildSliverList(20),
        ],
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

  // 构建 header
  Widget buildHeader(int i) {
    return Container(
      color: Colors.lightBlue.shade200,
      alignment: Alignment.centerLeft,
      child: Text("PersistentHeader $i"),
    );
  }
}

typedef SliverHeaderBuilder = Widget Function(
    BuildContext context, double shrinkOffset, bool overlapsContent);

/**
 *abstract class SliverPersistentHeaderDelegate {
 *  // header 最大高度；pined为 true 时，当 header 刚刚固定到顶部时高度为最大高度。
 *  double get maxExtent;
 *
 *  // header 的最小高度；pined为true时，当header固定到顶部，用户继续往上滑动时，header
 *  // 的高度会随着用户继续上滑从 maxExtent 逐渐减小到 minExtent
 *  double get minExtent;
 *
 *  // 构建 header。
 *  // shrinkOffset取值范围[0,maxExtent],当header刚刚到达顶部时，shrinkOffset 值为0，
 *  // 如果用户继续向上滑动列表，shrinkOffset的值会随着用户滑动的偏移减小，直到减到0时。
 *  // overlapsContent：一般不建议使用，在使用时一定要小心，后面会解释。
 *  Widget build(BuildContext context, double shrinkOffset, bool overlapsContent);
 *
 *  // header 是否需要重新构建；通常当父级的 StatefulWidget 更新状态时会触发。
 *  // 一般来说只有当 Delegate 的配置发生变化时，应该返回false，比如新旧的 minExtent、maxExtent
 *  // 等其他配置不同时需要返回 true，其余情况返回 false 即可。
 *  bool shouldRebuild(covariant SliverPersistentHeaderDelegate oldDelegate);
 *
 *  // 下面这几个属性是SliverPersistentHeader在SliverAppBar中时实现floating、snap
 *  // 效果时会用到，平时开发过程很少使用到，读者可以先不用理会。
 *  TickerProvider? get vsync => null;
 *  FloatingHeaderSnapConfiguration? get snapConfiguration => null;
 *  OverScrollHeaderStretchConfiguration? get stretchConfiguration => null;
 *  PersistentHeaderShowOnScreenConfiguration? get showOnScreenConfiguration => null;
 *}
 */
class SliverHeaderDelegate extends SliverPersistentHeaderDelegate {
  final double maxHeight;
  final double minHeight;
  final SliverHeaderBuilder builder;

  // child 为 header
  SliverHeaderDelegate({
    required this.maxHeight,
    this.minHeight = 0,
    required Widget child,
  })  : builder = ((a, b, c) => child),
        assert(minHeight <= maxHeight && minHeight >= 0);

  @override
  Widget build(BuildContext context, double shrinkOffset, bool overlapsContent) {
    Widget child = builder(context, shrinkOffset, overlapsContent);
    //测试代码：如果在调试模式，且子组件设置了key，则打印日志
    assert(() {
      if (child.key != null) {
        print('${child.key}: shrink: $shrinkOffset，overlaps:$overlapsContent');
      }
      return true;
    }());
    // 让 header 尽可能充满限制的空间；宽度为 Viewport 宽度，
    // 高度随着用户滑动在[minHeight,maxHeight]之间变化。
    return SizedBox.expand(child: child);
  }

  //最大和最小高度相同
  SliverHeaderDelegate.fixedHeight({
    required double height,
    required Widget child,
  })  : builder = ((a, b, c) => child),
        maxHeight = height,
        minHeight = height;

  //需要自定义builder时使用
  SliverHeaderDelegate.builder({
    required this.maxHeight,
    this.minHeight = 0,
    required this.builder,
  });

  @override
  double get maxExtent => maxHeight;

  @override
  double get minExtent => minHeight;

  @override
  bool shouldRebuild(covariant SliverPersistentHeaderDelegate oldDelegate) {
    return oldDelegate.maxExtent != maxExtent || oldDelegate.minExtent != minExtent;
  }
}

/**
 * 书城
 */
class BookCityPage extends StatefulWidget {
  const BookCityPage({Key? key}) : super(key: key);

  @override
  State<BookCityPage> createState() => _BookCityPageState();
}

class _BookCityPageState extends State<BookCityPage> {
  late ScrollController scrollController;
  double paddingLeft = 15;
  double titleOpacity = 1;

  @override
  void initState() {
    super.initState();
    //0-76
    scrollController = ScrollController();
    scrollController.addListener(() {
      print("zm1234 监听到滚动...${scrollController.offset}");
      double offset = scrollController.offset;
      if (offset <= 42) {
        setState(() {
          paddingLeft = (offset / 42.0) * 30 + 15;
          titleOpacity = 1 - (offset / 42.0);
        });
      } else if (offset <= 0) {
        paddingLeft = 15;
        paddingLeft = 1.0;
      } else if (offset > 42) {
        setState(() {
          paddingLeft = 45;
          titleOpacity = 0;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: CustomScrollView(
        controller: scrollController,
        slivers: [
          SliverAppBar(
            leading: Icon(
              Icons.arrow_back_ios_new,
              color: Colors.black,
            ),
            backgroundColor: Color(0xffF5F3F0),
            pinned: true,
            title: Opacity(
              opacity: titleOpacity,
              child: Text(
                "书城",
                style: TextStyle(color: Color(0xff111111), fontSize: 34.sp),
              ),
            ),
            centerTitle: true,
            // 滑动到顶端时会固定住
            expandedHeight: 208.w,
            flexibleSpace: FlexibleSpaceBar(
              title: getSearch(),
              expandedTitleScale: 1,
              titlePadding: EdgeInsets.only(left: paddingLeft, right: 15, bottom: 26.w),
            ),
          ),
          SliverFixedExtentList(
            itemExtent: 50.0,
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                //创建列表项
                return Container(
                  alignment: Alignment.center,
                  color: Colors.lightBlue[100 * (index % 9)],
                  child: Text('list item $index'),
                );
              },
              childCount: 20,
            ),
          ),
        ],
      ),
    );
  }

  Widget getSearch() {
    return Container(
      ///控制高度
      height: 68.w,
      width: 1.sw,
      child: TextField(
        onChanged: (value) {
          setState(() {});
        },

        ///输入内容文字样式, 设置该样式会和hintStyle样式相同，缩有对应不用的要设置
        style: TextStyle(
          color: Color(0xff75624B),
          fontWeight: FontWeight.w500,
          fontSize: 26.sp,
        ),

        ///输入框样式
        decoration: InputDecoration(
          ///提示文字
          hintText: "搜索你想读的书",
          hintStyle: TextStyle(
            fontSize: 26.sp,
            color: Color(0xff75624B),
            fontWeight: FontWeight.w400,
          ),

          ///去掉输入框边框，设置角度为80，
          border: OutlineInputBorder(
            borderSide: BorderSide.none,
            borderRadius: BorderRadius.circular(80.w),
          ),

          ///设置背景色，可以修改背景色的只有fillColor这个属性。但是只设置fillColor属性是不行的，还必须同时设置filled为 true
          filled: true,
          fillColor: const Color(0xffffffff),

          /// 输入框decoration属性
          //contentPadding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 10.0),
          contentPadding: EdgeInsets.only(left: 0, top: 10, right: 0, bottom: 9),

          /// 让文字垂直居中
          isCollapsed: true,

          ///输入框左边图片,只有设置了prefixIconConstraints后，width和height才生效
          prefixIcon: GestureDetector(
            onTap: () {
              Fluttertoast.showToast(msg: "点击了左边搜索图标");
            },
            child: Padding(
              padding: EdgeInsets.only(left: 20.w, right: 10.w),
              child: Image.asset(
                "assets/images/icon_search.png",
                width: 38.w,
                height: 38.w,
              ),
            ),
          ),
          prefixIconConstraints: BoxConstraints(),
        ),
      ),
    );
  }
}
