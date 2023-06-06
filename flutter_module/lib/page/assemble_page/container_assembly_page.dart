import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/container_assembly/align_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/aspectratio_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/center_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/constrainedbox_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/container_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/decoratedbox_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/dotted_border_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/fractionallysizedbox_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/padding_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/sizebox_widget.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/utils/appbar_utils.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';


/**
 * 容器类组件
 */
class ContainerAssemblyPage extends StatefulWidget {
  const ContainerAssemblyPage({ Key? key }) : super(key: key);

  @override
  State<ContainerAssemblyPage> createState() => _ContainerAssemblyPageState();
}

class _ContainerAssemblyPageState extends State<ContainerAssemblyPage> {
  
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
   ItemButtonEntity("align_widget", AlignWidgetPage()),
    ItemButtonEntity("aspectradio_widget", AspectRatioWidgetPage()),
    ItemButtonEntity("center_widget", CenterWidgetPage()),
    ItemButtonEntity("constrainedbox_widget", ConstrainedBoxWidgetPage()),
    ItemButtonEntity("container_widget", ContainerWidgetPage()),
    ItemButtonEntity("decoratedbox_widget", DecoratedBoxWidgetPage()),
    ItemButtonEntity("dotted_border_widget", DottedBorderWidgetPage()),
    ItemButtonEntity("fractionallysizedbox_widget", FractionallySizedBoxWidgetPage()),
    ItemButtonEntity("padding_widget", PaddingWidgetPage()),
    ItemButtonEntity("sizebox_widget", SizeBoxWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "容器类组件"),
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
