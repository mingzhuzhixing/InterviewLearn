import 'package:flutter/material.dart';

// ignore_for_file: slash_for_doc_comments
///Container
/**
 * Container(“容器”)是用于包含子窗口小部件的框。同时可以通过填充，边距，对齐方式等属性来设置其样式。
 * 创建容器时涉及很多参数，例如宽度，高度，子代，对齐方式等。此外，它受父窗口小部件约束的影响，因此布局行为相对复杂。
 *
 * https://www.yiibai.com/flutter/flutter-container.html
 */
class ContainerWidgetPage extends StatelessWidget {
  const ContainerWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("widget container"),
        ),
        body: ListView(
          children: [
            container1(),
            container2(),
            container3(),
            container4(),
            container5(),
            container6(),
            container7(),
          ],
        ),
      ),
    );
  }

  /**
   * 如果未指定子级，宽度，高度和约束参数，并且父级窗口小部件未绑定，
   */
  Widget container1() {
    return Container(
      color: Colors.greenAccent[100],
      padding: EdgeInsets.fromLTRB(10, 5, 50, 5),
      width: 100,
      height: 200,
    );
  }

  /**
   * 如果未指定子级，宽度，高度和约束参数(或为null)，并且父级窗口小部件未绑定，则Container将尝试确定其大小尽可能小。
   * 示例：未指定具有：width，height，child和constraints参数的Container，并且它是Row的子类，它将尝试确定其水平尺寸尽可能小。
   * 注意：“Row”是一个垂直限制的小部件，但水平方向是无限制的。
   */
  Widget container2() {
    return Row(
      children: [
        Container(
          color: Colors.greenAccent[100],
          padding: EdgeInsets.fromLTRB(50, 30, 50, 30),
          margin: EdgeInsets.fromLTRB(30, 55, 50, 70),
        )
      ],
    );
  }

  /**
   * 如果未指定child, width, height 和 constraints 参数(或为null)，并且父级窗口小部件是有界的，则Container将尝试将其大小设置为尽可能大。
   * 示例：未指定带有child, width, height 和 constraints参数的Container，并且它是Center的子项，那么Container的大小将尽可能大。
   */
  Widget container3() {
    return Container(
      color: Colors.greenAccent[100],
      padding: EdgeInsets.fromLTRB(50, 30, 50, 30),
      margin: EdgeInsets.fromLTRB(30, 55, 50, 70),
    );
  }

  /**
   * 如果指定了child，但未指定width, height，constraints和alignment参数，则Container将尽可能小，并符合父窗口小部件的约束。
   */
  Widget container4() {
    return Container(
      color: Colors.greenAccent[100],
      padding: EdgeInsets.fromLTRB(50, 30, 50, 30),
      margin: EdgeInsets.fromLTRB(30, 55, 50, 70),
      child: ElevatedButton(
        child: Text("Button"),
        onPressed: () {},
      ),
    );
  }

  /**
   * decoration属性用于在“容器”背景之上和子项后面绘制一些东西。如果要在容器的背景上(以及在子对象的后面)绘制颜色，则最好使用color属性。
   */
  Widget container5() {
    return Container(
      alignment: Alignment.center,
      decoration: BoxDecoration(
        color: const Color(0xff7c94b6),
        image: const DecorationImage(
          image: NetworkImage('https://s3.o7planning.com/images/tom-and-jerry.png'),
          fit: BoxFit.cover,
        ),
        border: Border.all(color: Colors.black, width: 8),
        borderRadius: BorderRadius.circular(12),
      ),
      margin: EdgeInsets.all(30),
      child: ElevatedButton(
        child: Text("I am a Long Button"),
        onPressed: () {},
      ),
    );
  }

  /**
   * foregroundDecoration属性用于在 Container 的背景上和子元素的前面绘制一些东西。它可以遮盖并隐藏子元素，但是仍然可以与子元素交互互动。
   */
  Widget container6() {
    return Container(
      alignment: Alignment.center,
      foregroundDecoration: BoxDecoration(
        image: const DecorationImage(
          image: NetworkImage('https://s3.o7planning.com/images/smile-64.png'),
          fit: BoxFit.none,
        ),
        border: Border.all(color: Colors.black, width: 8),
        borderRadius: BorderRadius.circular(12),
      ),
      margin: EdgeInsets.all(30),
      child: ElevatedButton(
        child: Text("I am a Long Button"),
        onPressed: () {},
      ),
    );
  }

  /**
   * constraints属性用于向容器添加额外的约束。
   */
  Widget container7() {
    return Container(
      color: Colors.greenAccent[100],
      padding: EdgeInsets.all(30),
      width: 200,
      height: 200,
      constraints: BoxConstraints(maxHeight: 80),
      child: ElevatedButton(
        child: Text("Button"),
        onPressed: () {},
      ),
    );
  }
}
