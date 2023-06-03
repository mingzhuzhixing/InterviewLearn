import 'package:flutter/material.dart';

class SizeBoxWidgetPage extends StatefulWidget {
  const SizeBoxWidgetPage({Key? key}) : super(key: key);

  @override
  State<SizeBoxWidgetPage> createState() => _SizeBoxWidgetPageState();
}

class _SizeBoxWidgetPageState extends State<SizeBoxWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("sizebox widget"),
      ),
      body: Column(
        children: [
          Text("sizebox1"),
          SizedBox(height: 10),
          Text("sizebox2"),
          SizedBox(height: 20),
          Text("sizebox3"),
          SizedBox(height: 30),
          Text("sizebox4"),

          Row(
            children: [
              Text("sizebox5"),
              SizedBox(width: 10),
              Text("sizebox6"),
              SizedBox(width: 20),
              Text("sizebox7"),
              SizedBox(width: 30),
              Text("sizebox8"),
            ],
          )
        ],
      ),
    );
  }
}
