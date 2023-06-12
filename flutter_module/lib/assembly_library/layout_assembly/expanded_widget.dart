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
      body: Column(
        children: [
          Row(
            children: [
              getExpended(Icons.home, Colors.orange, 1),
              getExpended(Icons.search, Colors.blue, 2),
            ],
          ),
          SizedBox(height: 10),
          //Expanded继承字Flexible，fit参数固定为FlexFit.tight，也就是说Expanded必须（强制）填满剩余空间。上面的OutlineButton想要充满剩余空间可以使用Expanded：
          Row(
            children: <Widget>[
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
              Expanded(
                child: MaterialButton(
                  onPressed: () {},
                  child: Text('OutlineButton'),
                ),
              ),
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
            ],
          ),
          SizedBox(height: 10),
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
  final Color colors;
  final IconData icons;
  final double size = 45.0;

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
