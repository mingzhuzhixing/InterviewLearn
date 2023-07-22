import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class ItemButton extends StatelessWidget {
  final String title;
  final String type;
  final Widget widget;
  final int index;

  ItemButton(this.title, this.widget, {Color color = Colors.grey, this.type = "2", this.index = 0});

  final List<Color> colorList = [
    Color(0xffd9d9d9),
    Colors.grey,
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 1.sw,
      margin: const EdgeInsets.only(left: 2, right: 2),
      child: type == "1"
          ? Container(
              height: 40,
              padding: EdgeInsets.only(left: 10),
              alignment: Alignment.centerLeft,
              color: Colors.teal,
              child: Text(
                title,
                style: TextStyle(color: Colors.white, fontSize: 10.sp),
              ),
            )
          : MaterialButton(
              height: 50,
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return widget;
                }));
              },
              shape: RoundedRectangleBorder(
                side: BorderSide(color: Colors.white),
                borderRadius: BorderRadius.circular(6),
              ),
              elevation: 0.0,
              child: Text(
                title,
                style:
                    const TextStyle(color: Colors.black, fontSize: 16, fontWeight: FontWeight.w500),
              ),
              color: colorList[index % colorList.length],
            ),
    );
  }
}
