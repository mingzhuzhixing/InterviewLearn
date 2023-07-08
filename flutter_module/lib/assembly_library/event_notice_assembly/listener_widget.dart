import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * 使用Listener来监听原始触摸事件，按照本书对组件的分类，则Listener也是一个功能性组件
 *
 * Listener({
 *  Key key,
 *  this.onPointerDown, //手指按下回调
 *  this.onPointerMove, //手指移动回调
 *  this.onPointerUp,//手指抬起回调
 *  this.onPointerCancel,//触摸事件取消回调
 *  this.behavior = HitTestBehavior.deferToChild, //先忽略此参数，后面小节会专门介绍
 *  Widget child
 * })
 */
class ListenerWidgetPage extends StatefulWidget {
  const ListenerWidgetPage({Key? key}) : super(key: key);

  @override
  State<ListenerWidgetPage> createState() => _ListenerWidgetPageState();
}

class _ListenerWidgetPageState extends State<ListenerWidgetPage> {
  PointerEvent? _event;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context,"Listener widget"),
      body: Listener(
        child: Container(
          alignment: Alignment.center,
          color: Colors.blue,
          width: 300.0,
          height: 150.0,
          child: Text(
            '${_event?.localPosition ?? ''}',
            style: TextStyle(color: Colors.white),
          ),
        ),
        onPointerDown: (PointerDownEvent event) => setState(() => _event = event),
        onPointerMove: (PointerMoveEvent event) => setState(() => _event = event),
        onPointerUp: (PointerUpEvent event) => setState(() => _event = event),
      ),
    );
  }
}
