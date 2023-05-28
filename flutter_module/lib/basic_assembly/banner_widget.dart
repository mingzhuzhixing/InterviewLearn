// ignore_for_file: slash_for_doc_comments

import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';

/**
 * banner widget
 *
 * layoutDirection属性用于指定布局的方向。其默认值为TextDirection.ltr(从左到右)。
 * location属性用于指定显示“横幅”的位置。它可以接收以下四个值之一：
    BannerLocation.topStartBannerLocation.topEndBannerLocation.bottomStartBannerLocation.bottomEnd

    https://www.yiibai.com/flutter/flutter-banner.html
 */
class BannerWidgetPage extends StatefulWidget {
  const BannerWidgetPage({Key? key}) : super(key: key);

  @override
  State<BannerWidgetPage> createState() => _BannerWidgetPageState();
}

class _BannerWidgetPageState extends State<BannerWidgetPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBarUtils.appBar("banner widget"),
      body: Container(
        padding: EdgeInsets.all(16),
        child: Align(
          alignment: Alignment.topCenter,
          child: Banner(
            message: 'Offer 20% off',
            location: BannerLocation.topEnd,
            color: Colors.red,
            child: Container(
              height: 186,
              width: 280,
              child: Image.network(
                'https://img1.baidu.com/it/u=377236965,657279963&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500',
                fit: BoxFit.cover,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
