import 'package:flutter/material.dart';

class CommonAppBar extends AppBar {
  CommonAppBar(
    BuildContext context,
    String titleStr, {Key? key,
    Color backgroundColor = Colors.white,
    Color arrowColor = Colors.black,
    double elevation = 1,
    required List<Widget> actions,
  }) : super(key: key,
            backgroundColor: backgroundColor,
            brightness: Brightness.dark,
            toolbarHeight: 44,
            elevation: elevation,
            centerTitle: true,
            leading: Padding(
              padding: EdgeInsets.only(left: 10, right: 23),
              child: IconButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                icon: const Icon(
                  Icons.arrow_back_ios_new,
                  color: Colors.white,
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
