import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/basic_assembly/banner_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/button_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/card_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/checkbox_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/expandicon_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/icon_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/image_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/radio_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/radiolisttile_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/rogressindicator_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/swiper_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/switch_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/text_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/textfield_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/togglebuttons_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/tooltip_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/vertical_text_widget.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_module/widget/no_scroll_behavior.dart';

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
    ItemButtonEntity("Banner_widget", BannerWidgetPage()),
    ItemButtonEntity("Button_widget", ButtonWidgetPage()),
    ItemButtonEntity("Card_widget", CardWidgetPage()),
    ItemButtonEntity("CheckBox_widget", CheckboxWidgetPage()),
    ItemButtonEntity("ExpandIcon_widget", ExpandIconWidgetPage()),
    ItemButtonEntity("Text_widget", TextWidgetPage()),
    ItemButtonEntity("TextField_widget", TextFieldWidgetPage()),
    ItemButtonEntity("ToggleButtons_widget", ToggleButtonsWidgetPage()),
    ItemButtonEntity("Icon_widget", IconWidgetPage()),
    ItemButtonEntity("Image_widget", ImageWidgetPage()),
    ItemButtonEntity("ProgressIndicator_widget", ProgressIndicatorWidget()),
    ItemButtonEntity("Swiper_widget", SwiperWidgetPage()),
    ItemButtonEntity("Switch_widget", SwitchWidgetPage()),
    ItemButtonEntity("Radio_widget", RadioWidgetPage()),
    ItemButtonEntity("RadioListTile_widget", RadioListTileWidgetPage()),
    ItemButtonEntity("ToolTip", TooltipWidgetPage()),
    ItemButtonEntity("VerticalText_widget", VerticalTextPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "基础组件"),
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
