import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class ToggleButtonsWidgetPage extends StatefulWidget {
  const ToggleButtonsWidgetPage({Key? key}) : super(key: key);

  @override
  State<ToggleButtonsWidgetPage> createState() => _ToggleButtonsWidgetPageState();
}

class _ToggleButtonsWidgetPageState extends State<ToggleButtonsWidgetPage> {
  List<SelectData> selectData = [];

  List<bool> isSelected = [true, false, false];
  List<bool> isSelected2 = [true, false, false];
  List<bool> isSelected3 = [true, false, false];
  List<Icon> icons = [
    Icon(Icons.ac_unit),
    Icon(Icons.call),
    Icon(Icons.cake),
  ];

  @override
  void initState() {
    super.initState();
    for (int i = 0; i < 6; i++) {
      selectData.add(SelectData("$i", i == 1));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "ToggleButtons Widget"),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          firstToggleButton(),
          SizedBox(height: 20.w),
          secondToggleButton(),
          SizedBox(height: 20.w),
          selectItem(),
        ],
      ),
    );
  }

  /**
   * 第一种，可以多选
   */
  Widget firstToggleButton() {
    return ToggleButtons(
      children: icons,
      onPressed: (int index) {
        setState(() {
          isSelected[index] = !isSelected[index];
        });
      },
      isSelected: isSelected,
    );
  }

  Widget secondToggleButton() {
    return ToggleButtons(
      children: icons,
      onPressed: (int index) {
        setState(() {
          for (int buttonIndex = 0; buttonIndex < isSelected2.length; buttonIndex++) {
            if (buttonIndex == index) {
              isSelected2[buttonIndex] = !isSelected2[buttonIndex];
            } else {
              isSelected2[buttonIndex] = false;
            }
          }
        });
      },
      isSelected: isSelected2,
    );
  }

  /**
   * 单选
   */
  Widget selectItem() {
    return Container(
      height: 180.w,
      color: Color(0x4d64DD17),
      padding: EdgeInsets.all(10),
      child: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 3,
          mainAxisSpacing: 10,
          crossAxisSpacing: 10,
          childAspectRatio: 204 / 136, //宽高比 默认1
        ),
        itemBuilder: (BuildContext context, int index) {
          return itemButton(selectData[index]);
        },
        itemCount: selectData.length,
      ),
    );
  }

  Widget itemButton(SelectData data) {
    return GestureDetector(
      onTap: () {
        setState(() {
          for (int i = 0; i < selectData.length; i++) {
            selectData[i].status = false;
          }
          data.status = true;
        });
      },
      child: Container(
        decoration: BoxDecoration(
          color: data.status ? Color(0xFFFEF6F4) : Color(0xFFFFFFFF),
          border: Border.all(
              color: data.status ? Color(0xffE65F3C) : Color(0xffDFDFDF),
              width: data.status ? 2.w : 1.w),
          borderRadius: BorderRadius.all(Radius.circular(10)),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              "${data.id}有书币",
              style: TextStyle(
                  fontSize: 16.sp, color: data.status ? Color(0xffE65F3C) : Color(0xff444444)),
            ),
            Text(
              "${data.id}元",
              style: TextStyle(
                  fontSize: 12.sp, color: data.status ? Color(0xffFA8364) : Color(0xFFb3b3b3)),
            ),
          ],
        ),
      ),
    );
  }
}

class SelectData {
  String id;
  bool status;

  SelectData(this.id, this.status);
}
