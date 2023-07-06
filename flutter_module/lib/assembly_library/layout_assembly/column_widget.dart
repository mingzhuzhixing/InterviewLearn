import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

///Row column布局
class ColumnWidgetPage extends StatelessWidget {
  const ColumnWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Column Widget"),
      extendBody: true,
      body: Column(
        children: [
          ItemButton("FirstWidgetPage", FirstWidgetPage(), index: 0),
          ItemButton("SecondWidgetPage", SecondWidgetPage(), index: 1),
          ItemButton("ThirdWidgetPage", ThirdWidgetPage(), index: 0),
          ItemButton("FourWidgetPage", FourWidgetPage(), index: 1),
          ItemButton("FiveWidgetPage", FiveWidgetPage(), index: 0),
        ],
      ),
    );
  }
}

class FirstWidgetPage extends StatelessWidget {
  const FirstWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Column Widget"),
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

class SecondWidgetPage extends StatelessWidget {
  const SecondWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Column Widget"),
      body: Container(
        padding: EdgeInsets.all(10.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              'Restaurants nearby',
              style: TextStyle(
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
            Divider(),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                FilledButton(
                  child: Text('Enter restaurant manually'),
                  onPressed: () {
                    print('Button pressed');
                  },
                ),
              ],
            ),
            Flexible(
              child: ListView.builder(
                itemBuilder: (BuildContext context, int index) {
                  return ListTile(
                    leading: CircleAvatar(
                      backgroundColor: Colors.cyan,
                    ),
                    title: Text('Test restaurant'),
                    subtitle: Text('80m'),
                  );
                },
                itemCount: 15,
              ),
            ),
            Text(
              'Restaurants nearby',
              style: TextStyle(
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class ThirdWidgetPage extends StatelessWidget {
  const ThirdWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Column Widget"),
      body: Container(
        padding: EdgeInsets.all(10.0),
        color: Colors.yellow,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              'Restaurants nearby',
              style: TextStyle(
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
            Divider(),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                FilledButton(
                  child: Text('Enter restaurant manually'),
                  onPressed: () {
                    print('Button pressed');
                  },
                ),
              ],
            ),
            Expanded(
              flex: 2,
              child: ListView.builder(
                itemBuilder: (BuildContext context, int index) {
                  return Container(
                    height: 40.w,
                    color: Colors.red,
                    margin: EdgeInsets.all(5.w),
                    child: Text('Test11'),
                  );
                },
                shrinkWrap: true,
                itemCount: 15,
                itemExtent: 100,
                scrollDirection: Axis.horizontal,
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class FourWidgetPage extends StatelessWidget {
  const FourWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    List<Widget> childWidget = [];
    for (int i = 0; i < 15; i++) {
      childWidget.add(Container(
        height: 100.w,
        child: Column(
          children: [Text("item$i"), Text("item$i")],
        ),
      ));
    }
    return Scaffold(
      appBar: CommonAppBar(context, "Column Widget"),
      body: Container(
        color: Colors.red,
        child: SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: Row(
            children: childWidget,
          ),
        ),
      ),
    );
  }
}

class FiveWidgetPage extends StatelessWidget {
  const FiveWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Column Widget"),
      body: Container(
        color: Colors.white,
        child: Column(
          children: [
            Row(
              children: [Text("我是头部"), Spacer(), Text("我是尾部")],
            ),
            SizedBox(height: 20.w),
            Align(
              alignment: Alignment.centerRight,
              child: Image.asset(
                "assets/images/icon_more.png",
                width: 38.w,
                height: 38.w,
              ),
            )
          ],
        ),
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
