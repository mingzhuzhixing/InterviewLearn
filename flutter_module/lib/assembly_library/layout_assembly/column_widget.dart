import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/src/size_extension.dart';

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
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          RaisedButton(
            onPressed: () {},
            child: Text("第一行的按钮"),
          ),
          RaisedButton(
            onPressed: () {},
            child: Text("第二行的按钮"),
          ),
          Container(
            height: 200.w,
            width: 300.w,
            color: Colors.lightBlue,
            child: Column(
              children: [
                Text("第1行的按钮"),
                Spacer(),
                Text("第2行的按钮"),
                Spacer(),
                Text("第3行的按钮"),
              ],
            ),
          )
        ],
      ),
    );
  }
}
