import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

class SwitchWidgetPage extends StatefulWidget {
  const SwitchWidgetPage({Key? key}) : super(key: key);

  @override
  State<SwitchWidgetPage> createState() => _SwitchWidgetPageState();
}

class _SwitchWidgetPageState extends State<SwitchWidgetPage> {
  bool _switchSelected = true; //维护单选开关状态

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Switch Widget"),
      body: Column(
        children: [
          Row(
            children: [
              Switch(
                value: _switchSelected, //当前状态
                onChanged: (value) {
                  //重新构建页面
                  setState(() {
                    _switchSelected = value;
                  });
                },
              ),
              Text("开关")
            ],
          )
        ],
      ),
    );
  }
}
