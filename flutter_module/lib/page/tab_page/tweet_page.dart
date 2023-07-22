import 'package:flutter/material.dart';
import 'package:flutter_module/page/card/card_21.dart';
import 'package:flutter_module/page/card/card_21_bean.dart';
import 'package:flutter_module/page/card/card_85.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class TweetPage extends StatefulWidget {
  const TweetPage({Key? key}) : super(key: key);

  @override
  State<TweetPage> createState() => _TweetPageState();

  void seh() {}
}

class _TweetPageState extends State<TweetPage> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xffe1e0e0),
      body: Column(
        children: [
          SizedBox(height: 30.w),
          Card_85(0),
          SizedBox(height: 5.w),
          Card_85(1),
          SizedBox(height: 5.w),
          Card_85(2),
          SizedBox(height: 5.w),
          Card_85(3)
        ],
      ),
    );
  }
}
