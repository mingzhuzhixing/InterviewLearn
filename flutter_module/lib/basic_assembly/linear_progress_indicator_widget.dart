// ignore_for_file: slash_for_doc_comments

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';

/**
 * 1、LinearProgressIndicator是ProgressIndicator的子类。它会创建水平进度条指示器，但是如果将其放置在旋转90度的RotatedBox中，也可以创建垂直进度条指示器。
 * 2、LinearProgressIndicator分为两种类型：
    第一种类型：Determinate
    确定类型(Determinate)：一个进度指示器，根据value属性的值(在0和1的范围内)向用户显示完成的工作百分比。
    第二种类型：Indeterminate
    不确定类型(Determinate)：进度指示器，既不能标识完成的工作百分比，也不能表示结束时间。LinearProgressIndicator构造函数：

    https://www.yiibai.com/flutter/flutter-linearprogressindicator.html
 */
class LinearProgressIndicatorWidgetPage extends StatefulWidget {
  const LinearProgressIndicatorWidgetPage({Key? key}) : super(key: key);

  @override
  State<LinearProgressIndicatorWidgetPage> createState() =>
      _LinearProgressIndicatorWidgetPageState();
}

class _LinearProgressIndicatorWidgetPageState extends State<LinearProgressIndicatorWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBarUtils.appBar("LinearProgressIndicator widget"),
      body: Column(
        children: [
          const SizedBox(height: 20),
          LinearProgressIndicator1(),
          const SizedBox(height: 20),
          LinearProgressIndicator2(),
          const SizedBox(height: 20),
          LinearProgressIndicator3(),
          const SizedBox(height: 20),
          LinearProgressIndicator4(),
          const SizedBox(height: 20),
          LinearProgressIndicator5()
        ],
      ),
    );
  }

  Widget LinearProgressIndicator1() {
    return LinearProgressIndicator(
      backgroundColor: Colors.cyan[100],
      valueColor: AlwaysStoppedAnimation<Color>(Colors.green),
    );
  }

  Widget LinearProgressIndicator2() {
    return SizedBox(
      width: 250,
      height: 20,
      child: LinearProgressIndicator(
        backgroundColor: Colors.cyan[100],
        valueColor: AlwaysStoppedAnimation<Color>(Colors.green),
      ),
    );
  }

  Widget LinearProgressIndicator3() {
    return SizedBox(
      width: 250,
      height: 20,
      child: LinearProgressIndicator(
        value: 0.3,
        backgroundColor: Colors.cyan[100],
        valueColor: AlwaysStoppedAnimation<Color>(Colors.green),
      ),
    );
  }

  Widget LinearProgressIndicator4() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        SizedBox(
          width: 250,
          height: 20,
          child: LinearProgressIndicator(
            value: _value,
            backgroundColor: Colors.cyan[100],
            valueColor: AlwaysStoppedAnimation<Color>(Colors.green),
          ),
        ),
        const SizedBox(width: 10, height: 10),
        Text(
          "Percent: " + (_value * 100).round().toString() + "%",
          style: const TextStyle(fontSize: 20),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              child: const Text("Start"),
              onPressed: this._working
                  ? null
                  : () {
                      startWorking();
                    },
            ),
            SizedBox(width: 10),
            ElevatedButton(
              child: const Text("Stop"),
              onPressed: !_working
                  ? null
                  : () {
                      stopWorking();
                    },
            )
          ],
        )
      ],
    );
  }

  double _value = 0;
  bool _working = false;

  void startWorking() async {
    setState(() {
      _working = true;
      _value = 0;
    });
    for (int i = 0; i < 10; i++) {
      if (!_working) {
        break;
      }
      await Future.delayed(Duration(seconds: 1));
      setState(() {
        _value += 0.1;
      });
    }
    setState(() {
      _working = false;
    });
  }

  void stopWorking() {
    setState(() {
      _working = false;
    });
  }

  /**
   * QuarterTurns = 1表示顺时针旋转90度。quarterTurns = -1表示逆时针旋转90度。
   */
  Widget LinearProgressIndicator5() {
    return RotatedBox(
      quarterTurns: -1,
      child: SizedBox(
        width: 250,
        height: 25,
        child: LinearProgressIndicator(
          backgroundColor: Colors.cyan[100],
          valueColor: AlwaysStoppedAnimation<Color>(Colors.green),
        ),
      ),
    );
  }
}
