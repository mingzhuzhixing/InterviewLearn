// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/widget/ys_image_style.dart';

class YsImageLoad extends StatelessWidget {
  String src;
  YsImageStyle? imageStyle;

  YsImageLoad(this.src, {this.imageStyle});

  //todo zhuming 目前取assets中的图片，后期修改本地的
  @override
  Widget build(BuildContext context) {
    if (src.isEmpty) {
      return SizedBox();
    }
    if (src.startsWith("http")) {
      return Image.network(
        src,
        width: imageStyle?.width,
        height: imageStyle?.height,
        color: imageStyle?.color,
        fit: imageStyle?.fit,
      );
    } else if (src.contains("assets/images")) {
      return Image.asset(
        src,
        width: imageStyle?.width,
        height: imageStyle?.height,
        color: imageStyle?.color,
        fit: imageStyle?.fit,
      );
    } else {
      src = "assets/images/${src}.png";
      return Image.asset(
        src,
        width: imageStyle?.width,
        height: imageStyle?.height,
        color: imageStyle?.color,
        fit: imageStyle?.fit,
      );
    }
  }
}
