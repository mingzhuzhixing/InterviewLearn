import 'package:flutter/material.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/ys_colors.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class HtmlWidgetPage extends StatefulWidget {
  const HtmlWidgetPage({Key? key}) : super(key: key);

  @override
  State<HtmlWidgetPage> createState() => _HtmlWidgetPageState();
}

class _HtmlWidgetPageState extends State<HtmlWidgetPage> {
  String htmlData = r"""
      <p id='top'><a href='#bottom'>Scroll to bottom</a></p>
      <h6>Header 6</h6>
      <h3>Ruby Support:</h3>
      <p>The should be <span style='color: blue;'>BLUE style='color: blue;'</span></p>
      <p>The should be <span style='color: red;'>RED style='color: red;'</span></p>
      <p>The should be <span style='color: rgba(0, 0, 0, 0.10);'>BLACK with 10% alpha style='color: rgba(0, 0, 0, 0.10);</span></p>
      <p>The should be <span style='color: rgb(0, 97, 0);'>GREEN style='color: rgb(0, 97, 0);</span></p>
      <p>The should be <span style='background-color: red; color: rgb(0, 97, 0);'>GREEN style='color: rgb(0, 97, 0);</span></p>
      <p style="text-align: center;"><span style="color: rgba(0, 0, 0, 0.95);">blasdafjklasdlkjfkl</span></p>
      <p style="text-align: right;"><span style="color: rgba(0, 0, 0, 0.95);">blasdafjklasdlkjfkl</span></p>
      <p style="text-align: justify;"><span style="color: rgba(0, 0, 0, 0.95);">blasdafjklasdlkjfkl</span></p>
      <p style="text-align: center;"><span style="color: rgba(0, 0, 0, 0.95);">blasdafjklasdlkjfkl</span></p>
""";
  String fontData = "<em>课程</em>玛法的的<em>诚可贵</em>就是";
  String heightLight = "#4b9e65";
  String heightLight_14C586 = "#14C586";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "Html 组件"),
      body: SingleChildScrollView(
        child: Column(
          children: [
            Html(data: htmlData),
            Html(
              data: fontData
                  .replaceAll("<em>", "<font color=" + heightLight + ">")
                  .replaceAll("</em>", "</font>"),
            ),
            Html(
              data: fontData.replaceAll("<em>", "<font>").replaceAll("</em>", "</font>"),
              style: {
                "font":
                    Style(color: YsColor.hexToColor(heightLight_14C586), fontSize: FontSize(40.sp)),
              },
            ),
            Html(
              data: fontData.replaceAll("<em>", "<font>").replaceAll("</em>", "</font>"),
              style: {
                "font": Style(color: Color(0xFFEF9A9A), fontSize: FontSize(40.sp)),
              },
            ),
            Html(
              data: fontData
                  .replaceAll("<em>", "<font color=" + heightLight + ">")
                  .replaceAll("</em>", "</font>"),
              style: {
                "font": Style(color: Colors.red),
                "body": Style(
                  fontSize: FontSize(18.0),
                  fontWeight: FontWeight.bold,
                ),
              },
            ),
            Html(
              data: fontData,
              style: {
                "em": Style(color: Colors.blue),
              },
            ),
            Html(
              data: fontData.replaceAll("<em>", "<font>").replaceAll("</em>", "</font>"),
              style: {
                "font": Style(color: Colors.blue),
              },
            ),
            Html(
              data: fontData,
              style: {
                "body": Style(
                  fontSize: FontSize(18.0),
                ),
              },
            ),
            Html(
              data: fontData.replaceAll("<em>", "").replaceAll("</em>", ""),
              style: {
                "body": Style(
                  fontSize: FontSize(14.0),
                ),
              },
            ),
            SizedBox(height: 100.w)
          ],
        ),
      ),
    );
  }
}
