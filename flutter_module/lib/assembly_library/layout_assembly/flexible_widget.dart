import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * FlexibleWidget
 * Expanded、Flexible和Spacer都是具有权重属性的组件，可以控制Row、Column、Flex的子控件如何布局的控件。
 *
 * 计算：子控件占比 = 当前子控件flex/所有子控件flex之和。
 *
 * Flexible中fit参数表示填满剩余空间的方式，说明如下：
 *   tight：必须（强制）填满剩余空间。
 *   loose：尽可能大的填满剩余空间，但是可以不填满。
 */
class FlexibleWidgetPage extends StatefulWidget {
  const FlexibleWidgetPage({Key? key}) : super(key: key);

  @override
  State<FlexibleWidgetPage> createState() => _FlexibleWidgetPageState();
}

class _FlexibleWidgetPageState extends State<FlexibleWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Flex Widget"),
      body: Column(
        children: [
          //Flexible组件可以控制Row、Column、Flex的子控件占满父控件，比如，Row中有3个子控件，2边的固定宽，中间的占满剩余的空间，
          Row(
            children: <Widget>[
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
              Flexible(
                  child: Container(
                color: Colors.red,
                height: 50,
              )),
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
            ],
          ),
          SizedBox(height: 20),

          //还是有3个子控件，希望第一个占1/6，第二个占2/6，第三个占3/6，
          Row(
            children: <Widget>[
              Flexible(
                flex: 1,
                child: Container(
                  color: Colors.blue,
                  alignment: Alignment.center,
                  child: Text(
                    '1/6 Flex',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
              ),
              Flexible(
                flex: 2,
                child: Container(
                  color: Colors.red,
                  alignment: Alignment.center,
                  child: Text(
                    '2 Flex/ 6 Total',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
              ),
              Flexible(
                flex: 3,
                child: Container(
                  color: Colors.green,
                  alignment: Alignment.center,
                  child: Text(
                    '3 Flex/ 6 Total',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
              ),
            ],
          ),
          SizedBox(height: 10),
          Row(
            children: <Widget>[
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
              Flexible(
                  child: Container(
                color: Colors.red,
                height: 50,
                child: Text(
                  'Container',
                  style: TextStyle(color: Colors.white),
                ),
              )),
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
            ],
          ),
          SizedBox(height: 10),

          //这段代码是在最上面代码的基础上给中间的红色Container添加了Text子控件，此时红色Container就不在充满空间，再给Container添加对齐方式，
          //Container默认是适配子控件大小的，但当设置对齐方式时Container将会填满父控件，在Flutter Widgets 之 Container中已经详细介绍，因此是否填满剩余空间取决于子控件是否需要填满父控件。
          Row(
            children: <Widget>[
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
              Flexible(
                  child: Container(
                color: Colors.red,
                height: 50,
                alignment: Alignment.center, //和上面代码不同之处
                child: Text(
                  'Container',
                  style: TextStyle(color: Colors.white),
                ),
              )),
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
            ],
          ),
          SizedBox(height: 10),

          //OutlineButton正常情况下是不充满父控件的，因此最终的效果应该是不填满剩余空间
          Row(
            children: <Widget>[
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
              Flexible(
                child: MaterialButton(
                  onPressed: () {},
                  child: Text('OutlineButton'),
                ),
              ),
              Container(
                color: Colors.blue,
                height: 50,
                width: 100,
              ),
            ],
          )
        ],
      ),
    );
  }
}
