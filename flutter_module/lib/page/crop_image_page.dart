import 'dart:io';
import 'dart:typed_data';

import 'package:crop_your_image/crop_your_image.dart';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

typedef CallBackFuture = void Function(String path);

/// 图片裁剪
class CropImagePage extends StatefulWidget {
  final Uint8List imageByte;
  final CallBackFuture _callBackFuture;

  CropImagePage(this.imageByte, this._callBackFuture);

  @override
  State<StatefulWidget> createState() {
    return CropImageState();
  }
}

class CropImageState extends State<CropImagePage> {
  final _controller = CropController();

  //将回调拿到的Uint8List格式的图片转换为File格式
  SaveImage(Uint8List imageByte) async {
    final tempDir = await getTemporaryDirectory();
    final file = await File(
            '${tempDir.path}/image_${DateTime.now().millisecondsSinceEpoch}.jpg')
        .create();
    file.writeAsBytesSync(imageByte);
    widget._callBackFuture(file.path);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Container(height: 24),
          Expanded(
            //Crop是裁剪控件
            child: Crop(
              image: widget.imageByte,
              controller: _controller,
              onCropped: (image) {
                //裁剪完成的回调
                SaveImage(image);
                Navigator.pop(context);
              },
              aspectRatio: 1,
              initialSize: 1,
              withCircleUi: false,
              baseColor: Colors.black,
              maskColor: Colors.black.withAlpha(150),
              cornerDotBuilder: (size, edgeAlignment) =>
                  const DotControl(color: Colors.white54),
            ),
          ),
          Container(
            padding: const EdgeInsets.fromLTRB(35, 16, 35, 16),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                TextButton(
                  onPressed: () {
                    Navigator.pop(context);
                  },
                  child:
                      const Text("取消", style: TextStyle(color: Colors.white)),
                ),
                TextButton(
                  onPressed: () {
                    //确认裁剪时调用这个方法
                    _controller.crop();
                  },
                  child:
                      const Text("选取", style: TextStyle(color: Colors.white)),
                )
              ],
            ),
          )
        ],
      ),
      backgroundColor: Colors.black,
    );
  }
}
