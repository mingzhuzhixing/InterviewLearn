import 'dart:io';

import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:path_provider/path_provider.dart';

///文件存储

/*
 * 是Flutter本身都没有内置提到的这三种存储方式，不过好在官方给我们提供了三方的支持库，
 *   path_provider: ^0.5.0
 * 使用 path_provider 和 dart 的 io 模块。path_provider 负责查找 iOS/Android 的目录文件，IO 模块负责对文件进行读写
 *
 * 在path_provider中有三个获取文件路径的方法：
 *  1、getTemporaryDirectory()//获取应用缓存目录，等同IOS的NSTemporaryDirectory()和Android的getCacheDir() 方法
 *  2、getApplicationDocumentsDirectory()获取应用文件目录类似于Ios的NSDocumentDirectory和Android上的 AppData目录
 *  3、getExternalStorageDirectory()//这个是存储卡，仅仅在Android平台可以使用
 */

class FileStorageWidgetPage extends StatefulWidget {
  const FileStorageWidgetPage({Key? key}) : super(key: key);

  @override
  _MyStoragePageState createState() => _MyStoragePageState();
}

class _MyStoragePageState extends State<FileStorageWidgetPage> {
  final _textFieldController = TextEditingController();
  var _storageString = "";

  //文件名 常量
  final fileName = "file.text";

  /*
   * 利用文件存储数据
   */
  Future saveString() async {
    final file = await getFile(fileName);
    //写入字符串
    file.writeAsString(_textFieldController.value.text.toString());
    Fluttertoast.showToast(msg: "存储数据成功", toastLength: Toast.LENGTH_SHORT);
  }

  /*
   * 获取存在文件中的数据
   */
  Future getString() async {
    final file = await getFile(fileName);
    var filePath = file.path;
    setState(() {
      file.readAsString().then((String value) {
        _storageString = value + "\n文件存储路径:" + filePath;
      });
    });
  }

  /*
   * 初始化文件路径
   */
  Future<File> getFile(String fileName) async {
    //获取应用文件目录类似于Ios的NSDocumentDirectory和Android上的 AppData目录
    final fileDirectory = await getApplicationDocumentsDirectory();

    //获取存储路径
    final filePath = fileDirectory.path;

    //获取file对象（操作文件记得导入import 'dart:io'）
    return File(filePath + "/" + fileName);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("文件存储"),
      ),
      body: Column(
        children: <Widget>[
          const Text(
            "文件存储",
            textAlign: TextAlign.center,
          ),
          TextField(
            controller: _textFieldController,
          ),
          MaterialButton(
            onPressed: () {
              saveString();
              // Fluttertoast.showToast(
              //     msg: "保持数据成功",
              //     toastLength: Toast.LENGTH_SHORT,
              //     gravity: ToastGravity.CENTER,
              //     backgroundColor: Colors.red,
              //     textColor: Colors.white,
              //     fontSize: 20.0);
            },
            child: const Text("存储"),
            color: Colors.cyan,
          ),
          MaterialButton(
            onPressed: () {
              getString();
            },
            child: const Text("获取"),
            color: Colors.deepOrange,
          ),
          Text("从文件存储中获取的值为:$_storageString")
        ],
      ),
    );
  }
}
