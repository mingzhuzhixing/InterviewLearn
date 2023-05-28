import 'package:flutter/material.dart';

/*
 * 顶部tabbar
 */
class TopTabBarPage extends StatefulWidget {
  const TopTabBarPage({Key? key}) : super(key: key);

  @override
  _TopTabBarPageState createState() => _TopTabBarPageState();
}

class _TopTabBarPageState extends State<TopTabBarPage> {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        appBar: AppBar(
          title: const Text("顶部tabbar"),
          bottom: const TabBar(
            tabs: [
              Tab(
                text: "热门",
              ),
              Tab(
                text: "推荐",
              )
            ],
          ),
        ),
        body: TabBarView(
          children: <Widget>[
            ListView(
              children: const [
                ListTile(title: Text("第一个tab文本")),
                ListTile(title: Text("第一个tab文本")),
                ListTile(title: Text("第一个tab文本")),
                ListTile(title: Text("第一个tab文本")),
              ],
            ),
            ListView(
              children: const [
                ListTile(title: Text("第二个tab文本")),
                ListTile(title: Text("第二个tab文本")),
                ListTile(title: Text("第二个tab文本")),
                ListTile(title: Text("第二个tab文本")),
                ListTile(title: Text("第二个tab文本")),
                ListTile(title: Text("第二个tab文本")),
                ListTile(title: Text("第二个tab文本")),
              ],
            )
          ],
        ),
      ),
    );
  }
}
