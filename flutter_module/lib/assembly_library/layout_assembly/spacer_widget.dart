import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * Spacer组件
 *
 * Spacer的通过Expanded的实现的，和Expanded的区别是：Expanded可以设置子控件，而Spacer的子控件尺寸是0，
 * 因此Spacer适用于撑开Row、Column、Flex的子控件的空隙
 */
class SpacerWidgetPage extends StatefulWidget {
  const SpacerWidgetPage({Key? key}) : super(key: key);

  @override
  State<SpacerWidgetPage> createState() => _SpacerWidgetPageState();
}

class _SpacerWidgetPageState extends State<SpacerWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Spacer Widget"),
      body: Row(
        children: <Widget>[
          Container(
            width: 100,
            height: 50,
            color: Colors.green,
          ),
          Spacer(
            flex: 2,
          ),
          Container(
            width: 100,
            height: 50,
            color: Colors.blue,
          ),
          Spacer(),
          Container(
            width: 100,
            height: 50,
            color: Colors.red,
          ),
        ],
      ),
    );
  }
}
