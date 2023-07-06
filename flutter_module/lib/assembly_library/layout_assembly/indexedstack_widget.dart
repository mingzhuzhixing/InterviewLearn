import 'package:flutter/material.dart';
/**
 *1. IndexedStackIndexedStack 是 Stack 的子类。与 Stack 不同，IndexedStack 一次最多只显示一个小部件，并隐藏其他小部件。
 * 可以通过 index 属性指定要显示的子小部件。如果索引为null，则不会显示任何子小部件。
 * 2、IndexedStack 的大小尽可能小，并尝试大于其所有子级(除了 Positioned 或 Transform )。可以通过将 IndexedStack 放置在 SizedBox 中来控制它的大小。
 *
 * https://www.yiibai.com/flutter/flutter-indexedstack.html
 */
class IndexedStackWidgetPage extends StatefulWidget {
  const IndexedStackWidgetPage({Key? key}) : super(key: key);

  @override
  State<IndexedStackWidgetPage> createState() => _IndexedStackWidgetPageState();
}

class _IndexedStackWidgetPageState extends State<IndexedStackWidgetPage> {
  int selectedIndex = 1;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("IndexedStack widget"),
      ),
      body: SizedBox(
        width: double.infinity,
        height: double.infinity,
        child: IndexedStack(
          alignment: Alignment.center,
          index: selectedIndex,
          children: <Widget>[
            Container(
              width: 290,
              height: 210,
              color: Colors.green,
            ),
            Container(
              width: 250,
              height: 170,
              color: Colors.red,
            ),
            Container(
              width: 220,
              height: 150,
              color: Colors.yellow,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Text("Next"),
        onPressed: () {
          setState(() {
            if (selectedIndex < 2) {
              selectedIndex++;
            } else {
              selectedIndex = 0;
            }
          });
        },
      ),
    );
  }
}
