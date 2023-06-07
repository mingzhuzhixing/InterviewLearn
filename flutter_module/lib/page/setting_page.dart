import 'package:flutter/material.dart';

import '../utils/event_bus_utils.dart';
import '../utils/shared_preference_utils.dart';

class SettingPage extends StatefulWidget {
  const SettingPage({Key? key}) : super(key: key);

  @override
  State<SettingPage> createState() => _SettingPageState();
}

class _SettingPageState extends State<SettingPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("设置"),
      ),
      body: MaterialButton(
        child: const Text('退出登录'),
        onPressed: () {
          SharePreferenceUtils.clearLoginInfo();
          Navigator.of(context).pop();
          eventBus.fire(LogoutEvent());
        },
      ),
    );
  }
}
