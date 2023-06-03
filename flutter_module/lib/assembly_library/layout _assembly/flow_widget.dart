import 'package:flutter/material.dart';

/**
 * 流式布局(Wrap、Flow)
 *
 * 说明：Flow需要自己算，性能较好，换行规则自己定  Flow因为计算复杂使用较少，优先考虑Wrap是否能实现 详细
 */
class FlowWidgetPage extends StatefulWidget {
  const FlowWidgetPage({Key? key}) : super(key: key);

  @override
  State<FlowWidgetPage> createState() => _FlowWidgetPageState();
}

class _FlowWidgetPageState extends State<FlowWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}
