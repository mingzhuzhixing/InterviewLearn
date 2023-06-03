import 'package:flutter/material.dart';

///RadioListTile 组件
class RadioListTileWidgetPage extends StatefulWidget {
  const RadioListTileWidgetPage({Key? key}) : super(key: key);

  @override
  _RadioListTileWidgetPageState createState() => _RadioListTileWidgetPageState();
}

class _RadioListTileWidgetPageState extends State<RadioListTileWidgetPage> {
  String _sex = "1";

  bool _flag = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("RadioListTile 组件"),
      ),
      body: Column(
        children: <Widget>[
          RadioListTile(
              value: 1,
              groupValue: _sex,
              onChanged: (v) {
                setState(() {
                  _sex = v.toString();
                });
              },
              title: const Text("标题"),
              subtitle: const Text("第二个标题"),
              selected: _sex == "1",
              secondary: const Icon(Icons.help)),
          RadioListTile(
              value: 2,
              groupValue: _sex,
              onChanged: (v) {
                setState(() {
                  _sex = v.toString();
                });
              },
              title: const Text("标题"),
              subtitle: const Text("第二个标题"),
              selected: _sex == "2",
              secondary: Image.network(
                  "https://img0.baidu.com/it/u=1797544775,2912350681&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")),
          const SizedBox(height: 40.0),
          Switch(
            value: _flag,
            onChanged: (v) {
              setState(() {
                _flag = v;
              });
            },
          )
        ],
      ),
    );
  }
}
