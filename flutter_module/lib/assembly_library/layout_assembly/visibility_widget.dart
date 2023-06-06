import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';

/**
 *
 * Offstage与Visibility比较:
    Offstage是控制组件隐藏/可见的组件，如果感觉有些单调功能不全，我们可以使用Visibility，
    Visibility也是控制子组件隐藏/可见的组件。不同是的Visibility有隐藏状态是否留有空间、隐藏状态下是否可调用等功能。
 */
class VisibilityWidgetPage extends StatefulWidget {
  const VisibilityWidgetPage({Key? key}) : super(key: key);

  @override
  State<VisibilityWidgetPage> createState() => _VisibilityWidgetPageState();
}

class _VisibilityWidgetPageState extends State<VisibilityWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Visibility Widget"),
      body: Column(
        children: [
          //显示蓝色的盒子消失 默认true显示
          Visibility(
            child: Container(
              height: 100,
              width: 100,
              color: Colors.blue,
            ),
          ),
          SizedBox(height: 10),

          //此时蓝色的盒子消失
          Visibility(
            visible: false,
            child: Container(
              height: 100,
              width: 100,
              color: Colors.blue,
            ),
          ),
          Container(
            width: 100,
            color: Colors.red,
            child: Text("标记位置"),
          ),
          SizedBox(height: 10),

          //replacement参数表示隐藏情况下显示的组件
          Visibility(
            visible: true,
            replacement: Container(
              height: 50,
              width: 50,
              color: Colors.red,
            ),
            child: Container(
              height: 100,
              width: 100,
              color: Colors.blue,
            ),
          ),
          SizedBox(height: 10),
          Visibility(
            visible: false,
            replacement: Container(
              height: 50,
              width: 50,
              color: Colors.red,
            ),
            child: Container(
              height: 100,
              width: 100,
              color: Colors.blue,
            ),
          )
        ],
      ),
    );
  }
}

/*
 * Visibility({
    Key key,
    @required this.child,
    this.replacement = const SizedBox.shrink(),//不可见时显示的组件（当maintainState=false）
    this.visible = true,//子组件是否可见，默认true（可见）
    this.maintainState = false,//不可见时是否维持状态，默认为false
    this.maintainAnimation = false,//不可见时，是否维持子组件中的动画
    this.maintainSize = false,//不可见时是否留有空间
    this.maintainSemantics = false,//不可见时是否维持它的语义
    this.maintainInteractivity = false,//不可见时是否具有交互性
    })
 */
