import 'package:flutter/material.dart';

///page widget
class PaddingWidgetPage extends StatelessWidget {
  const PaddingWidgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("padding widget"),
      ),
      body: Padding(
        padding: const EdgeInsets.fromLTRB(0, 0, 10.0, 10.0),
        child: GridView.count(
          crossAxisCount: 2,
          childAspectRatio: 1.7,
          children: <Widget>[
            getImageWidget("https://www.itying.com/images/flutter/1.png"),
            getImageWidget("https://www.itying.com/images/flutter/2.png"),
            getImageWidget("https://www.itying.com/images/flutter/3.png"),
            getImageWidget("https://www.itying.com/images/flutter/4.png"),
            getImageWidget("https://www.itying.com/images/flutter/5.png"),
            getImageWidget("https://www.itying.com/images/flutter/6.png"),
            getImageWidget("https://www.itying.com/images/flutter/1.png"),
            getImageWidget("https://www.itying.com/images/flutter/2.png"),
            getImageWidget("https://www.itying.com/images/flutter/3.png"),
            getImageWidget("https://www.itying.com/images/flutter/4.png"),
            getImageWidget("https://www.itying.com/images/flutter/5.png"),
            getImageWidget("https://www.itying.com/images/flutter/6.png"),
          ],
        ),
      ),
    );
  }
}

/*
 * 获取图片widget
 */
Widget getImageWidget(String url) {
  return new Padding(
      padding: EdgeInsets.fromLTRB(10.0, 10.0, 0, 0),
      child: Image.network(
        url,
        fit: BoxFit.cover,
      ));
}
