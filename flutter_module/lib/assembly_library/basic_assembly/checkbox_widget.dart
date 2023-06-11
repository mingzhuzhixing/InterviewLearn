import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

class CheckboxWidgetPage extends StatefulWidget {
  const CheckboxWidgetPage({Key? key}) : super(key: key);

  @override
  State<CheckboxWidgetPage> createState() => _CheckboxWidgetPageState();
}

class _CheckboxWidgetPageState extends State<CheckboxWidgetPage> {
  bool _checkboxSelected = true; //维护复选框状态
  bool _checkboxSportSelected = true; //维护复选框状态

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Checkbox Widget"),
      body: Column(
        children: [
          Row(
            children: [
              Checkbox(
                value: _checkboxSelected,
                activeColor: Colors.red, //选中时的颜色
                onChanged: (value) {
                  setState(() {
                    _checkboxSelected = value!;
                  });
                },
              ),
              Text("爱好")
            ],
          ),
          Row(
            children: [
              Checkbox(
                value: _checkboxSportSelected,
                activeColor: Colors.blue, //选中时的颜色
                onChanged: (value) {
                  setState(() {
                    _checkboxSportSelected = value!;
                  });
                },
              ),
              Text("运动")
            ],
          )
        ],
      ),
    );
  }
}
