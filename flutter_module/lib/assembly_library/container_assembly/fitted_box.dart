import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

///空间适配 它根据指定的适合度缩放和定位子窗口小部件
class FittedBoxWidgetPage extends StatefulWidget {
  const FittedBoxWidgetPage({Key? key}) : super(key: key);

  @override
  State<FittedBoxWidgetPage> createState() => _FittedBoxWidgetPageState();
}

class _FittedBoxWidgetPageState extends State<FittedBoxWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "FittedBox Widget"),
      body: Column(
        children: [
          wRow("00000"),
          FittedBox(
            fit: BoxFit.cover,
            child: wRow("0000000 0000000000"),
          ),
          FittedBox(child: wRow(' 800 ')),
          LayoutBuilder(
            builder: (_, constraints) {
              return FittedBox(
                child: ConstrainedBox(
                  constraints: constraints.copyWith(
                    minWidth: constraints.maxWidth,
                    maxWidth: double.infinity,
                    //maxWidth: constraints.maxWidth
                  ),
                  child: wRow("000000 111111"),
                ),
              );
            },
          ),
          // wRow(' 90000000000000000 '),
          // SingleLineFittedBox(child: wRow(' 90000000000000000 ')),
          // wRow(' 800 '),
          // SingleLineFittedBox(child: wRow(' 800 ')),
        ],
      ),
    );
  }

  // 直接使用Row
  Widget wRow(String text) {
    Widget child = Text(text, style: TextStyle(fontSize: 22.sp));
    child = Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [child, child, child],
    );
    return child;
  }
}

/**
 * FittedBox({
 *   Key? key,
 *   this.fit = BoxFit.contain, // 适配方式
 *   this.alignment = Alignment.center, //对齐方式
 *   this.clipBehavior = Clip.none, //是否剪裁
 *   Widget? child,
 * })
 */
