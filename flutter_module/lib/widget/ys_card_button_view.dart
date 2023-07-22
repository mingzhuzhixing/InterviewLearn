// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/bean/card_button.dart';
import 'package:flutter_module/widget/ys_image_load.dart';
import 'package:flutter_module/widget/ys_image_style.dart';
import 'package:flutter_module/widget/ys_text_direction.dart';
import 'package:flutter_module/widget/ys_text_style.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

typedef CardCallBackListener = Function(Widget view, dynamic obj);

/// 纯文本按钮
/// 注：按钮上可以定制样式边框背景等样式
///
/// 用法如下：
/// TextCardButton(
///           CardButton()..label = widget.cardBean?.bottom_label1,
///           style: TextStyle(fontSize: 22.sp),
///           padding:
///              EdgeInsets.only(left: 6.w, right: 6.w, top: 4.w, bottom: 4.w),
///           decoration: BoxDecoration(
///               color: Color(0x24C57D1C),
///              borderRadius: BorderRadius.all(Radius.circular(8.w))),
///         )
class TextCardButton extends BaseCardButton {
  TextCardButton(super.cardButton,
      {YsTextStyle? textStyle,
      BoxDecoration? decoration,
      EdgeInsetsGeometry? padding,
      EdgeInsetsGeometry? margin,
      CardCallBackListener? callback,
      double? width,
      double? height,
      bool matchParent = false})
      : super(
            textStyle: textStyle,
            decoration: decoration,
            padding: padding,
            margin: margin,
            callback: callback,
            width: width,
            height: height,
            matchParent: matchParent);

  @override
  Widget childWidget() {
    return _getText();
  }
}

/// 纯图片按钮
/// 注：按钮上可以定制样式边框背景等样式
/// @param imageStyle 图片样式 默认图片宽高 24
class ImageCardButton extends BaseCardButton {
  YsImageStyle? imageStyle;

  ImageCardButton(super.cardButton,
      {this.imageStyle,
      double? width,
      double? height,
      EdgeInsetsGeometry? padding,
      EdgeInsetsGeometry? margin})
      : super(width: width, height: height, padding: padding, margin: margin) {
    imageStyle ??= YsImageStyle(width: 12.w, height: 12.w);
  }

  @override
  Widget childWidget() {
    return _getImage(imageStyle);
  }
}

/// 图文混合button
/// 注：按钮上可以定制样式边框背景等样式
/// 实例：
/// ImageAndTextCardButton(
///                 value,
///                 12.w,
///                 12.w,
///                 direction: YsTextDirection.il_tr,
///                 style: TextStyle(color: Colors.red),
///               )
/// 参数说明：
/// @param direction 图文的排列方向
///     YsTextDirection.il_tr, 左图右文
///     YsTextDirection.it_tb, 上图下文
///     YsTextDirection.ir_tl, 右图左文
///     YsTextDirection.ib_tt, 下图上文
/// @param drawablePadding 图文之间的间距
/// @param style 文字样式
/// @param imageStyle 图片样式 默认图片宽高 24
class ImageAndTextCardButton extends BaseCardButton {
  YsTextDirection? direction;
  double? drawablePadding;
  YsImageStyle? imageStyle;

  ImageAndTextCardButton(super.cardButton,
      {this.imageStyle,
      this.direction = YsTextDirection.il_tr,
      this.drawablePadding = 0,
      BoxDecoration? decoration,
      YsTextStyle? textStyle,
      double? width,
      double? height,
      EdgeInsetsGeometry? padding,
      EdgeInsetsGeometry? margin,
      bool matchParent = false})
      : super(
            textStyle: textStyle,
            decoration: decoration,
            width: width,
            height: height,
            padding: padding,
            margin: margin,
            matchParent: matchParent) {
    imageStyle ??= YsImageStyle(width: 12.w, height: 12.w);
  }

  @override
  Widget childWidget() {
    if (direction == YsTextDirection.il_tr) {
      return Row(
        children: [_getImage(imageStyle), SizedBox(width: drawablePadding), _getText()],
      );
    } else if (direction == YsTextDirection.it_tb) {
      return Column(
        children: [_getImage(imageStyle), SizedBox(width: drawablePadding), _getText()],
      );
    } else if (direction == YsTextDirection.ir_tl) {
      return Row(
        children: [_getText(), SizedBox(width: drawablePadding), _getImage(imageStyle)],
      );
    } else if (direction == YsTextDirection.ib_tt) {
      return Column(
        children: [_getText(), SizedBox(width: drawablePadding), _getImage(imageStyle)],
      );
    }
    return SizedBox();
  }
}

abstract class BaseCardButton extends StatefulWidget {
  CardButton cardButton;
  YsTextStyle? textStyle;
  BoxDecoration? decoration;
  EdgeInsetsGeometry? padding;
  EdgeInsetsGeometry? margin;
  CardCallBackListener? callback;
  double? width;
  double? height;
  AlignmentGeometry? alignment;
  bool matchParent;

  BaseCardButton(this.cardButton,
      {this.textStyle,
      this.decoration,
      this.padding,
      this.margin,
      this.callback,
      this.height,
      this.width,
      this.alignment,
      this.matchParent = false}) {
    textStyle ??= YsTextStyle(color: Colors.black);
  }

  @override
  State<BaseCardButton> createState() => _CardButtonViewState();

  /// 子Widget
  Widget childWidget();

  /// 获取Image
  Widget _getImage(YsImageStyle? imageStyle) {
    if (cardButton.icon?.isNotEmpty ?? false) {
      return YsImageLoad(cardButton.icon ?? "", imageStyle: imageStyle);
    } else {
      return SizedBox();
    }
  }

  /// 获取label
  Widget _getText() {
    if (cardButton.label?.isNotEmpty ?? false) {
      if (matchParent) {
        return Expanded(
          child: Text(
            cardButton.label ?? "",
            style: textStyle,
            maxLines: textStyle?.maxLines,
          ),
        );
      } else {
        return Text(
          cardButton.label ?? "",
          style: textStyle,
          maxLines: textStyle?.maxLines,
        );
      }
    } else {
      return SizedBox();
    }
  }
}

class _CardButtonViewState extends State<BaseCardButton> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        onCardButtonClick(widget.cardButton);
      },
      child: Container(
        height: widget.height,
        width: widget.width,
        decoration: widget.decoration,
        alignment: widget.alignment,
        padding: widget.padding,
        margin: widget.margin,
        child: widget.childWidget(),
      ),
    );
  }

  ///点击事件
  void onCardButtonClick(CardButton? cardButton) {
    if (cardButton == null) {
      return;
    }
    if (null == cardButton.type) {
      return;
    }
    switch (cardButton.type) {
      default:
      //throw new CardException("No corresponding type was found");
    }
  }
}
