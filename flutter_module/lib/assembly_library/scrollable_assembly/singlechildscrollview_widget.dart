import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

/**
 *
 * SingleChildScrollView类似于Android中的ScrollView，它只能接收一个子组件
 *
 * 注意：通常SingleChildScrollView只应在期望的内容不会超过屏幕太多时使用，这是因为SingleChildScrollView不支持基于
 * Sliver 的延迟加载模型，所以如果预计视口可能包含超出屏幕尺寸太多的内容时，那么使用SingleChildScrollView将会非常昂贵（性能差），
 * 此时应该使用一些支持Sliver延迟加载的可滚动组件
 */
class SingleChildScrollViewWidgetPage extends StatefulWidget {
  const SingleChildScrollViewWidgetPage({Key? key}) : super(key: key);

  @override
  State<SingleChildScrollViewWidgetPage> createState() => _SingleChildScrollViewWidgetPageState();
}

class _SingleChildScrollViewWidgetPageState extends State<SingleChildScrollViewWidgetPage> {
  String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  final ScrollController _controller = ScrollController();
  bool showToTopBtn = false; //是否显示“返回到顶部”按钮

  @override
  void initState() {
    super.initState();
    //监听滚动事件，打印滚动位置
    _controller.addListener(() {
      print(_controller.offset); //打印滚动位置
      if (_controller.offset < 1000 && showToTopBtn) {
        setState(() {
          showToTopBtn = false;
        });
      } else if (_controller.offset >= 1000 && showToTopBtn == false) {
        setState(() {
          showToTopBtn = true;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "SingleChildScrollView Widget"),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(1.0),
        physics: BouncingScrollPhysics(),
        controller: _controller,
        child: Center(
          child: Column(
            //动态创建一个List<Widget>
            children: str
                .split("")
                //每一个字母都用一个Text显示,字体为原来的两倍
                .map(
                  (c) =>Column(
                    children: [
                      Text(
                        c,
                        textScaleFactor: 2.0,
                      ),
                      Divider(height: 2.w,color: Colors.red,)
                    ],
                  ),
                )
                .toList(),
          ),
        ),
      ),
      floatingActionButton: !showToTopBtn
          ? null
          : FloatingActionButton(
              child: Icon(Icons.arrow_upward),
              onPressed: () {
                //返回到顶部时执行动画
                _controller.animateTo(
                  .0,
                  duration: Duration(milliseconds: 200),
                  curve: Curves.ease,
                );
              }),
    );
  }

  @override
  void dispose() {
    //为了避免内存泄露，需要调用_controller.dispose
    _controller.dispose();
    super.dispose();
  }
}

/**
 * 参数说明：
 * axisDirection 滚动方向。
 *
 * physics：此属性接受一个ScrollPhysics类型的对象，它决定可滚动组件如何响应用户操作，比如用户滑动完抬起手指后，继续执行动画；
 *    或者滑动到边界时，如何显示。默认情况下，Flutter会根据具体平台分别使用不同的ScrollPhysics对象，应用不同的显示效果，
 *    如当滑动到边界时，继续拖动的话，在 iOS 上会出现弹性效果，而在 Android 上会出现微光效果。如果你想在所有平台下使用同一种效果，
 *    可以显式指定一个固定的ScrollPhysics，Flutter SDK中包含了两个ScrollPhysics的子类，他们可以直接使用：
 *      ClampingScrollPhysics：列表滑动到边界时将不能继续滑动，通常在Android 中 配合
 *      GlowingOverscrollIndicator（实现微光效果的组件） 使用。
 *      BouncingScrollPhysics：iOS 下弹性效果。
 *
 * controller：此属性接受一个ScrollController对象。ScrollController的主要作用是控制滚动位置和监听滚动事件。
 *    默认情况下，Widget树中会有一个默认的PrimaryScrollController，如果子树中的可滚动组件没有显式的指定controller，
 *    并且primary属性值为true时（默认就为true），可滚动组件会使用这个默认的PrimaryScrollController。
 *    这种机制带来的好处是父组件可以控制子树中可滚动组件的滚动行为，例如，Scaffold正是使用这种机制在iOS中实现了点击导航栏回到顶部的功能。
 *    我们将在6.4节详细介绍ScrollController。
 *
 * viewportBuilder：构建 Viewport 的回调。当用户滑动时，Scrollable 会调用此回调构建新的 Viewport，同时传递一个 ViewportOffset
 *    类型的 offset 参数，该参数描述 Viewport 应该显示那一部分内容。注意重新构建 Viewport 并不是一个昂贵的操作，因为 Viewport
 *    本身也是 Widget，只是配置信息，Viewport 变化时对应的 RenderViewport 会更新信息，并不会随着 Widget 进行重新构建。
 */
