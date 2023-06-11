import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * PageView
 *
 * PageView({
 *  Key? key,
 *  this.scrollDirection = Axis.horizontal, // 滑动方向
 *  this.reverse = false,
 *  PageController? controller,
 *  this.physics,
 *  List<Widget> children = const <Widget>[],
 *  this.onPageChanged,
 *
 *  //每次滑动是否强制切换整个页面，如果为false，则会根据实际的滑动距离显示页面
 *  this.pageSnapping = true,
 *  //主要是配合辅助功能用的，后面解释
 *  this.allowImplicitScrolling = false,
 *  //后面解释
 *  this.padEnds = true,
 * })
 */
class PageViewWidgetPage extends StatefulWidget {
  const PageViewWidgetPage({Key? key}) : super(key: key);

  @override
  State<PageViewWidgetPage> createState() => _PageViewWidgetPageState();
}

class _PageViewWidgetPageState extends State<PageViewWidgetPage> {
  @override
  Widget build(BuildContext context) {
    var children = <Widget>[];
    // 生成 6 个 Tab 页
    for (int i = 0; i < 6; ++i) {
      children.add(Page(text: '$i'));
    }

    return Scaffold(
      appBar: CommonAppBar(context, "PageView Widget"),
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
