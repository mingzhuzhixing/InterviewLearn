import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/customscrollview_vidget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/gridview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget_build.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/pull_to_refresh_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/webview_widget.dart';
import 'package:flutter_module/utils/appbar_utils.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
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
    ItemButtonEntity("customscrollview_vidget", CustomScrollViewPage()),
    ItemButtonEntity("pull_to_refresh", PullToRefreshPage()),
    ItemButtonEntity("gridview_widget", GridViewWidgetPage()),
    ItemButtonEntity("listview_widget", ListViewWidgetPage()),
    ItemButtonEntity("listview_widget_build", ListViewWidgetBuildPage()),
    ItemButtonEntity("webview_widget", WebviewWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "滚动组件"),
      body: ListView.separated(
          itemBuilder: (BuildContext context, int index) {
              return ItemButton(
                entityList[index].title, entityList[index].widget ?? Spacer(),
                type: entityList[index].type ?? "", index: index);
          },
          separatorBuilder: (BuildContext context, int index) {
            return const Divider(height: 0.0, color: Colors.transparent);
          },
          itemCount: entityList.length),
    );
  }
}
