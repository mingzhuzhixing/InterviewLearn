import 'package:flutter/cupertino.dart';

class BottomItem {
  final dynamic iconPath;
  final dynamic title;
  final dynamic activatePath;

  //一定标准类型
  final BottomNavigationBarItem item;

  BottomItem({@required this.iconPath, @required this.title, @required this.activatePath})
      : item = BottomNavigationBarItem(
            label: title,
            icon: Image.asset(
              iconPath,
              width: 15,
              height: 15,
            ),
            activeIcon: Image.asset(
              activatePath,
              width: 15,
              height: 15,
            ));
}
