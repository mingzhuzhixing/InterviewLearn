// @dart=2.9
// 必须在dart文件的第一行,可以加在任何dart文件中
import 'package:flutter/material.dart';
import 'package:flutter_module/oschinabody.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const OsChinaBody(),
    );
  }
}