import 'dart:ffi';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/page/about_page.dart';
import 'package:flutter_module/page/confine_page.dart';
import 'package:flutter_module/page/tab_page/discover_page.dart';
import 'package:flutter_module/page/tab_page/news_page.dart';
import 'package:flutter_module/page/tab_page/profile_page.dart';
import 'package:flutter_module/page/publish_trends_page.dart';
import 'package:flutter_module/page/setting_page.dart';
import 'package:flutter_module/page/tab_page/tweet_page.dart';
import 'package:flutter_module/widget/bottom_item.dart';
import 'package:flutter_module/widget/left_drawer.dart';

class OsChinaBody extends StatefulWidget {
  const OsChinaBody({Key? key}) : super(key: key);

  @override
  State<OsChinaBody> createState() => _OsChinaBodyState();
}

class _OsChinaBodyState extends State<OsChinaBody> {
  //当前页面
  var _current = 0;

  late List _listTitle;

  //底部按钮
  late List<BottomItem> _listBottom;

  //展示页面
  late List<Widget> _listPage;

  late PageController _pageController;

  @override
  void initState() {
    super.initState();
    _listTitle = ['资讯', '动弹', '发现', '我的'];
    _current = 0;
    _pageController = PageController(initialPage: _current);
    _listPage = [const NewsPage(), const TweetPage(), const DiscoverPage(), const ProfilePage()];
    _listBottom = [
      BottomItem(
          iconPath: 'assets/images/ic_nav_news_normal.png',
          activatePath: 'assets/images/ic_nav_news_actived.png',
          title: '资讯'),
      BottomItem(
          iconPath: 'assets/images/ic_nav_tweet_normal.png',
          activatePath: 'assets/images/ic_nav_tweet_actived.png',
          title: '动弹'),
      BottomItem(
          iconPath: 'assets/images/ic_nav_discover_normal.png',
          activatePath: 'assets/images/ic_nav_discover_actived.png',
          title: '发现'),
      BottomItem(
          iconPath: 'assets/images/ic_nav_my_normal.png',
          activatePath: 'assets/images/ic_nav_my_pressed.png',
          title: '我的'),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: PreferredSize(
      //   child: Offstage(
      //     offstage: false,
      //     child: AppBar(
      //       title: Text("${_listTitle[_current]}"),
      //       //自定义菜单图标
      //       leading: Builder(
      //         builder: (context) {
      //           return IconButton(
      //               onPressed: () {
      //                 _openDrawer(context);
      //               },
      //               icon: const Icon(
      //                 Icons.menu,
      //                 color: Colors.white,
      //               ));
      //         },
      //       ),
      //     ),
      //   ),
      //   preferredSize:Size.fromHeight(MediaQuery.of(context).size.height * 0.07),
      // ),
      body: PageView.builder(
        itemBuilder: (context, index) {
          return _listPage[index];
        },
        itemCount: _listPage.length,
        controller: _pageController,
        //禁止页面滑动
        physics: const NeverScrollableScrollPhysics(),
      ),
      drawer: LeftDrawer(listTitle: const [
        '发布动态',
        '动弹小黑屋',
        '关于',
        '设置'
      ], listIcon: const [
        Icons.border_horizontal_rounded,
        Icons.safety_divider,
        Icons.account_balance_outlined,
        Icons.settings,
      ], listPage: const [
        PublishTrendsPage(),
        ConfinePage(),
        AboutPage(),
        SettingPage()
      ], imgPath: 'assets/images/cover_img.jpg'),
      bottomNavigationBar: BottomNavigationBar(
        items: _listBottom.map((e) => e.item).toList(),
        selectedItemColor: const Color(0xFF24CF5F),
        unselectedItemColor: Colors.grey,
        currentIndex: _current,
        onTap: (index) {
          setState(() {
            _current = index;
            _pageController.animateToPage(_current,
                duration: const Duration(milliseconds: 1), curve: Curves.easeIn);
          });
        },
        type: BottomNavigationBarType.fixed,
      ),
    );
  }

  ///打开左边的抽屉
  _openDrawer(context) {
    Scaffold.of(context).openDrawer();
  }
}
