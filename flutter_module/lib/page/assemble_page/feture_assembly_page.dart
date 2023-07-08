import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/feature_widget/datetime_picker_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/dialog_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/event_bus_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/file_storage_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/future_builder_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/sp_storage_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/sqflite_storage_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/stream_builder_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/willpopscope_widget.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';

/**
 * 功能型组件
 */
class FetureAssemblyPage extends StatefulWidget {
  const FetureAssemblyPage({ Key? key }) : super(key: key);

  @override
  State<FetureAssemblyPage> createState() => _FetureAssemblyPageState();
}

class _FetureAssemblyPageState extends State<FetureAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("datepicker_widget", DateTimePickerBottomSheetPage()),
    ItemButtonEntity("dialog_widget", DialogWidgetPage()),
    ItemButtonEntity("eventbus_widget", EventBusWidgetPage()),
    ItemButtonEntity("file_storage_widget", FileStorageWidgetPage()),
    ItemButtonEntity("future_builder_widget", FutureBuilderWidgetPage()),
    ItemButtonEntity("sp_storage_widget", SpStorageWidgetPage()),
    ItemButtonEntity("spflite_storage_widget", SqfliteStorageWidgetPage()),
    ItemButtonEntity("stream_builder_widget", StreamBuilderWidgetPage()),
    ItemButtonEntity("willpopscope_widget", WillPopScopeWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "功能型组件"),
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
