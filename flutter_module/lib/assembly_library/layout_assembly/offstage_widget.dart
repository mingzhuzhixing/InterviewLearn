import 'package:flutter/material.dart';

/// offstage的布局行为完全取决于offstate参数,offstage默认为true,不显示;
///
/// 当offstage为true,child不会绘制到屏幕上,不会响应点击事件,也不会占用空间;
/// 当offstage为false,child绘制到屏幕上;
/// 注意,当offstage不可见,如果child有动画,应该手动停止动画,offstage不会停止动画;
class OffstageWidgetPage extends StatefulWidget {
  const OffstageWidgetPage({Key? key}) : super(key: key);

  @override
  State<OffstageWidgetPage> createState() => _OffstageWidgetPageState();
}

class _OffstageWidgetPageState extends State<OffstageWidgetPage> {
  bool _offStage = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("offstage widget"),
      ),
      body: Container(
        color: Colors.white,
        child: Column(
          children: <Widget>[_firstWidget(), _secondWidget()],
        ),
      ),
    );
  }

  _firstWidget() {
    return FlatButton(
      onPressed: () {
        setState(() {
          _offStage = !_offStage;
        });
      },
      child: Text('$_offStage'),
      color: Colors.green,
    );
  }

  _secondWidget() {
    return Offstage(
      offstage: _offStage,
      child: Container(
        width: 50.0,
        height: 50.0,
        color: Colors.blueAccent,
      ),
    );
  }
}
