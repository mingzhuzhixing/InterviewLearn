// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';

///字体样式
@immutable
class YsTextStyle extends TextStyle {
  int? maxLines;

  YsTextStyle(
      {bool inherit = true,
      Color? color,
      Color? backgroundColor,
      double? fontSize,
      FontWeight? fontWeight,
      FontStyle? fontStyle,
      double? letterSpacing,
      double? wordSpacing,
      TextBaseline? textBaseline,
      double? height,
      TextOverflow? overflow,
      this.maxLines})
      : super(
            inherit: inherit,
            color: color,
            backgroundColor: backgroundColor,
            fontSize: fontSize,
            fontWeight: fontWeight,
            fontStyle: fontStyle,
            letterSpacing: letterSpacing,
            wordSpacing: wordSpacing,
            textBaseline: textBaseline,
            height: height,
            overflow: overflow);
}
