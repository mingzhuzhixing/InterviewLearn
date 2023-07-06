import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_module/widget/keep_alive_wrapper.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * TabController 实现tab切换
 */
class TabBarViewHomePage extends StatelessWidget {
  const TabBarViewHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "TabBarView"),
      body: Column(
        children: [
          ItemButton("TabBarView Controller", TabBarControllerPage(), index: 0),
          ItemButton("自定义TabBarView中样式 Controller", TabBarView2WidgetPage(), index: 0),
        ],
      ),
    );
  }
}

class TabBarControllerPage extends StatefulWidget {
  const TabBarControllerPage({Key? key}) : super(key: key);

  @override
  _TabControllerPageState createState() => _TabControllerPageState();
}

class _TabControllerPageState extends State<TabBarControllerPage>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;
  List tabs = ["新闻", "历史", "图片"];

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: tabs.length, vsync: this);

    _tabController.addListener(() {
      print("controller:${_tabController.index}");
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("TabBar Controller"),
        bottom: TabBar(
          controller: _tabController,
          tabs: tabs.map((e) => Tab(text: e)).toList(),
        ),
      ),
      body: TabBarView(
        //构建
        controller: _tabController,
        children: tabs.map((e) {
          return KeepAliveWrapper(
            child: Container(
              alignment: Alignment.center,
              child: Text(e, textScaleFactor: 5),
            ),
          );
        }).toList(),
      ),
    );
  }

  @override
  void dispose() {
    // 释放资源
    _tabController.dispose();
    super.dispose();
  }
}

/**
 * 自定义 TabBarView 样式
 */
class TabBarView2WidgetPage extends StatefulWidget {
  const TabBarView2WidgetPage({Key? key}) : super(key: key);

  @override
  State<TabBarView2WidgetPage> createState() => _TabBarView2WidgetPageState();
}

class _TabBarView2WidgetPageState extends State<TabBarView2WidgetPage>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;
  List tabs = ["新闻", "历史", "图片"];

  int selectIndex = 0;

  var children = <Widget>[];

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: tabs.length, vsync: this);

    _tabController.addListener(() {
      print("controller:${_tabController.index}");
      setState(() {
        selectIndex = _tabController.index;
      });
    });

    // 生成 tabs.length 个 Tab 页
    for (int i = 0; i < tabs.length; ++i) {
      children.add(KeepAliveWrapper(child: Page(text: '${tabs[i]}')));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xffF5F3F0),
      appBar: CommonAppBar(context, "自定义 TabBarView 样式"),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            margin: EdgeInsets.only(top: 20.w),
            child: TabBar(
              controller: _tabController,
              //设置tabbar不平均排布
              isScrollable: true,
              //指示器的大小
              indicatorSize: TabBarIndicatorSize.label,
              //设置内边距 也就是可以改变其大小
              indicatorPadding: EdgeInsets.zero,
              labelPadding: EdgeInsets.only(left: 10),
              tabs: tabs.map((e) {
                return Container(
                  alignment: Alignment.center,
                  width: 124.w,
                  height: 66.w,
                  padding: EdgeInsets.only(bottom: 4.w),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8.w),
                    color: e == tabs[selectIndex] ? Color(0xffF2DDC5) : Colors.white,
                  ),
                  child: Text(
                    e,
                    style: TextStyle(
                        color: e == tabs[selectIndex] ? Color(0xff75624B) : Color(0xff292929)),
                  ),
                );
              }).toList(),
              indicatorColor: Colors.transparent,
            ),
          ),
          // Expanded(
          //   child: PageView(
          //     allowImplicitScrolling: true, //allowImplicitScrolling 为 true 时设置了预渲染区域，
          //     children: children,
          //   ),
          // ),
          Expanded(
            child: TabBarView(
              //构建
              controller: _tabController,
              children: tabs.map((e) {
                return KeepAliveWrapper(
                  child: Container(
                    margin: EdgeInsets.only(top: 20.w),
                    color: Color(0x4dB71C1C),
                    alignment: Alignment.center,
                    child: Text(e, textScaleFactor: 5),
                  ),
                );
              }).toList(),
            ),
          )
        ],
      ),
    );
  }

  void s() {}
}

// Tab 页面
class Page extends StatefulWidget {
  const Page({Key? key, required this.text}) : super(key: key);

  final String text;

  @override
  _PageState createState() => _PageState();
}

class _PageState extends State<Page> {
  @override
  Widget build(BuildContext context) {
    print("build ${widget.text}");
    return Container(
      margin: EdgeInsets.only(top: 20.w),
      color: Color(0x4dB71C1C),
      child: Text(widget.text, textScaleFactor: 5),
    );
  }
}
