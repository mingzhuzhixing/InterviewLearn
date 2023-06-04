import 'package:flutter/material.dart';

class ImageWidgetPage extends StatelessWidget {
  const ImageWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Image Widget")),
      body: Center(
        child: Column(
          children: <Widget>[
            Image.network(
                "https://p1.ssl.qhmsg.com/dr/220__/t01d5ccfbf9d4500c75.jpg",
                width: 200,
                height: 100),
            Image.asset("images/ic_avatar_default.png",
                height: 100, width: 100, scale: 1.0),
            const ClipOvalWidget(),
          ],
        ),
      ),
    );
  }
}

//  Image, 用于从ImageProvider获取图像。
//  Image.asset, 用于从AssetBundle获取图像。
//  Image.network, 用于从URL获取图像。
//  Image.file, 用于从文件中获取图像。
//  Image.memory, 用于从内存中获取图像

///ClipOval 设置圆行图片
class ClipOvalWidget extends StatelessWidget {
  const ClipOvalWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: ClipOval(
        child: Image.network(
          "https://p1.ssl.qhmsg.com/dr/220__/t01d5ccfbf9d4500c75.jpg",
          fit: BoxFit.cover,
          width: 200,
          height: 200,
        ),
      ),
    );
  }
}
