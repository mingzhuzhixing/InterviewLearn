import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/basic_assembly/banner_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/button_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/card_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/expandicon_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/icon_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/image_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/linear_progress_indicator_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/radio_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/radiolisttile_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/swiper_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/tab_tabbar_controller_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/text_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/textfield_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/togglebuttons_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/tooltip_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/top_tabbar_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/vertical_text_widget.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';

/**
 * 基础组件
 */
class BaseAssemblyPage extends StatefulWidget {
  const BaseAssemblyPage({Key? key}) : super(key: key);

  @override
  State<BaseAssemblyPage> createState() => _BaseAssemblyPageState();
}

class _BaseAssemblyPageState extends State<BaseAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("banner_widget", BannerWidgetPage()),
    ItemButtonEntity("button_widget", ButtonWidgetPage()),
    ItemButtonEntity("card_widget", CardWidgetPage()),
    ItemButtonEntity("expandicon_widget", ExpandIconWidgetPage()),
    ItemButtonEntity("text_widget", TextWidgetPage()),
    ItemButtonEntity("textfield_widget", TextFieldWidgetPage()),
    ItemButtonEntity("togglebuttons_widget", ToggleButtonsWidgetPage()),
    ItemButtonEntity("icon_widget", IconWidgetPage()),
    ItemButtonEntity("image_widget", ImageWidgetPage()),
    ItemButtonEntity("linear_progress_indicator_widget",
        LinearProgressIndicatorWidgetPage()),
    ItemButtonEntity("swiper_widget", SwiperWidgetPage()),
    ItemButtonEntity("radio_widget", RadioWidgetPage()),
    ItemButtonEntity("radiolisttile_widget", RadioListTileWidgetPage()),
    ItemButtonEntity("tooltip", TooltipWidgetPage()),
    ItemButtonEntity("top_tabbar_widget", TopTabBarPage()),
    ItemButtonEntity("top_tabbar_controller_widget", TabBarControllerPage()),
    ItemButtonEntity("vertical_text_widget", VerticalTextPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "基础组件"),
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
