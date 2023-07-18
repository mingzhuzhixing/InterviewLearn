import 'package:flutter/material.dart';
import 'package:flutter_module/utils/sp_utils.dart';
import 'package:fluttertoast/fluttertoast.dart';

///SharedPreferences数据存储

///在Flutter中本身并没有内置SharedPreferences存储，但是官方给我们提供了第三方的组件来实现这一存储方式。我们可以通过pubspec.yaml文件引入 ,网址：https://pub.dartlang.org/flutter
///  shared_preferences: ^0.5.0
///SharedPreferences中为我们提供了String、bool、Double、Int、StringList数据类型的存取
///
class SpStorageWidgetPage extends StatefulWidget {
  const SpStorageWidgetPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _StoragePage();
  }
}

class _StoragePage extends State<SpStorageWidgetPage> {
  var value1;
  var value2;
  var value3;
  var value4;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("SharedPreferences 数据存储"),
      ),
      body: Column(
        children: <Widget>[
          MaterialButton(
            onPressed: () {
              SpUtils.getInstance().save("key1", "hhahahahah");
              SpUtils.getInstance().save("key2", 123);
              SpUtils.getInstance().save("key3", false);
              SpUtils.getInstance().save("key4", 11.1);
              Fluttertoast.showToast(msg: "存储完成");
            },
            child: const Text("开始存储"),
            color: Colors.pink,
          ),
          MaterialButton(
            onPressed: () {
              setState(() {
                value1 = SpUtils.getInstance().get("key1");
                value2 = SpUtils.getInstance().get("key2");
                value3 = SpUtils.getInstance().get("key3");
                value4 = SpUtils.getInstance().get("key4");
              });
            },
            child: const Text("获取"),
            color: Colors.lightGreen,
          ),
          Text("shared_preferences存储的值为：\n$value1\n$value2\n$value3\n$value4")
        ],
      ),
    );
  }
}
