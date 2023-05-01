import 'package:flutter/material.dart';
import 'package:flutter_module/page/take_photo_page.dart';

///咨询
class NewsPage extends StatefulWidget {
  const NewsPage({Key? key}) : super(key: key);

  @override
  State<NewsPage> createState() => _NewsPageState();
}

class _NewsPageState extends State<NewsPage> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        TextButton(
            onPressed: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return TakePhotoPage();
              }));
            },
            child: Text("拍照/图片选择功能"))
      ],
    );
  }
}
