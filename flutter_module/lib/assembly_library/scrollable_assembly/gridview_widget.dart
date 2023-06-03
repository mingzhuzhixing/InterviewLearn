import 'package:flutter/material.dart';

class GridViewWidgetPage extends StatefulWidget {
  const GridViewWidgetPage({Key? key}) : super(key: key);

  @override
  State<GridViewWidgetPage> createState() => _GridViewWidgetPageState();
}

class _GridViewWidgetPageState extends State<GridViewWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("gridview widget"),
      ),
      body: Text("gridview"),
    );
  }
}

