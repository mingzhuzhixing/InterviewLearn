import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * AspectRatio组件是固定宽高比的组件，如果组件的宽度固定，希望高是宽的1/2，可以用AspectRatio实现此效果
 */
class AspectRatioWidgetPage extends StatefulWidget {
  const AspectRatioWidgetPage({Key? key}) : super(key: key);

  @override
  State<AspectRatioWidgetPage> createState() => _AspectRatioWidgetPageState();
}

class _AspectRatioWidgetPageState extends State<AspectRatioWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "AspectRatio Widget"),
      body: Column(
        children: [
          AspectRatio(
            aspectRatio: 2 / 1,
            child: Container(color: Colors.red),
          )
        ],
      ),
    );
  }
}
