import 'package:flutter/material.dart';
import 'package:flutter_module/widget/keep_alive_wrapper.dart';

/**
 * 顶部TabBar
 */
class TabBarWidgetDefaultPage extends StatefulWidget {
  const TabBarWidgetDefaultPage({Key? key}) : super(key: key);

  @override
  State<TabBarWidgetDefaultPage> createState() => _TabBarWidgetDefaultPageState();
}

class _TabBarWidgetDefaultPageState extends State<TabBarWidgetDefaultPage> {
  List tabs = ["新闻", "历史", "图片"];

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: tabs.length,
      child: Scaffold(
        appBar: AppBar(
          title: Text("DefaultTabController widget"),
          bottom: TabBar(
            tabs: tabs.map((e) => Tab(text: e)).toList(),
          ),
        ),
        body: TabBarView(
          //构建
          children: tabs.map((e) {
            return KeepAliveWrapper(
              child: Container(
                alignment: Alignment.center,
                child: Text(e, textScaleFactor: 5),
              ),
            );
          }).toList(),
        ),
      ),
    );
  }
}
