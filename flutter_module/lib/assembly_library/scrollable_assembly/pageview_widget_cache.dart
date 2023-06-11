import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/keep_alive_wrapper.dart';

/**
 * PageView
 *
 * 定义缓存
 */
class PageViewWidgetCachePage extends StatefulWidget {
  const PageViewWidgetCachePage({Key? key}) : super(key: key);

  @override
  State<PageViewWidgetCachePage> createState() => _PageViewWidgetCachePageState();
}

class _PageViewWidgetCachePageState extends State<PageViewWidgetCachePage> {
  @override
  Widget build(BuildContext context) {
    var children = <Widget>[];
    for (int i = 0; i < 6; ++i) {
      //只需要用 KeepAliveWrapper 包装一下即可
      children.add(KeepAliveWrapper(child: Page(text: '$i')));
    }
    return Scaffold(
      appBar: CommonAppBar(context, "PageView Cache Widget"),
      body: PageView(
        // scrollDirection: Axis.vertical, // 滑动方向为垂直方向
        allowImplicitScrolling: true, //allowImplicitScrolling 为 true 时设置了预渲染区域，
        // cacheExtent 为 1.0，则代表前后各缓存一个页面宽度，即前后各一页
        children: children,
      ),
    );
  }
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
    return Center(child: Text(widget.text, textScaleFactor: 5));
  }
}
