import 'package:flutter/material.dart';

class CardWidgetPage extends StatelessWidget {
  const CardWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("card wideget"),
        ),
        body: ListView(
          children: <Widget>[
            Card(
              margin: const EdgeInsets.all(10.0),
              elevation: 0.0,
              child: Column(
                children: const [
                  ListTile(
                    title: Text("张三", style: TextStyle(fontSize: 20.0)),
                    subtitle: Text("高级工程师"),
                  ),
                  Divider(
                    height: 2.0,
                    color: Colors.grey,
                    indent: 15.0,
                    endIndent: 15.0,
                  ),
                  ListTile(
                    title: Text("电话：xxxxxxxx"),
                  ),
                  Divider(
                    height: 2.0,
                    color: Colors.grey,
                    indent: 15.0,
                    endIndent: 15.0,
                  ),
                  ListTile(
                    title: Text("地址：xxxxxxxx"),
                  )
                ],
              ),
            ),
            Card(
              margin: const EdgeInsets.all(10.0),
              child: Column(
                children: const [
                  ListTile(
                    title: Text("李四", style: TextStyle(fontSize: 20.0)),
                    subtitle: Text("高级工程师"),
                  ),
                  Divider(
                    height: 2.0,
                    color: Colors.grey,
                    indent: 15.0,
                    endIndent: 15.0,
                  ),
                  ListTile(
                    title: Text("电话：xxxxxxxx"),
                  ),
                  Divider(
                    height: 2.0,
                    color: Colors.grey,
                    indent: 15.0,
                    endIndent: 15.0,
                  ),
                  ListTile(
                    title: Text("地址：xxxxxxxx"),
                  )
                ],
              ),
            ),
            Card(
              margin: const EdgeInsets.all(10.0),
              child: Column(
                children: const [
                  ListTile(
                    title: Text("王五", style: TextStyle(fontSize: 20.0)),
                    subtitle: Text("高级工程师"),
                  ),
                  Divider(
                    height: 2.0,
                    color: Colors.grey,
                    indent: 15.0,
                    endIndent: 15.0,
                  ),
                  ListTile(
                    title: Text("电话：xxxxxxxx"),
                  ),
                  Divider(
                    height: 2.0,
                    color: Colors.grey,
                    indent: 15.0,
                    endIndent: 15.0,
                  ),
                  ListTile(
                    title: Text("地址：xxxxxxxx"),
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
