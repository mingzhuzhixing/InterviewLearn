import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * FutureBuilder({
 *   this.future, //FutureBuilder依赖的Future，通常是一个异步耗时任务
 *   this.initialData, //初始数据，用户设置默认数据。
 *   required this.builder, //Widget构建器；该构建器会在Future执行的不同阶段被多次调用，
 * })
 *
 * enum ConnectionState {
 *  /// 当前没有异步任务，比如[FutureBuilder]的[future]为null时
 *  none,
 *
 *  /// 异步任务处于等待状态
 *  waiting,
 *
 *  /// Stream处于激活状态（流上已经有数据传递了），对于FutureBuilder没有该状态。
 *  active,
 *
 *  /// 异步任务已经终止.
 *  done,
 * }
 */
class FutureBuilderWidgetPage extends StatefulWidget {
  const FutureBuilderWidgetPage({Key? key}) : super(key: key);

  @override
  State<FutureBuilderWidgetPage> createState() => _FutureBuilderWidgetPageState();
}

class _FutureBuilderWidgetPageState extends State<FutureBuilderWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "FutureBuilder widget"),
      body: Center(
        child: FutureBuilder<String>(
          future: mockNetworkData(),
          builder: (BuildContext context, AsyncSnapshot snapshot) {
            // 请求已结束
            if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.hasError) {
                // 请求失败，显示错误
                return Text("Error: ${snapshot.error}");
              } else {
                // 请求成功，显示数据
                return Text("Contents: ${snapshot.data}");
              }
            } else {
              // 请求未结束，显示loading
              return CircularProgressIndicator();
            }
          },
        ),
      ),
    );
  }

  ///mock 数据，模拟网络请求
  Future<String> mockNetworkData() async {
    return Future.delayed(Duration(seconds: 2), () => "我是从互联网上获取的数据");
  }
}
