import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_shop/pages/cart_page.dart';
import 'package:flutter_shop/pages/category_page.dart';
import 'package:flutter_shop/pages/home_page.dart';
import 'package:flutter_shop/pages/member_page.dart';

class IndexPage extends StatefulWidget {
  const IndexPage({Key? key}) : super(key: key);

  @override
  State<IndexPage> createState() => _IndexPageState();
}

class _IndexPageState extends State<IndexPage> {

  final List<BottomNavigationBarItem> bottomTabs=[
    const BottomNavigationBarItem(
      icon: Icon( CupertinoIcons.home),
      title: Text("首页"),
    ),
    const BottomNavigationBarItem(
      icon: Icon( CupertinoIcons.search),
      title: Text("分类"),
    ),
    const BottomNavigationBarItem(
      icon: Icon( CupertinoIcons.shopping_cart),
      title: Text("购物车"),
    ),
    const BottomNavigationBarItem(
      icon: Icon( CupertinoIcons.profile_circled),
      title: Text("会员中心"),
    )
  ];

  final List tabBodies=[
    HomePage(),
    CategoryPage(),
    CartPage(),
    MemberPage()
  ];

  int cuttentIndex=0;//当前选择的索引
  var currentPage;

  @override
  void initState() {
    currentPage= tabBodies[0];
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromRGBO(244, 245, 245, 1.0),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        currentIndex: cuttentIndex,
        items:bottomTabs,
        onTap: (index) {
          setState(() {
            cuttentIndex = index;
            currentPage = tabBodies[cuttentIndex];
          });
        },),
        body: currentPage,
    );
  }
}
