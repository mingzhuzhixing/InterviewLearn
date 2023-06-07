import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/vertical_text.dart';

class VerticalTextPage extends StatefulWidget {
  const VerticalTextPage({Key? key}) : super(key: key);

  @override
  State<VerticalTextPage> createState() => _VerticalTextPageState();
}

/**
 *
 */
class _VerticalTextPageState extends State<VerticalTextPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "vertical_text_widget"),
      body: Container(
        color: Colors.red,
        child: const VerticalText(
          "优惠券",
          textStyle: TextStyle(
            fontSize: 17,
            wordSpacing: 1,
            letterSpacing: 1,
            color: Colors.black,
          ),
        ),
      ),
    );
  }
}
