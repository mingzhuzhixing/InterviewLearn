import 'package:flutter/material.dart';

///Expanded
class ExpandedWidgetPage extends StatelessWidget {
  const ExpandedWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("expanded widget"),
      ),
      body: Row(
        children: [
          getExpended(Icons.home, Colors.orange, 1),
          getExpended(Icons.search, Colors.blue, 2),
        ],
      ),
    );
  }
}

/// Expended组件的使用
Widget getExpended(IconData icon, Color color, int flex) {
  return Expanded(
    child: MyIconContainer(icon, color),
    flex: flex,
  );
}

class MyIconContainer extends StatelessWidget {
  Color colors = Colors.red;
  IconData icons;
  double size = 45.0;

  MyIconContainer(this.icons, this.colors, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100,
      height: 100,
      color: colors,
      child: Center(
        child: Icon(
          icons,
          size: size,
          color: Colors.white,
        ),
      ),
    );
  }
}
