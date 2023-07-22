import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';
/**
 * 自定义Toast
 */
class YsToast {
  static const Duration lengthShort = Duration(milliseconds: 2000);
  static const Duration lengthLong = Duration(milliseconds: 3000);

  BuildContext _context; //上下文
  String _text; //显示的文本
  Duration _duration; //持续的时长
  ToastGravity gravity;
  int uiStyle; // toast ui样式，可自定义

  YsToast._(this._context, this._text, this._duration, this.gravity, this.uiStyle);

  factory YsToast.makeText(BuildContext context, String text,
      {Duration duration = lengthLong,
      ToastGravity gravity = ToastGravity.BOTTOM,
      int uiStyle = 0}) {
    return YsToast._(context, text, duration, gravity, uiStyle);
  }

  show() {
    OverlayEntry entry = _buildBody();
    Overlay.of(_context).insert(entry);
    Future.delayed(_duration, () {
      entry.remove();
    });
  }

  OverlayEntry _buildBody() {
    return OverlayEntry(
      builder: (context) {
        return Positioned(
          //top值，可以改变这个值来改变toast在屏幕中的位置
          top: _topGravity(context),
          child: Container(
            alignment: Alignment.center,
            width: MediaQuery.of(context).size.width,
            padding: EdgeInsets.symmetric(horizontal: 10.w),
            child: AnimatedOpacity(
              opacity: 1.0, //目标透明度
              duration: Duration(milliseconds: 100),
              child: _buildToastWidget(),
            ),
          ),
        );
      },
    );
  }

  double _topGravity(BuildContext context) {
    if (1 == uiStyle) {
      return MediaQuery.of(context).size.height * 0.45;
    } else if (gravity == ToastGravity.TOP) {
      return MediaQuery.of(context).size.height * 1;
    } else if (gravity == ToastGravity.CENTER) {
      return MediaQuery.of(context).size.height * 0.45;
    } else {
      return MediaQuery.of(context).size.height * 0.9;
    }
  }

  //toast ui 绘制
  _buildToastWidget() {
    if (uiStyle == 1) {
      return Card(
        color: Colors.black,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.w)),
        child: SizedBox(
          width: 105.w,
          height: 105.w,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(
                "assets/images/succeed_icon.png",
                width: 37.w,
                height: 37.w,
              ),
              SizedBox(height: 5.w),
              Text(
                _text,
                style: TextStyle(fontSize: 15.sp, color: Colors.white),
                textAlign: TextAlign.center,
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
              )
            ],
          ),
        ),
      );
    } else {
      return Card(
        color: Colors.black,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(75.w)),
        child: Padding(
          padding: EdgeInsets.only(left: 15.w, top: 12.w, right: 15.w, bottom: 12.w),
          child: Text(
            _text,
            style: TextStyle(fontSize: 15.sp, color: Colors.white),
            textAlign: TextAlign.center,
          ),
        ),
      );
    }
  }
}
