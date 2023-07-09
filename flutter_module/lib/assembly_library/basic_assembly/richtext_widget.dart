import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';

/**
 * RichText 富文本标签
 *
 * 参数:
 * RichText参数	              类型	                     说明
 * overflow	                 TextOverflow	              对不可见文本操作
 * maxLines	                 int	                      用来设置最大行数
 * textAlign               	 TextAlign	                对齐属性
 * textScaleFactor	         double	                    文字放大缩小倍数
 * textDirection	           TextDirection	            文本排列方式
 * text	                     TextSpan	                  必传参数,用来展示文本
 * recognizer	               TapGestureRecognizer	      手势监听
 */
class RichTextWidgetPage extends StatefulWidget {
  const RichTextWidgetPage({Key? key}) : super(key: key);

  @override
  State<RichTextWidgetPage> createState() => _RichTextWidgetPageState();
}

class _RichTextWidgetPageState extends State<RichTextWidgetPage> {
  final TapGestureRecognizer _recognizer1 = TapGestureRecognizer();
  final TapGestureRecognizer _recognizer2 = TapGestureRecognizer();

  @override
  void initState() {
    super.initState();
    _recognizer1.onTap = () {
      Fluttertoast.showToast(msg: "点击了用户协议");
    };
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "RichText widget"),
      body: Column(
        children: [
          SizedBox(height: 20.w),
          Container(
            //ListView可滑动
            child: RichText(
              overflow: TextOverflow.ellipsis,
              maxLines: 3,
              //必传文本
              text: TextSpan(
                text: "请认真阅读并理解",
                style: TextStyle(color: Colors.grey),
                //手势监听
                // recognizer: ,
                children: [
                  TextSpan(
                    text: "<用户协议>",
                    style: TextStyle(color: Colors.blueAccent),
                    recognizer: _recognizer1,
                  ),
                  TextSpan(text: "与", style: TextStyle(color: Colors.grey)),
                  TextSpan(
                    text: "<隐私协议>",
                    style: TextStyle(color: Colors.blueAccent),
                    recognizer: _recognizer2
                      ..onTap = () {
                        Fluttertoast.showToast(msg: "点击了隐私协议222");
                      },
                  ),
                  TextSpan(
                    text:
                        "我们一向尊重并会严格保护用户在使用本产品时的合法权益（包括用户隐私、用户数据等）不受到任何侵犯。本协议（包括本文最后部分的隐私政策）是用户（包括通过各种合法途径获取到本产品的自然人、法人或其他组织机构，以下简称“用户”或“您”）与我们之间针对本产品相关事项",
                    style: TextStyle(color: Colors.grey),
                  )
                ],
              ),
            ),
          ),
          SizedBox(height: 20.w),
          Container(
            child: Text.rich(
              TextSpan(
                children: [
                  WidgetSpan(
                    child: Padding(
                      padding: EdgeInsets.only(bottom: 3.w, right: 10.w),
                      child: Image.asset(
                        "assets/images/wode_baiding_dengji.png",
                        width: 64.w,
                        height: 25.w,
                      ),
                    ),
                  ),
                  TextSpan(
                    text: "我们一向尊重并会严格保护用户在使用本产品时的合法权益（包括用户隐私、法人或其他组织机构，以下简称“用户”或“您”）与我们之间针对本产品相关事项",
                    style: TextStyle(color: Colors.grey),
                  ),
                  // WidgetSpan(
                  //     child: Text(
                  //   "我们一向尊重并会严格保护用户在使用本产品时的合法权益（包括用户隐私、用户数据等）不受到任何侵犯。或其他组织户”或“您”）与我们之间针对本产品相关事项",
                  //   style: TextStyle(color: Colors.grey),
                  // )),
                ],
              ),
              maxLines: 2,
              overflow: TextOverflow.ellipsis,
            ),
          )
        ],
      ),
    );
  }

  @override
  void dispose() {
    super.dispose();
    _recognizer1.dispose();
    _recognizer2.dispose();
  }
}
