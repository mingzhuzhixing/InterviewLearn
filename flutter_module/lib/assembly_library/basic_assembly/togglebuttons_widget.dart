import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class ToggleButtonsWidgetPage extends StatefulWidget {
  const ToggleButtonsWidgetPage({Key? key}) : super(key: key);

  @override
  State<ToggleButtonsWidgetPage> createState() =>
      _ToggleButtonsWidgetPageState();
}

class _ToggleButtonsWidgetPageState extends State<ToggleButtonsWidgetPage> {
  List<Widget> children = [];
  String selectId = "";

  @override
  void initState() {
    super.initState();
     print("zm1234 初始化initState");
  }

  @override
  Widget build(BuildContext context) {
    print("zm1234 执行build");
    children.clear();
     for (int i = 0; i < 6; i++) {
      children.add(itemButton("$i", selectId == "$i" ? true : false));
    }
    return Scaffold(
      appBar: CommonAppBar(context, "ToggleButtons Widget"),
      body: Container(
        padding: EdgeInsets.all(10),
        child: GridView.count(
          scrollDirection: Axis.vertical,
          crossAxisCount: 3, //一行多少个
          crossAxisSpacing: 10, //水平间隔
          mainAxisSpacing: 10, //垂直间隔
          childAspectRatio: 204 / 136, //宽高比 默认1
          children: children,
        ),
      ),
    );
  }

  Widget itemButton(String id, bool isSelectid) {
    return GestureDetector(
      onTap: () {
        setState(() {
          selectId = id;
          print("zm1234 id:$id");
        });
      },
      child: Container(
        decoration: BoxDecoration(
          color: isSelectid ? Color(0xFFFEF6F4) : Color(0xFFFFFFFF),
          border: Border.all(
              color: Color(0xffDFDFDF), width: isSelectid ? 4.w : 2.w),
          borderRadius: BorderRadius.all(Radius.circular(10)),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              "$id有书币",
              style: TextStyle(
                  fontSize: 32.sp,
                  color: isSelectid ? Color(0xffE65F3C) : Color(0xff444444)),
            ),
            Text(
              "$id元",
              style: TextStyle(
                  fontSize: 24.sp,
                  color: isSelectid ? Color(0xffFA8364) : Color(0xFFb3b3b3)),
            ),
          ],
        ),
      ),
    );
  }
}
