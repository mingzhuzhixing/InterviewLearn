import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

///Row column布局
class ColumnWidgetPage extends StatelessWidget {
  const ColumnWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Column Widget"),
      ),
      body: Column(
        children: <Widget>[
          RaisedButton(
            onPressed: () {},
            child: Text("第一行的按钮"),
          ),
          RaisedButton(
            onPressed: () {},
            child: Text("第二行的按钮"),
          )
        ],
      ),
    );
  }
}
