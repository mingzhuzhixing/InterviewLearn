import 'package:flutter/material.dart';
import 'package:flutter_swiper/flutter_swiper.dart';

class SwiperWidgetPage extends StatefulWidget {
  const SwiperWidgetPage({Key? key}) : super(key: key);

  @override
  _SwiperWidgetPageState createState() => _SwiperWidgetPageState();
}

class _SwiperWidgetPageState extends State<SwiperWidgetPage> {
  List<Map> imgList = [
    {"url": "http://www.itying.com/images/flutter/1.png"},
    {"url": "http://www.itying.com/images/flutter/2.png"},
    {"url": "http://www.itying.com/images/flutter/3.png"},
    {"url": "http://www.itying.com/images/flutter/4.png"},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("轮播图 Swiper Widget"),
      ),
      body: Container(
        width: double.infinity,
        child: AspectRatio(
          aspectRatio: 16 / 9,
          child: Swiper(
            itemBuilder: (BuildContext context, int index) {
              return Image.network(
                imgList[index]['url'],
                fit: BoxFit.fill,
              );
            },
            loop: true,
            //无线循环轮播
            autoplay: true,
            //自动轮播
            duration: 300,
            //动画时间，单位是毫秒
            itemCount: imgList.length,
            pagination: const SwiperPagination(), //底部分页器
            // control: new SwiperControl(), //左右控制器
          ),
        ),
      ),
    );
  }
}
