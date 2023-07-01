import 'dart:async';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_webview_plugin/flutter_webview_plugin.dart';

/**
 * flutter_webview
 *
 * flutter_inappwebview需要依赖androidx和swift。
 *
 * https://blog.csdn.net/u013038616/article/details/119034807
 */
class WebviewWidgetPage extends StatefulWidget {
  WebviewWidgetPage({Key? key}) : super(key: key);

  @override
  State<WebviewWidgetPage> createState() => _WebviewWidgetPageState();
}

class _WebviewWidgetPageState extends State<WebviewWidgetPage> {
  final String TAG = "webview";
  late String selectUrl = "http://www.baidu.com";

  @override
  Widget build(BuildContext context) {
    return WebviewScaffold(
      url: selectUrl,
      appBar: CommonAppBar(context, "flutter webview"),
    );
  }
}
