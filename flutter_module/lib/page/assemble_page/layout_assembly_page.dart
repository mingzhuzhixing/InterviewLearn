import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/layout_assembly/clip_wdiget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/rotatedbox_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/transform_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/wrap_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/column_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/expanded_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/flex_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/flexible_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/flow_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/indexedstack_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/offstage_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/positioned_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/row_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/spacer_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/stack_widget.dart';
import 'package:flutter_module/assembly_library/layout_assembly/visibility_widget.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';

/**
 * 布局类组件
 */
class LayoutAssemblyPage extends StatefulWidget {
  const LayoutAssemblyPage({ Key? key }) : super(key: key);

  @override
  State<LayoutAssemblyPage> createState() => _LayoutAssemblyPageState();
}

class _LayoutAssemblyPageState extends State<LayoutAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("clip_widget", ClipWidgetPage()),
    ItemButtonEntity("column_widget", ColumnWidgetPage()),
    ItemButtonEntity("expanded_widget", ExpandedWidgetPage()),
    ItemButtonEntity("flex_widget", FlexWidgetPage()),
    ItemButtonEntity("flexible_widget", FlexibleWidgetPage()),
    ItemButtonEntity("flow_widget", FlowWidgetPage()),
    ItemButtonEntity("positioned_widget", PositionedWidgetPage()),
    ItemButtonEntity("rotated_box_widget", RotatedBoxWidgetPage()),
    ItemButtonEntity("row_widget", RowWidgetPage()),
    ItemButtonEntity("spacer_widget", SpacerWidgetPage()),
    ItemButtonEntity("stack_widget", StackWidgetPage()),
    ItemButtonEntity("transform_widget", TransformWidgetPage()),
    ItemButtonEntity("visibility_widget", VisibilityWidgetPage()),
    ItemButtonEntity("indexstack_widget", IndexedStackWidgetPage()),
    ItemButtonEntity("offstage_widget", OffstageWidgetPage()),
    ItemButtonEntity("warp_widget", WrapWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "布局类组件"),
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
