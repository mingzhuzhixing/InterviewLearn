import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

///图片样式
/// 可扩展样式
@immutable
class YsImageStyle with Diagnosticable {
  final double? width;

  final double? height;

  final Color? color;

  final BoxFit? fit;

  YsImageStyle({this.width, this.height, this.color, this.fit});
}
