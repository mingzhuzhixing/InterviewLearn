import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';

/// 数据库（Sqflite）存储

/*
 * Sqflite是一个同时支持Android跟Ios平台的数据库，并且支持标准的CURD操作
 * #添加Sqflite依赖  sqflite: ^1.0.0
 */
class SqfliteStorageWidgetPage extends StatefulWidget {
  const SqfliteStorageWidgetPage({Key? key}) : super(key: key);

  @override
  _MyStoragePageState createState() => _MyStoragePageState();
}

class _MyStoragePageState extends State<SqfliteStorageWidgetPage> {
  final _textFieldController = TextEditingController();
  var _storageString = "";

  //数据库名
  final dbName = "my_db.db";

  /*
   * 利用Sqflite数据库存储数据
   */
  Future saveString() async {
    final db = await getDatabase(dbName);
    //写入字符串
    db.transaction((trx) {
      var sql =
          'insert into user(name) values("${_textFieldController.value.text.toString()}")';
      return trx.rawInsert(sql);
    });
    Fluttertoast.showToast(msg: "保持成功", toastLength: Toast.LENGTH_SHORT);
  }

  /*
   * 获取存在Sqflite数据库中的数据
   */
  Future getString() async {
    final db = await getDatabase(dbName);
    var dbPath = db.path;
    setState(() {
      db.rawQuery('select * from user').then((List<Map> lists) {
        var listSize = lists.length;
        //获取数据库中的最后一条数据
        _storageString = lists[listSize - 1]['name'] +
            "\n现在数据库中一共有${listSize}条数据" +
            "\n数据库的存储路径为${dbPath}";
      });
    });
  }

  /*
   * 初始化数据存储路径
   */
  Future<Database> getDatabase(String dbName) async {
    //获取应用文件目录类似于Ios的NSDocumentDirectory和Android上的 AppData目录
    final fileDirectory = await getApplicationDocumentsDirectory();

    //获取存储路径
    final dbPath = fileDirectory.path;

    //构建数据库对象
    Database database = await openDatabase(dbPath + "/" + dbName, version: 1,
        onCreate: (Database db, int version) async {
      await db.execute("create table user (id integer primary key, name text)");
    });
    return database;
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Sqlife数据存储"),
      ),
      body: new Column(
        children: <Widget>[
          Text("Sqflite数据库存储", textAlign: TextAlign.center),
          TextField(
            controller: _textFieldController,
          ),
          MaterialButton(
            onPressed: saveString,
            child: new Text("存储"),
            color: Colors.cyan,
          ),
          MaterialButton(
            onPressed: getString,
            child: new Text("获取"),
            color: Colors.deepOrange,
          ),
          Text('从Sqflite数据库中获取的值为  $_storageString'),
        ],
      ),
    );
  }
}
