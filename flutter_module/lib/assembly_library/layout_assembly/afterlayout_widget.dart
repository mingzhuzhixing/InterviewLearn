import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * 获取组件大小和相对于屏幕的坐标
 *
 */
class AfterLayoutWidgetPage extends StatefulWidget {
  const AfterLayoutWidgetPage({Key? key}) : super(key: key);

  @override
  State<AfterLayoutWidgetPage> createState() => _AfterLayoutWidgetPageState();
}

class _AfterLayoutWidgetPageState extends State<AfterLayoutWidgetPage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context,"AfterLayout Widget"),
      body: Column(
        mainAxisSize: MainAxisSize.min,
        children: [

        ],
      ),
    );
  }
}
