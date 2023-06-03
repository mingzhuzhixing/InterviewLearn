import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

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
  final _textFieldController = new TextEditingController();
  var _storageString = "";
  final STORAGE_KEY = 'storage_key';

  /*
   * 利用SharedPreferences存储数据
   */
  Future saveString() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    sharedPreferences.setString(STORAGE_KEY, _textFieldController.value.text.toString());
  }

  /*
   * 获取存在SharedPreferences中的数据
   */
  Future getString() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    setState(() {
      _storageString = sharedPreferences.get(STORAGE_KEY).toString();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("SharedPreferences 数据存储"),
      ),
      body: Column(
        children: <Widget>[
          const Text(
            "shared_preferences 存储",
            textAlign: TextAlign.center,
          ),
          TextField(
            keyboardType: TextInputType.text,
            controller: _textFieldController,
          ),
          MaterialButton(
            onPressed: () {
              saveString();
            },
            child: const Text("存储"),
            color: Colors.pink,
          ),
          MaterialButton(
            onPressed: () {
              getString();
            },
            child: const Text("获取"),
            color: Colors.lightGreen,
          ),
          Text("shared_preferences存储的值为：$_storageString")
        ],
      ),
    );
  }
}
