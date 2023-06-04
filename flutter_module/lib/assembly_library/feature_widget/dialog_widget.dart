import 'package:flutter/material.dart';

class DialogWidgetPage extends StatelessWidget {
  const DialogWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("dialog widget"),
        ),
        body: Column(
          children: [
            MaterialButton(
              minWidth: 200,
              onPressed: () {
                simpleDialog(context);
              },
              child: const Text("SimpleDialog"),
              color: Colors.blue,
              highlightColor: Colors.lightBlueAccent,
              disabledColor: Colors.lightBlueAccent,
            ),
            MaterialButton(
              minWidth: 200,
              onPressed: () {
                alertDialog(context);
              },
              child: const Text("AlertDialog"),
              color: Colors.green,
              highlightColor: Colors.greenAccent,
              disabledColor: Colors.greenAccent,
            ),
            MaterialButton(
              minWidth: 200,
              onPressed: () {
                bottomSheetDialog(context);
              },
              child: const Text("BottomSheetDialog"),
              color: Colors.yellow,
              highlightColor: Colors.yellowAccent,
              disabledColor: Colors.yellowAccent,
            ),
            MaterialButton(
              minWidth: 200,
              onPressed: () {
                modalBottomSheetDialog(context);
              },
              child: const Text("ModalBottomSheetDialog"),
              color: Colors.red,
              highlightColor: Colors.redAccent,
              disabledColor: Colors.redAccent,
            ),
            MaterialButton(
              minWidth: 200,
              onPressed: () {
                _showAboutDialog(context);
              },
              child: const Text("AboutDialog"),
              color: Colors.red,
              highlightColor: Colors.purpleAccent,
              disabledColor: Colors.purple,
            )
          ],
        ));
  }
}

///simpledialog
///SimpleDialog跟它的名字一样，它就是一个简单的对话框，开发者只需传入title跟child就可以使用它，
void simpleDialog(BuildContext context) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return const SimpleDialog(
        title: Text("标题"),
        contentPadding: EdgeInsets.all(10.0),
        children: <Widget>[
          Text("文字内容1"),
          ListTile(
            leading: Icon(Icons.android),
            title: Text("android"),
          ),
          Text("文字内容2"),
          Text("文字内容3"),
          Text("文字内容4")
        ],
      );
    },
  );
}

///AlertDialog
///AlertDialog其实就是simpleDialog的封装，更加方便开发者使用，只不过在SimpleDialog的基础上新增了action操作而已，用户可以定制具体类似，“取消”、“确定”等一切可能存在dialog上的逻辑处理
void alertDialog(BuildContext context) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text("标题"),
        content: const Text("内容区域"),
        actions: <Widget>[
          FlatButton(
              onPressed: () {
                print("点击了确定");
              },
              child: const Text("确定")),
          FlatButton(
              onPressed: () {
                print("点击了取消");
              },
              child: const Text("取消"))
        ],
      );
    },
  );
}

///BottomSheetDialog
///是需要借助showDialog唤起,dialog是从屏幕下方向上弹出的，而且是BottomSheetDialog默认会铺满全屏显示
///注意 使用时需要放到Builder()中
void bottomSheetDialog(BuildContext context) {
  print("zm1234 bottomSheetDialog");
  showBottomSheet(
    context: context,
    builder: (BuildContext context) {
      return Container(
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              ListTile(
                leading: Icon(Icons.chat),
                title: Text("对话框列表1"),
              ),
              ListTile(
                leading: Icon(Icons.chat),
                title: Text("对话框列表2"),
              ),
              ListTile(
                leading: Icon(Icons.chat),
                title: Text("对话框列表3"),
              ),
              ListTile(
                leading: Icon(Icons.chat),
                title: Text("对话框列表4"),
              )
            ],
          ),
        ),
      );
    },
  );
}

///ModalBottomSheetDialog
///是需要借助showDialog唤起,dialog是从屏幕下方向上弹出的，而且是BottomSheetDialog默认会铺半屏显示
void modalBottomSheetDialog(BuildContext context) {
  showModalBottomSheet(
    context: context,
    builder: (BuildContext context) {
      return Container(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            children: const [
              ListTile(
                leading: Icon(Icons.chat),
                title: Text("对话框列表1"),
              ),
              ListTile(
                leading: Icon(Icons.help),
                title: Text("对话框列表2"),
              ),
              ListTile(
                leading: Icon(Icons.settings),
                title: Text("对话框列表3"),
              ),
              ListTile(
                leading: Icon(Icons.more),
                title: Text("对话框列表4"),
              )
            ],
          ),
        ),
      );
    },
  );
}

/**
 * AboutDialog用于描述当前App信息，底部提供2个按钮：查看许可按钮和关闭按钮。AboutDialog需要和showAboutDialog配合使用，
 */
void _showAboutDialog(BuildContext context) {
  showAboutDialog(
    context: context,
    applicationIcon: Image.asset(
      'assets/images/cover_img.jpg',
      height: 100,
      width: 100,
    ),
    applicationName: '应用程序',
    applicationVersion: '1.0.0',
    applicationLegalese: 'copyright 老孟，一枚有态度的程序员',
    children: <Widget>[
      Container(
        height: 30,
        color: Colors.red,
      ),
      Container(
        height: 30,
        color: Colors.blue,
      ),
      Container(
        height: 30,
        color: Colors.green,
      )
    ],
  );
}
