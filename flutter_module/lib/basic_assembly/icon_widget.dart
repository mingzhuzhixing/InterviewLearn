import 'package:flutter/material.dart';

///icon_widget
class IconWidgetPage extends StatefulWidget {
  const IconWidgetPage({Key? key}) : super(key: key);

  @override
  State<IconWidgetPage> createState() => _IconWidgetPageState();
}

class _IconWidgetPageState extends State<IconWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("icon widget")),
      body: Center(
        child: Column(
          children: [],
        ),
      ),
    );
  }
}
