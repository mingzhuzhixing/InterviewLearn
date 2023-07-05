import 'package:flutter/material.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/page/assemble_page/base_assembly_page.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

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
          ItemButton("CustomScrollViewPage2", CustomScrollViewSearchPage(), index: 1),
        ],
      ),
    );
  }
}

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

  @override
  void initState() {
    super.initState();
    for (int i = 0; i < 20; i++) {
      list.add("第一个--->$i");
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
          SliverList(
            delegate: SliverChildBuilderDelegate(childCount: list.length, (ctx, index) {
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
            }),
          ),
          // SliverToBoxAdapter(
          //   child: Container(
          //     width: 1.sw,
          //     decoration: BoxDecoration(
          //       borderRadius: BorderRadius.only(
          //         bottomRight: Radius.zero,
          //         bottomLeft: Radius.zero,
          //         topLeft: Radius.circular(20.w),
          //         topRight: Radius.circular(20.w),
          //       ),
          //       color: Colors.white,
          //     ),
          //     child: Column(
          //       crossAxisAlignment: CrossAxisAlignment.start,
          //       children: [
          //         Text("搜索历史"),
          //
          //         // Expanded(
          //         //   child: ListView(
          //         //     children: [
          //         //       itemWarp("第一个"),
          //         //       itemWarp("第二个二个二个按钮"),
          //         //       itemWarp("第三个"),
          //         //       itemWarp("第四个"),
          //         //       itemWarp("第五个第五个第"),
          //         //       itemWarp("第六个"),
          //         //       itemWarp("第七个第七个第七个第七个"),
          //         //     ],
          //         //     shrinkWrap: true,
          //         //     physics: NeverScrollableScrollPhysics(),
          //         //   ),
          //         // )
          //       ],
          //     ),
          //   ),
          // )
        ],
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
}
