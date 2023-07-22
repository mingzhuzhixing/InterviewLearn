// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/widget/ys_image_load.dart';
import 'package:flutter_module/widget/ys_image_style.dart';
import 'package:flutter_module/widget/ys_text_direction.dart';
import 'package:flutter_module/widget/ys_text_style.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

///文字图片组合widget
///1、宽度自适应，但是文本不能超多行宽
///UnconstrainedBox(
///       child: Container(
///         color: Colors.yellow,
///         child: YsTextImage(
///           "wode_baiding_dengji",
///           "文本文本文本文本文本文本文",
///           direction: YsTextDirection.il_tr,
///           textStyle: YsTextStyle(maxLines: 1, overflow: TextOverflow.ellipsis),
///           matchParent: false,
///         ),
///       ),
///     )
///
/// 2、宽度充满，但是文本不能超出宽度省略
/// Container(
///         color: Colors.yellow,
///         child: YsTextImage(
///           "wode_baiding_dengji",
///           "文本文本文本文本文本文本文文本文本文本文本文文本文本文本文本文文本文本文本文本文",
///           direction: YsTextDirection.il_tr,
///           textStyle: YsTextStyle(maxLines: 1, overflow: TextOverflow.ellipsis),
///           matchParent: true,
///         ),
///     )
class YsTextImage extends StatelessWidget {
  YsTextDirection? direction;
  String imageName;
  String? title;
  double? drawablePadding;
  final YsTextStyle? textStyle;
  YsImageStyle? imageStyle;
  final GestureTapCallback? callback;
  bool matchParent = false;

  YsTextImage(this.imageName, this.title,
      {Key? key,
      this.direction,
      this.drawablePadding,
      this.textStyle,
      this.imageStyle,
      this.callback,
      this.matchParent = false}) {
    direction = direction ?? YsTextDirection.il_tr;
    imageStyle ??= YsImageStyle(width: 12.w, height: 12.w);
    drawablePadding = drawablePadding ?? 1.w;
  }

  @override
  Widget build(BuildContext context) {
    if (direction == YsTextDirection.il_tr) {
      return GestureDetector(
        onTap: callback,
        child: Row(
          children: [_getImage(), SizedBox(width: drawablePadding), _getText()],
        ),
      );
    } else if (direction == YsTextDirection.it_tb) {
      return GestureDetector(
        onTap: callback,
        child: Column(
          children: [_getImage(), SizedBox(width: drawablePadding), _getText()],
        ),
      );
    } else if (direction == YsTextDirection.ir_tl) {
      return GestureDetector(
        onTap: callback,
        child: Row(
          children: [_getText(), SizedBox(width: drawablePadding), _getImage()],
        ),
      );
    } else if (direction == YsTextDirection.ib_tt) {
      return GestureDetector(
        onTap: callback,
        child: Column(
          children: [_getText(), SizedBox(width: drawablePadding), _getImage()],
        ),
      );
    }
    return SizedBox();
  }

  Widget _getImage() {
    if (imageName.isEmpty) {
      return SizedBox();
    }
    return YsImageLoad(imageName, imageStyle: imageStyle);
  }

  Widget _getText() {
    if (matchParent) {
      return Expanded(
        child: Text(
          title ?? "",
          style: textStyle,
          maxLines: textStyle?.maxLines,
        ),
      );
    } else {
      return Text(
        title ?? "",
        style: textStyle,
        maxLines: textStyle?.maxLines,
      );
    }
  }
}
