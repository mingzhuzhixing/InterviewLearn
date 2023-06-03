import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';

/// 弹性布局(Flex)
class FlexWidgetPage extends StatefulWidget {
  const FlexWidgetPage({Key? key}) : super(key: key);

  @override
  State<FlexWidgetPage> createState() => _FlexWidgetPageState();
}

//Flex里面的两个Expanded按照1：2平分。
class _FlexWidgetPageState extends State<FlexWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Flex Widget"),
      body: Flex(
        direction: Axis.horizontal,
        children: <Widget>[
          Expanded(
            child: Container(
              height: 30,
              color: Colors.red,
            ),
            flex: 1,
          ),
          Expanded(
            child: Container(
              height: 30,
              color: Colors.blue,
            ),
            flex: 2,
          ),
        ],
      ),
    );
  }
}
