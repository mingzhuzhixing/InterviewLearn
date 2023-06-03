import 'package:flutter/material.dart';

///Radio 组件
class RadioWidgetPage extends StatefulWidget {
  const RadioWidgetPage({Key? key}) : super(key: key);

  @override
  _RadioWidgetPageState createState() => _RadioWidgetPageState();
}

class _RadioWidgetPageState extends State<RadioWidgetPage> {
  String _sex = "1";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Radio 组件"),
      ),
      body: Column(
        children: [
          Row(
            children: [
              const Text("男"),
              Radio(
                  value: 1,
                  groupValue: _sex,
                  onChanged: (v) {
                    setState(() {
                      _sex = v.toString();
                    });
                  }),
              const SizedBox(
                width: 20.0,
              ),
              const Text("女"),
              Radio(
                  value: 2,
                  groupValue: _sex,
                  onChanged: (v) {
                    setState(() {
                      _sex = v.toString();
                    });
                  })
            ],
          ),
          Row(
            children: [Text(_sex), Text(_sex == "1" ? "男" : "女")],
          )
        ],
      ),
    );
  }
}
