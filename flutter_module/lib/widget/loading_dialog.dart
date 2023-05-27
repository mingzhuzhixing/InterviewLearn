import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:lottie/lottie.dart';

/// 自定义 loading
class LoadingDialog {
  ///显示loading
  static Future<void> showLoading(BuildContext context) {
    return showDialog<void>(
        context: context,
        barrierDismissible: false,
        barrierColor: Colors.transparent,
        builder: (context) {
          return Center(
            child: Material(
              ///背景透明
              color: Colors.transparent,

              ///保证控件居中效果
              child: Container(
                color: Colors.transparent,
                alignment: Alignment.center,
                width: double.infinity,
                height: double.infinity,
                child: Lottie.asset("assets/loading.json", width: 38.w, height: 40.h),
              ),
            ),
          );
        });
  }

  ///隐藏loading
  static void hideLoading(BuildContext context) {
    Navigator.of(context, rootNavigator: true).pop();
  }
}
