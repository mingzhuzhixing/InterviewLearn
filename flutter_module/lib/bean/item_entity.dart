import 'package:flutter/material.dart';

class ItemButtonEntity {
  String title;
  Widget? widget;
  String? type;

  ItemButtonEntity(this.title, this.widget, {this.type = "2"});
}
