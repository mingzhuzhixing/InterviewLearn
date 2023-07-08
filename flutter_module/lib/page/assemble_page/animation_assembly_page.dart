import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/animation_assembly/animated_builder_widget.dart';
import 'package:flutter_module/assembly_library/animation_assembly/animated_switcher_widget.dart';
import 'package:flutter_module/assembly_library/animation_assembly/animated_widget.dart';
import 'package:flutter_module/assembly_library/animation_assembly/scale_animation_widget.dart';
import 'package:flutter_module/assembly_library/animation_assembly/translate_animation_widget.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';

/**
 * 动画组件
 */
class AnimationAssemblyPage extends StatefulWidget {
  const AnimationAssemblyPage({Key? key}) : super(key: key);

  @override
  State<AnimationAssemblyPage> createState() => _AnimationAssemblyPageState();
}

class _AnimationAssemblyPageState extends State<AnimationAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("AnimatedBuilder_widget", AnimatedBuilderWidgetpage()),
    ItemButtonEntity("AnimatedSwitcher_widget", AnimatedSwitcherWidgetPage()),
    ItemButtonEntity("Animated_widget", AnimatedWidgetsPage()),
    ItemButtonEntity("Scale_widget", ScaleAnimationPage()),
    ItemButtonEntity("Translate_widget", TranslateWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "动画组件"),
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
