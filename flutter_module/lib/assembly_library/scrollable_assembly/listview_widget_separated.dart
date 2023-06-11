import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/no_scroll_behavior.dart';

/**
 * ListView.separated 带分割线列表
 *
 * ListView.separated可以在生成的列表项之间添加一个分割组件，它比ListView.builder多了一个separatorBuilder参数，该参数是一个分割组件生成器。
 */
class ListViewWidgetSeparatedPage extends StatefulWidget {
  const ListViewWidgetSeparatedPage({Key? key}) : super(key: key);

  @override
  State<ListViewWidgetSeparatedPage> createState() => _ListViewWidgetSeparatedPageState();
}

class _ListViewWidgetSeparatedPageState extends State<ListViewWidgetSeparatedPage> {
  //下划线widget预定义以供复用。
  Widget divider1 = Divider(color: Colors.blue);
  Widget divider2 = Divider(color: Colors.green);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "ListView.separated widget"),
      body: ScrollConfiguration(
        behavior: NoShadowScrollBehavior(),
        child: ListView.separated(
          itemCount: 100,
          //列表项构造器
          itemBuilder: (BuildContext context, int index) {
            return ListTile(title: Text("$index"));
          },
          //分割器构造器
          separatorBuilder: (BuildContext context, int index) {
            return index % 2 == 0 ? divider1 : divider2;
          },
        ),
      ),
    );
  }
}
