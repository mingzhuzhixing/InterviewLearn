import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * WillPopScope 组件
 *
 * WillPopScope用于处理是否离开当前页面，在Flutter中有多种方式可以离开当前页面，比如AppBar、CupertinoNavigationBar上面的返回按钮，
 * 点击将会回到前一个页面，在Android手机上点击实体（虚拟）返回按钮，也将会回到前一个页面，此功能对于iOS程序员来说可能特别容易忽略。
 *
 *  以下几种情况我们会用到WillPopScope：
 *   需要询问用户是否退出。
 *   App中有多个Navigator，想要的是让其中一个 Navigator 退出，而不是直接让在 Widget tree 底层的 Navigator 退出。
 *
 */
class WillPopScopeWidgetPage extends StatefulWidget {
  const WillPopScopeWidgetPage({Key? key}) : super(key: key);

  @override
  State<WillPopScopeWidgetPage> createState() => _WillPopScopeWidgetPageState();
}

class _WillPopScopeWidgetPageState extends State<WillPopScopeWidgetPage> {
  final GlobalKey<NavigatorState> _key = GlobalKey();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context,"WillPopScopeWidget"),
      body: WillPopScope(
          onWillPop: () async {
            if (_key.currentState!.canPop()) {
              _key.currentState!.pop();
              return false;
            }
            return true;
          },
          child: Column(
            children: <Widget>[
              Expanded(
                child: Navigator(
                  key: _key,
                  onGenerateRoute: (RouteSettings settings) =>
                      MaterialPageRoute(builder: (context) {
                    return OnePage();
                  }),
                ),
              ),
              Container(
                height: 50,
                color: Colors.blue,
                alignment: Alignment.center,
                child: Text('底部Bar'),
              )
            ],
          )),
    );
  }
}

class OnePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
          child: MaterialButton(
            child: Text('去下一个页面'),
            onPressed: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return TwoPage();
              }));
            },
          ),
        ),
      ),
    );
  }
}

class TwoPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
          child: Text('这是第二个页面'),
        ),
      ),
    );
  }
}
