// ignore_for_file: unused_field, unnecessary_null_comparison

import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/common/resources.dart';
// import 'package:flutter_webview_plugin/flutter_webview_plugin.dart';

import '../../utils/net_utils.dart';
import '../../utils/shared_preference_utils.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  var isLoading = true;

  // late FlutterWebviewPlugin _flutterWebviewPlugin;

  @override
  void initState() {
    super.initState();
    // _flutterWebviewPlugin = FlutterWebviewPlugin()
    //   ..onUrlChanged.listen((event) {
    //     if (mounted) {
    //       setState(() {
    //         isLoading = false;
    //       });
    //     }
    //     //https://www.dongnaoedu.com/?code=6MhyQB&state=#
    //     print('url=$event');
    //     if (event != null && event.contains("?code")) {
    //       String code = event.split('?')[1].split('&')[0].split('=')[1];
    //
    //       Map<String, dynamic> params = Map();
    //       params['client_id'] = NetData.CLIENT_ID;
    //       params['client_secret'] = NetData.CLIENT_SECRET;
    //       params['grant_type'] = 'authorization_code';
    //       params['redirect_uri'] = NetData.REDIRECT_URI;
    //       params['code'] = code;
    //       params['dataType'] = 'json';
    //
    //       //{"access_token":"d98fea26-7308-4a1d-adb1-ff7e5b044625",
    //       // "refresh_token":"a581c1e0-7729-44c1-84b6-a652fdc37e7c",
    //       // "uid":4544532,"token_type":"bearer","expires_in":512280}
    //
    //       NetUtils.get(NetData.OAUTH2_TOKEN, params).then((value) {
    //         print('value=$value');
    //         Map<String, dynamic> decode = json.decode(value);
    //         SharePreferenceUtils.saveData(decode);
    //       });
    //     }
    //   });
  }

  @override
  Widget build(BuildContext context) {
    // return WebviewScaffold(
    //   url: '${NetData.OAUTH2_AUTHORIZE}?'
    //       'response_type=code&client_id=${NetData.CLIENT_ID}&'
    //       'redirect_uri=${NetData.REDIRECT_URI}',
    //   appBar: AppBar(
    //     title: Row(
    //       children: <Widget>[
    //         const Text(
    //           '开源中国登录',
    //           style: TextStyle(color: Colors.white),
    //         ),
    //         isLoading ? const CupertinoActivityIndicator() : Container()
    //       ],
    //     ),
    //   ),
    // );

    return Scaffold(
      body: Text("文本"),
    );
  }
}
