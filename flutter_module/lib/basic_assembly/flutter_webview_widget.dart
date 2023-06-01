// ignore_for_file: slash_for_doc_comments

import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';

/**
 * flutter_webview
 *
 * android项目配置：
    android {
    compileSdkVersion 29
    defaultConfig {
    minSdkVersion 19
    targetSdkVersion 29
    }
    }
 *
 * gradle相应版本：
    gradle插件版本 ：4.1.* 以及上
    gradle版本 ：5.6 及以上

 * flutter_inappwebview需要依赖androidx和swift。
 *
 * https://blog.csdn.net/u013038616/article/details/119034807
 */
class WebviewWidgetPage extends StatefulWidget {
  const WebviewWidgetPage({Key? key}) : super(key: key);

  @override
  State<WebviewWidgetPage> createState() => _WebviewWidgetPageState();
}

class _WebviewWidgetPageState extends State<WebviewWidgetPage> {
  final GlobalKey webViewKey = GlobalKey();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "flutter_webview"),
    );
  }
}
