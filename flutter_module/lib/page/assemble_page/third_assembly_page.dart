import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/third_assembly/flutter_toast.dart';
import 'package:flutter_module/assembly_library/third_assembly/html.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';

class ThirdAssemblyPage extends StatefulWidget {
  const ThirdAssemblyPage({Key? key}) : super(key: key);

  @override
  State<ThirdAssemblyPage> createState() => _ThirdAssemblyPageState();
}

class _ThirdAssemblyPageState extends State<ThirdAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("html_widget", HtmlWidgetPage()),
    ItemButtonEntity("flutter_toast", FlutterToastPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "第三方组件"),
      body: ListView.separated(
        itemBuilder: (BuildContext context, int index) {
          return ItemButton(entityList[index].title, entityList[index].widget ?? Spacer(),
              type: entityList[index].type ?? "", index: index);
        },
        separatorBuilder: (BuildContext context, int index) {
          return const Divider(height: 0.0, color: Colors.transparent);
        },
        itemCount: entityList.length,
      ),
    );
  }
}
