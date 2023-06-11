import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

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
          MaterialButton(
            onPressed: () {},
            child: Text("第一行的按钮"),
          ),
          MaterialButton(
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

// https://book.flutterchina.club/chapter4/row_and_column.html#_4-3-3-column
//特殊情况
//如果Row里面嵌套Row，或者Column里面再嵌套Column，那么只有最外面的Row或Column会占用尽可能大的空间，里面Row或Column所占用的空间为实际大小
// Container(
//   color: Colors.green,
//   child: Padding(
//     padding: const EdgeInsets.all(16.0),
//     child: Column(
//       crossAxisAlignment: CrossAxisAlignment.start,
//       mainAxisSize: MainAxisSize.max, //有效，外层Colum高度为整个屏幕
//       children: <Widget>[
//         Container(
//           color: Colors.red,
//           child: Column(
//             mainAxisSize: MainAxisSize.max,//无效，内层Colum高度为实际高度
//             children: <Widget>[
//               Text("hello world "),
//               Text("I am Jack "),
//             ],
//           ),
//         )
//       ],
//     ),
//   ),
// );
