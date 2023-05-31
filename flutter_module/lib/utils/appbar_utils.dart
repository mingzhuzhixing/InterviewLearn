import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

/// 自定义appbar
class CommonAppBar extends AppBar {
  CommonAppBar(
    BuildContext context,
    String titleStr, {
    Key? key,
    Color backgroundColor = Colors.white,
    Color arrowColor = Colors.black,
    double elevation = 1,
    List<Widget>? actions,
  }) : super(
            key: key,
            backgroundColor: backgroundColor,
            brightness: Brightness.dark,
            toolbarHeight: 44,
            elevation: elevation,
            centerTitle: true,
            leading: Padding(
              padding: const EdgeInsets.only(left: 10, right: 23),
              child: GestureDetector(
                onTap: () => Navigator.pop(context),
                child: const Icon(
                  Icons.arrow_back_ios_new,
                  color: Colors.black,
                  size: 20,
                ),
              ),
            ),
            title: Text(
              titleStr,
              textScaleFactor: 1.0,
              style: const TextStyle(color: Colors.black, fontSize: 16),
            ),
            actions: actions);
}
