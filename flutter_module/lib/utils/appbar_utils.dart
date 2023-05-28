import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
class AppBarUtils{
  static PreferredSizeWidget appBar(String title) {
    return AppBar(
      title: Text(title),
    );
  }
}

