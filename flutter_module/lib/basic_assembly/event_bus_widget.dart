// ignore_for_file: slash_for_doc_comments

import 'dart:math';

import 'package:event_bus/event_bus.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

import 'event_bus/GCUserInfo.dart';

//EventBus

/**
 * 1、创建event_bus对象(发送端)
 *    final EventBus eventBus = EventBus();
 * 2、 监听相应的响应事件(接收端，可以放在initState()中)
 *    _event = eventBus.on<类型>().listen((event) {
 *        //接收数据并按需更新界面
 *        setState(() { });
 *    });
 * 3、销毁event_bus对象(接收端，放在dispose()中)
 *    _event.dispose();
 *
 * 4、发出事件(发送端)
 *   eventBus.fire(传递的数据)
 *
 *   https://www.jianshu.com/p/7b5e85d28751
 */
final EventBus eventBus = EventBus();

class EventBusWidgetPage extends StatefulWidget {
  const EventBusWidgetPage({Key? key}) : super(key: key);

  @override
  State<EventBusWidgetPage> createState() => _EventBusWidgetPageState();
}

class _EventBusWidgetPageState extends State<EventBusWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "EventBusWidget"),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextButton(
              onPressed: () {
                final userInfo = GCUserInfo("Steven", Random().nextInt(100));
                //发出事件
                eventBus.fire(userInfo);
              },
              child: const Text("按钮", style: TextStyle(fontSize: 18)),
            ),
            GCText()
          ],
        ),
      ),
    );
  }
}

class GCText extends StatefulWidget {
  const GCText({Key? key}) : super(key: key);

  @override
  State<GCText> createState() => _GCTextState();
}

class _GCTextState extends State<GCText> {
  String info = "Hello world!";
  late final _event;

  @override
  void initState() {
    super.initState();
    //3.监听相应类型的事件
    _event = eventBus.on<GCUserInfo>().listen((event) {
      setState(() {
        info = "${event.name}-----${event.age}";
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Text(
      info,
      style: const TextStyle(fontSize: 18),
    );
  }

  @override
  void dispose() {
    super.dispose();
    _event.dispose();
  }
}
