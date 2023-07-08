// ignore_for_file: must_be_immutable, invalid_use_of_protected_member

import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

abstract class BaseTitleBarWidget extends StatefulWidget {
  BaseTitleBarWidget({Key? key}) : super(key: key);
  late _BaseTitleBarWidgetState state;

  @override
  State<BaseTitleBarWidget> createState() {
    return state = _BaseTitleBarWidgetState();
  }

  //子Widget
  Widget childWidget();

  //标题
  String title() {
    return "";
  }

  //自定义appbar样式
  AppBar? appBar() {
    return null;
  }

  //刷新状态
  void setState(VoidCallback fn) {
    state.setState(fn);
  }
}

class _BaseTitleBarWidgetState extends State<BaseTitleBarWidget> {
  PreferredSizeWidget? appBar;

  @override
  void initState() {
    super.initState();
    if (widget.title().isNotEmpty) {
      appBar = CommonAppBar(context, widget.title());
    } else if (widget.appBar() != null) {
      appBar = widget.appBar();
    } else {
      appBar = null;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appBar,
      body: widget.childWidget(),
    );
  }
}
