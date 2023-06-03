import 'package:flutter/material.dart';

/*
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

/*
 * 声明周期函数
 */
  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);

    _tabController.addListener(() {
      print("controller:${_tabController.index}");
    });
  }

/*
 * 声明周期函数
 */
  @override
  void dispose() {
    super.dispose();
    _tabController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("TabControllerPage"),
        bottom: TabBar(controller: _tabController, tabs: [
          Tab(
            text: "推荐",
          ),
          Tab(
            text: "热点",
          )
        ]),
      ),
      body: TabBarView(controller: _tabController, children: [
        Center(
          child: Text("推荐"),
        ),
        Center(
          child: Text("热点"),
        ),
      ]),
    );
  }
}
