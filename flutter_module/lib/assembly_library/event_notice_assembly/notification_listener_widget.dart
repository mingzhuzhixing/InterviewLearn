// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:flutter_module/base/base_widget.dart';
import 'package:flutter_module/widget/item_button.dart';

class NotificationListenerHomePage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return Column(
      children: [
        ItemButton("监听列表通知", NotificationListenerPage(), index: 0),
        ItemButton("指定监听通知类型", SimpleNotificationListenerPage(), index: 0),
      ],
    );
  }

  @override
  String title() {
    return "监听通知";
  }
}

/**
 * 监听列表通知
 */
class NotificationListenerPage extends BaseTitleBarWidget {
  @override
  Widget childWidget() {
    return NotificationListener(
      onNotification: (notification) {
        switch (notification.runtimeType) {
          case ScrollStartNotification:
            print("开始滚动");
            break;
          case ScrollUpdateNotification:
            print("正在滚动");
            break;
          case ScrollEndNotification:
            print("滚动停止");
            break;
          case OverscrollNotification:
            print("滚动到边界");
            break;
        }
        return false;
      },
      child: ListView.builder(
        itemCount: 100,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text("$index"),
          );
        },
      ),
    );
  }

  @override
  String title() {
    return "监听列表通知";
  }
}

/**
 * 指定监听通知类型
 */
class SimpleNotificationListenerPage extends BaseTitleBarWidget{
  @override
  Widget childWidget() {
    //指定监听通知的类型为滚动结束通知(ScrollEndNotification)
    return NotificationListener<ScrollEndNotification>(
      onNotification: (notification){
        //只会在滚动结束时才会触发此回调
        print("滚动 $notification");
        return false;
      },
      child: ListView.builder(
          itemCount: 50,
          itemBuilder: (context, index) {
            return ListTile(title: Text("$index"),);
          }
      ),
    );
  }

  @override
  String title() {
    return "指定监听通知类型 滚动结束/停止";
  }
}
