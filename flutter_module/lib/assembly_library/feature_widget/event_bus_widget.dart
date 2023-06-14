import 'package:flutter/material.dart';
import 'package:flutter_module/utils/eventbus_utils.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';

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
// final EventBus eventBus = EventBus();

//传递的对象
class GCUserInfo {
  GCUserInfo(this.name, this.age);
  final String name;
  final int age;
}

class EventBusWidgetPage extends StatefulWidget {
  const EventBusWidgetPage({Key? key}) : super(key: key);

  @override
  State<EventBusWidgetPage> createState() => _EventBusWidgetPageState();
}

class _EventBusWidgetPageState extends State<EventBusWidgetPage> {
  String info = "Hello world!";

  @override
  void initState() {
    super.initState();
    EventBusUtils.eventBus.on<GCUserInfo>().listen((event) {
      setState(() {
        info = "${event.name}-----${event.age}";
        print("zm1234:" + info);
        Fluttertoast.showToast(
            msg: "${event.name}:${event.age}", toastLength: Toast.LENGTH_SHORT);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "EventBusWidget"),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(info, style: const TextStyle(fontSize: 18)),
            TextButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (BuildContext context) {
                  return EventBusSecondPage();
                }));
              },
              child: const Text("跳转第二页", style: TextStyle(fontSize: 18)),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    super.dispose();
    print("zm1234 dispose()");
    EventBusUtils.onDestroy();
  }
}

/**
 * EventBus 第二页
 */
class EventBusSecondPage extends StatefulWidget {
  const EventBusSecondPage({Key? key}) : super(key: key);

  @override
  State<EventBusSecondPage> createState() => _EventBusSecondPageState();
}

class _EventBusSecondPageState extends State<EventBusSecondPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "EventBus 第二页"),
      body: Container(
        width: 1.sw,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            TextButton(
              onPressed: () {
                EventBusUtils.eventBus.fire(GCUserInfo("Steven", 100));
                Navigator.pop(context);
              },
              child: Text("EventBus 开始发送消息", style: TextStyle(fontSize: 18)),
            ),
          ],
        ),
      ),
    );
  }
}
