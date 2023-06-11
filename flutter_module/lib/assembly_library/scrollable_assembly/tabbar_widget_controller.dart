import 'package:flutter/material.dart';
import 'package:flutter_module/widget/keep_alive_wrapper.dart';

/**
 * TabController 实现tab切换
 */
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
