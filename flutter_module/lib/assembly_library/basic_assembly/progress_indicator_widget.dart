import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/basic_assembly/circular_progress_indicator_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/linear_progress_indicator_widget.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 * 进度指示器
 */
class ProgressIndicatorWidget extends StatefulWidget {
  const ProgressIndicatorWidget({Key? key}) : super(key: key);

  @override
  State<ProgressIndicatorWidget> createState() => _ProgressIndicatorWidgetState();
}

class _ProgressIndicatorWidgetState extends State<ProgressIndicatorWidget> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "进度指示器"),
      body: Container(
        width: 1.sw,
        child: Column(
          mainAxisSize:MainAxisSize.max,
          children: [
            ItemButton("线性进度条", LinearProgressIndicatorWidgetPage()),
            ItemButton("圆形进度条", CircularProgressIndicatorWidgetPage()),
          ],
        ),
      ),
    );
  }
}
