import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * StreamBuilder({
 *  this.initialData,
 *  Stream<T> stream,
 *  required this.builder,
 * })
 */
class StreamBuilderWidgetPage extends StatefulWidget {
  const StreamBuilderWidgetPage({Key? key}) : super(key: key);

  @override
  State<StreamBuilderWidgetPage> createState() => _StreamBuilderWidgetPageState();
}

class _StreamBuilderWidgetPageState extends State<StreamBuilderWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "StreamBuilder widget"),
      body: StreamBuilder<int>(
        stream: counter(), //
        //initialData: ,// a Stream<int> or null
        builder: (BuildContext context, AsyncSnapshot<int> snapshot) {
          if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
          } else if (snapshot.connectionState == ConnectionState.none) {
            return Text('没有Stream');
          } else if (snapshot.connectionState == ConnectionState.waiting) {
            return Text('等待数据...');
          } else if (snapshot.connectionState == ConnectionState.active) {
            return Text('active: ${snapshot.data}');
          } else if (snapshot.connectionState == ConnectionState.done) {
            return Text('Stream 已关闭');
          } else {
            return SizedBox();
          }
        },
      ),
    );
  }

  Stream<int> counter() {
    return Stream.periodic(Duration(seconds: 1), (i) {
      return i;
    });
  }
}
