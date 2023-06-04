import 'package:flutter/material.dart';

class ImageWidgetPage extends StatelessWidget {
  const ImageWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Image Widget")),
      backgroundColor: Colors.grey,
      body: Center(
        child: CustomScrollView(
          slivers: [
            SliverList(
              delegate: SliverChildBuilderDelegate((BuildContext content, int index) {
                return Column(
                  children: <Widget>[
                    Image.network("https://p1.ssl.qhmsg.com/dr/220__/t01d5ccfbf9d4500c75.jpg",
                        width: 200, height: 100),
                    Image.asset("assets/images/ic_avatar_default.png",
                        height: 100, width: 100, scale: 1.0),
                    const ClipOvalWidget(),
                  ],
                );
              }, childCount: 1),
            ),
            SliverList(
              delegate: SliverChildBuilderDelegate((BuildContext content, int index) {
                //FadeInImage
                // 在加载网络图片时通常需要一张展位图，当网络图片没有加载时先显示占位图，FadeInImage可以很好的实现这个功能。
                return FadeInImage(
                  placeholder: AssetImage('assets/images/zhanweitu07.png'),
                  image: NetworkImage(
                      'https://img0.baidu.com/it/u=1677462811,726287569&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500'),
                );
              }, childCount: 1),
            )
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
