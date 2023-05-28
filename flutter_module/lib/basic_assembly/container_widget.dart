import 'package:flutter/material.dart';

///Container
class ContainerWidgetPage extends StatelessWidget {
  const ContainerWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("widget container"),
        ),
        body: Container(
          child: Text("这是Container"),
        ),
      ),
    );
  }
}
