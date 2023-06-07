import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/dotted_border.dart';

/**
 * Flutter各种虚线实战和虚线边框原理
 */
class DottedBorderWidgetPage extends StatefulWidget {
  const DottedBorderWidgetPage({Key? key}) : super(key: key);

  @override
  State<DottedBorderWidgetPage> createState() => _DottedBorderWidgetPageState();
}

class _DottedBorderWidgetPageState extends State<DottedBorderWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: CommonAppBar(context, "DottedBorder Widget"),
        body: Container(
          padding: EdgeInsets.all(10.0),
          child: Column(
            children: [
              DottedBorder(
                dashPattern: [8, 4],
                strokeWidth: 2,
                padding: EdgeInsets.all(0),
                child: Container(
                  height: 100,
                  width: 220,
                  color: Colors.red,
                  // color: Colors.black26,
                ),
              ),
              SizedBox(height: 10),
              DottedBorder(
                borderType: BorderType.RRect,
                radius: Radius.circular(12),
                padding: EdgeInsets.all(6),
                child: ClipRRect(
                  borderRadius: BorderRadius.all(Radius.circular(12)),
                  child: Container(
                    height: 100,
                    width: 220,
                    color: Colors.amber,
                  ),
                ),
              ),
              SizedBox(height: 10),
              DottedBorder(
                color: Colors.red,
                borderType: BorderType.RRect,
                radius: Radius.circular(3),
                padding: EdgeInsets.only(left: 4, right: 4, top: 1, bottom: 1),
                child: ClipRRect(
                  borderRadius: BorderRadius.all(Radius.circular(12)),
                  child: Text("全平台"),
                ),
              ),
              SizedBox(height: 10),
              DottedBorder(
                color: Colors.red,
                borderType: BorderType.Oval,
                radius: Radius.circular(3),
                padding: EdgeInsets.only(left: 4, right: 4, top: 1, bottom: 1),
                child: ClipRRect(
                  borderRadius: BorderRadius.all(Radius.circular(12)),
                  child: Text("全平台"),
                ),
              ),
              SizedBox(height: 10),
              DottedBorder(
                color: Colors.red,
                borderType: BorderType.Circle,
                radius: Radius.circular(3),
                padding:
                    EdgeInsets.only(left: 30, top: 30, right: 30, bottom: 30),
                child: ClipRRect(
                  borderRadius: BorderRadius.all(Radius.circular(12)),
                  child: Text("全平台"),
                ),
              )
            ],
          ),
        ));
  }
}
