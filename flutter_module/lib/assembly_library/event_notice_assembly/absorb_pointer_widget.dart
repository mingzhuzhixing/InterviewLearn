import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * 忽略指针事件
 *
 *    假如我们不想让某个子树响应PointerEvent的话，我们可以使用IgnorePointer和AbsorbPointer，这两个组件都能阻止子树接收指针事件，
 * 不同之处在于AbsorbPointer本身会参与命中测试，而IgnorePointer本身不会参与，这就意味着AbsorbPointer本身是可以接收指针事件的(但其子树不行)，
 * 而IgnorePointer不可以
 */
class AbsorbPointerWidgetPage extends StatefulWidget {
  const AbsorbPointerWidgetPage({Key? key}) : super(key: key);

  @override
  State<AbsorbPointerWidgetPage> createState() => _AbsorbPointerWidgetPageState();
}

class _AbsorbPointerWidgetPageState extends State<AbsorbPointerWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Listener widget"),
      // 点击下面的Container时，由于它在AbsorbPointer的子树上，所以不会响应指针事件，所以日志不会输出"in"，
      // 但AbsorbPointer本身是可以接收指针事件的，所以会输出"up"。如果将AbsorbPointer换成IgnorePointer，那么两个都不会输出。
      body: Listener(
        child: AbsorbPointer(
          child: Listener(
            child: Container(
              color: Colors.red,
              width: 200.0,
              height: 100.0,
            ),
            onPointerDown: (event) => print("in"),
          ),
        ),
        onPointerDown: (event) => print("up"),
      ),
    );
  }
}
