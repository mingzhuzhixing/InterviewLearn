import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/constant/native_methods.dart';
import 'package:flutter_module/utils/method_channel_utils.dart';

class DiscoverPage extends StatefulWidget {
  const DiscoverPage({Key? key}) : super(key: key);

  @override
  State<DiscoverPage> createState() => _DiscoverPageState();
}

class _DiscoverPageState extends State<DiscoverPage> {
  String flutter_android = ""; // flutter调用android,来自android的返回值

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(flutter_android),
        ElevatedButton(
            onPressed: () {
              MethodChannelUtils.nativeChannel
                  .invokeMethod(NativeMethods.invokeAndroid)
                  .then((value) {
                    setState(() {
                      flutter_android = value;
                    });
              });
            },
            child: Text("调用 android 方法")),
      ],
    );
  }
}
