import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';

/**
 * 当我们需要一个控件的尺寸是相对尺寸时，比如当前按钮的宽度占父组件的70%，可以使用FractionallySizedBox来实现此效果。
    使用FractionallySizedBox包裹子控件，设置widthFactor宽度系数或者heightFactor高度系数，系数值的范围是0-1，0.7表示占父组件的70%，
 */
class FractionallySizedBoxWidgetPage extends StatefulWidget {
  const FractionallySizedBoxWidgetPage({Key? key}) : super(key: key);

  @override
  State<FractionallySizedBoxWidgetPage> createState() => _FractionallySizedBoxWidgetPageState();
}

class _FractionallySizedBoxWidgetPageState extends State<FractionallySizedBoxWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "FractionallySizedBox Widget"),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          FractionallySizedBox(
            widthFactor: .5,
            child: RaisedButton(
              onPressed: () {  },
              child: Text('button'),
            ),
          ),

          //通过alignment参数控制子组件显示的位置，默认为center
          FractionallySizedBox(
            widthFactor: .7,
            alignment: Alignment.center,
            child: RaisedButton(
              onPressed: () {  },
              child: Text('button'),
            ),
          ),

          //如果想让2个控件之间的间隔是当前父控件的10%，可以使用无子控件的FractionallySizedBox，
          Container(
            height: 200,
            color: Colors.grey,
            child: Column(
              children: <Widget>[
                Container(
                  height: 50,
                  color: Colors.red,
                ),
                Flexible(
                  child: FractionallySizedBox(
                    heightFactor: .1,
                  ),
                ),
                Container(
                  height: 50,
                  color: Colors.blue,
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
