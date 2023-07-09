// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/widget/ys_image_style.dart';
import 'package:power_image/power_image.dart';

class YsImageLoad extends StatelessWidget {
  String src;
  YsImageStyle? imageStyle;
  bool isOval;
  double radius;
  double topLeft;
  double topRight;
  double bottomLeft;
  double bottomRight;
  String? placeholder;
  String? errorholder;

  YsImageLoad(this.src,
      {this.imageStyle,
      this.isOval = false,
      this.radius = 0,
      this.topLeft = 0,
      this.topRight = 0,
      this.bottomLeft = 0,
      this.bottomRight = 0,
      this.placeholder,
      this.errorholder});

  @override
  Widget build(BuildContext context) {
    if (src.isEmpty) {
      return SizedBox();
    }
    if (src.startsWith("http")) {
      if (isOval) {
        return ClipOval(child: powerImageNetwork());
      } else if (radius > 0) {
        return ClipRRect(child: powerImageNetwork(), borderRadius: BorderRadius.circular(radius));
      } else if (topLeft > 0 || topRight > 0 || bottomLeft > 0 || bottomRight > 0) {
        return ClipRRect(
          child: powerImageNetwork(),
          borderRadius: onlyRadius(topLeft, topRight, bottomLeft, bottomRight),
        );
      } else {
        return powerImageNetwork();
      }
    } else if (src.contains("assets/images")) {
      if (isOval) {
        return ClipOval(child: loadImageAsset());
      } else if (radius > 0) {
        return ClipRRect(child: loadImageAsset(), borderRadius: BorderRadius.circular(radius));
      } else if (topLeft > 0 || topRight > 0 || bottomLeft > 0 || bottomRight > 0) {
        return ClipRRect(
          child: loadImageAsset(),
          borderRadius: onlyRadius(topLeft, topRight, bottomLeft, bottomRight),
        );
      } else {
        return loadImageAsset();
      }
    } else {
      if (isOval) {
        return ClipOval(child: powerImageAsset());
      } else if (radius > 0) {
        return ClipRRect(child: powerImageAsset(), borderRadius: BorderRadius.circular(radius));
      } else if (topLeft > 0 || topRight > 0 || bottomLeft > 0 || bottomRight > 0) {
        return ClipRRect(
          child: powerImageAsset(),
          borderRadius: onlyRadius(topLeft, topRight, bottomLeft, bottomRight),
        );
      } else {
        return powerImageAsset();
      }
    }
  }

  /**
   * Image 加载asset
   */
  Image loadImageAsset() {
    return Image.asset(
      src,
      width: imageStyle?.width,
      height: imageStyle?.height,
      fit: imageStyle?.fit ?? BoxFit.cover,
      frameBuilder: (BuildContext context, Widget child, int? frame, bool wasSynchronouslyLoaded) {
        //frame：如果图片还在加载中的话为null
        //wasSynchronouslyLoaded 布尔值，图片加载完成后为true
        return _placeHodler(child, frame, wasSynchronouslyLoaded);
      },
      errorBuilder: (BuildContext context, Object error, StackTrace? stackTrace) {
        return _errorHolder();
      },
    );
  }

  /**
   * PowerImage 加载nativeAsset
   */
  PowerImage powerImageAsset() {
    return PowerImage.nativeAsset(
      src,
      width: imageStyle?.width,
      height: imageStyle?.height,
      fit: imageStyle?.fit ?? BoxFit.cover,
      frameBuilder: (BuildContext context, Widget child, int? frame, bool wasSynchronouslyLoaded) {
        //wasSynchronouslyLoaded 布尔值，图片加载完成后为true
        return _placeHodler(child, frame, wasSynchronouslyLoaded);
      },
      errorBuilder: (BuildContext context, Object error, StackTrace? stackTrace) {
        return _errorHolder();
      },
    );
  }

  /**
   * PowerImage 加载network
   */
  Widget powerImageNetwork() {
    // PowerImage.network 需要注册
    // return PowerImage.network(
    //   src,
    //   width: imageStyle?.width,
    //   height: imageStyle?.height,
    //   fit: imageStyle?.fit ?? BoxFit.cover,
    //   frameBuilder: (BuildContext context, Widget child, int? frame, bool wasSynchronouslyLoaded) {
    //     //wasSynchronouslyLoaded 布尔值，图片加载完成后为true
    //     return _placeHodler(child,frame, wasSynchronouslyLoaded);
    //   },
    //   errorBuilder: (BuildContext context, Object error, StackTrace? stackTrace) {
    //     return _errorHolder();
    //   },
    // );
    return Image.network(
      src,
      width: imageStyle?.width,
      height: imageStyle?.height,
      fit: imageStyle?.fit ?? BoxFit.cover,
      frameBuilder: (BuildContext context, Widget child, int? frame, bool wasSynchronouslyLoaded) {
        //wasSynchronouslyLoaded 布尔值，图片加载完成后为true
        return _placeHodler(child, frame, wasSynchronouslyLoaded);
      },
      errorBuilder: (BuildContext context, Object error, StackTrace? stackTrace) {
        return _errorHolder();
      },
    );
  }

  /**
   * 占位图
   */
  Widget _placeHodler(Widget child, int? frame, bool wasSynchronouslyLoaded) {
    print(
        "zm1234 _placeHodler:$placeholder wasSynchronouslyLoaded:$wasSynchronouslyLoaded frame：$frame child：$child");
    if (wasSynchronouslyLoaded) {
      return child;
    } else if (frame == null || frame == 0) {
      return child;
    }
    if (placeholder != null && (placeholder?.isNotEmpty ?? false)) {
      return Image.asset(
        placeholder!,
        width: imageStyle?.width,
        height: imageStyle?.height,
        fit: imageStyle?.fit ?? BoxFit.cover,
      );
    } else {
      return SizedBox(width: imageStyle?.width, height: imageStyle?.height);
    }
  }

  /**
   * 图片加载失败占位图片
   */
  Widget _errorHolder() {
    print("zm1234 _errorHolder:$errorholder");
    if (errorholder != null && (errorholder?.isNotEmpty ?? false)) {
      return Image.asset(
        errorholder!,
        width: imageStyle?.width,
        height: imageStyle?.height,
        fit: imageStyle?.fit ?? BoxFit.cover,
      );
    } else {
      return SizedBox(width: imageStyle?.width, height: imageStyle?.height);
    }
  }

  /**
   * 图片圆角
   */
  BorderRadius onlyRadius(double topLeft, double topRight, double bottomLeft, double bottomRight) {
    return BorderRadius.only(
      topLeft: Radius.circular(topLeft),
      topRight: Radius.circular(topRight),
      bottomLeft: Radius.circular(bottomLeft),
      bottomRight: Radius.circular(bottomRight),
    );
  }
}
