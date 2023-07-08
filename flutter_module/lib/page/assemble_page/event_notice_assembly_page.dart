import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/event_notice_assembly/absorb_pointer_widget.dart';
import 'package:flutter_module/assembly_library/event_notice_assembly/gesture_detector_widget.dart';
import 'package:flutter_module/assembly_library/event_notice_assembly/listener_widget.dart';
import 'package:flutter_module/assembly_library/event_notice_assembly/notification_listener_widget.dart';
import 'package:flutter_module/assembly_library/third_assembly/html.dart';
import 'package:flutter_module/bean/item_entity.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/item_button.dart';
class EventNoticeAssemblyPage extends StatefulWidget {
  const EventNoticeAssemblyPage({Key? key}) : super(key: key);

  @override
  State<EventNoticeAssemblyPage> createState() => _EventNoticeAssemblyPageState();
}

class _EventNoticeAssemblyPageState extends State<EventNoticeAssemblyPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemButtonEntity> entityList = [
    ItemButtonEntity("AbsorbPointer_widget", AbsorbPointerWidgetPage()),
    ItemButtonEntity("GestureDetector_widget", GestureDetectorHomePage()),
    ItemButtonEntity("Listener_widget", ListenerWidgetPage()),
    ItemButtonEntity(" NotificationListener_widget", NotificationListenerHomePage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "事件处理与通知组件"),
      body: ListView.separated(
        itemBuilder: (BuildContext context, int index) {
          return ItemButton(entityList[index].title, entityList[index].widget ?? Spacer(),
              type: entityList[index].type ?? "", index: index);
        },
        separatorBuilder: (BuildContext context, int index) {
          return const Divider(height: 0.0, color: Colors.transparent);
        },
        itemCount: entityList.length,
      ),
    );
  }
}
