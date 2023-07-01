import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class YsColor {
  static const Color H1 = Color(0xFF14C586);
  static const Color H3 = Color(0xFFE8FCF5);
  static const Color H4 = Color(0xFFFF594D);
  static const Color H5 = Color(0xFFC57D1C);
  static const Color H5_14 = Color(0x24C57D1C);
  static const Color H6 = Color(0xFF4DAAB8);
  static const Color H7 = Color(0xFFF7F7F7);
  static const Color H8 = Color(0xFFE6E6E6);
  static const Color H9 = Color(0xFFFFFFFF);
  static const Color S1 = Color(0xFF1F1F1F);
  static const Color S2 = Color(0xFF383838);
  static const Color S3 = Color(0xFF676767);
  static const Color S4 = Color(0xFF969696);
  static const Color S5 = Color(0xFFB3B3B3);

  static Color hexToColor(String? color) {
    if (color == null ||
        color.length != 7 ||
        int.tryParse(color.substring(1, 7), radix: 16) == null) {
      return S1;
    }
    var parse = int.parse(color.substring(1, 7), radix: 16);
    var hexColor = Color(parse + 0xFF000000);
    return hexColor;
  }
}
