import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/ys_toast.dart';
import 'package:fluttertoast/fluttertoast.dart';

class FlutterToastPage extends StatefulWidget {
  const FlutterToastPage({Key? key}) : super(key: key);

  @override
  State<FlutterToastPage> createState() => _FlutterToastPageState();
}

class _FlutterToastPageState extends State<FlutterToastPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "flutter toast"),
      body: Container(
        child: Column(
          children: [
            TextButton(
              onPressed: () {
                Fluttertoast.showToast(msg: "YsFluttertoast");
              },
              child: Text("flutter toast"),
            ),
            TextButton(
              onPressed: () {
                YsToast.makeText(context, "YsFluttertoast").show();
              },
              child: Text("YsToast"),
            ),
            TextButton(
              onPressed: () {
                YsToast.makeText(context, "打卡成功", uiStyle: 1).show();
              },
              child: Text("YsToast uiStyle 1"),
            )
          ],
        ),
      ),
    );
  }
}
