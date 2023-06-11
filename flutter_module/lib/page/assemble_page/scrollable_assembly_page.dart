import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/animatedlist_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/customscrollview_vidget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/gridview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/gridview_widget_build.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget_build.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget_separated.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/nestedscrollview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/pageview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/pageview_widget_cache.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/pull_to_refresh_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/singlechildscrollview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/tabbar_widget_controller.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/tabbar_widget_default.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/webview_widget.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_module/bean/item_entity.dart';

/**
 * 滚动组件
 */
class ScrollableAssemblyPage extends StatefulWidget {
  const ScrollableAssemblyPage({Key? key}) : super(key: key);

  @override
  State<ScrollableAssemblyPage> createState() => _ScrollableAssemblyPageState();
}

class _ScrollableAssemblyPageState extends State<ScrollableAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];
  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("AnimatedList_widget", AnimatedListWidgetPage()),
    ItemButtonEntity("CustomScrollView_widget", CustomScrollViewPage()),
    ItemButtonEntity("GridView_widget", GridViewWidgetPage()),
    ItemButtonEntity("GridView_widget_build", GridViewWidgetBuildPage()),
    ItemButtonEntity("ListView_widget", ListViewWidgetPage()),
    ItemButtonEntity("ListView_widget_build", ListViewWidgetBuildPage()),
    ItemButtonEntity("ListView_widget_separated", ListViewWidgetSeparatedPage()),
    ItemButtonEntity("NestedScrollView_widget", NestedScrollViewWidgetPage()),
    ItemButtonEntity("PageView_widget", PageViewWidgetPage()),
    ItemButtonEntity("PageView_widget_cache", PageViewWidgetCachePage()),
    ItemButtonEntity("SmartRefresher_widget", PullToRefreshPage()),
    ItemButtonEntity("SingleChildScrollView_widget", SingleChildScrollViewWidgetPage()),
    ItemButtonEntity("TabBar_widget_controller", TabBarControllerPage()),
    ItemButtonEntity("TabBar_widget_default", TabBarWidgetDefaultPage()),
    ItemButtonEntity("Webview_widget", WebviewWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "滚动组件"),
      body: ListView.separated(
          itemBuilder: (BuildContext context, int index) {
            return ItemButton(entityList[index].title, entityList[index].widget ?? Spacer(),
                type: entityList[index].type ?? "", index: index);
          },
          separatorBuilder: (BuildContext context, int index) {
            return const Divider(height: 0.0, color: Colors.transparent);
          },
          itemCount: entityList.length),
    );
  }
}
