import 'package:flutter/material.dart';
import 'package:flutter_module/page/card/cardBean.dart';
import 'package:flutter_module/page/card/cardSingle.dart';
import 'package:flutter_module/widget/ys_image_load.dart';
import 'package:flutter_module/widget/ys_image_style.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class Card_85 extends CardSingle {
  int layout;

  Card_85(this.layout, {Key? key}) : super(key: key);

  @override
  Widget realView() {
    switch (layout) {
      case 1:
        return layout1();
      case 2:
        return layout2();
      case 3:
        return layout3();
    }
    return defalutLayout();
  }

  Widget defalutLayout() {
    return Container(
      padding: EdgeInsets.only(left: 15.w, top: 18.w, right: 15.w, bottom: 18.w),
      decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(10.w)),
      child: Row(
        children: [
          YsImageLoad("assets/images/icon_cover.png",
              imageStyle: YsImageStyle(width: 93.w, height: 93.w),
              radius: 8.w,
              placeholder: "assets/images/zhanweitu01.png",
              errorholder: "assets/images/zhanweitu10.png"),
          SizedBox(width: 8.w),
          Expanded(
            child: SizedBox(
              height: 93.w,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "defalutLayout 草莓、极光与火焰",
                    style: TextStyle(fontSize: 15.sp, color: Color(0xff1F1F1F)),
                  ),
                  Text(
                    "他创造了太空歌剧的奇迹",
                    overflow: TextOverflow.ellipsis,
                    maxLines: 2,
                    style: TextStyle(fontSize: 14.sp, color: Color(0xff969696)),
                  ),
                  Spacer(),
                  Text(
                    "共5本 · 已学2%",
                    style: TextStyle(fontSize: 12.sp, color: Color(0xffb3b3b3)),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget layout1() {
    return Container(
      padding: EdgeInsets.only(left: 15.w, top: 18.w, right: 15.w, bottom: 18.w),
      decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(10.w)),
      child: Row(
        children: [
          YsImageLoad(
            'https://p1.ssl.qhmsg.com/dr/220__/t01d5ccfbf9d4500c75.jpg',
            imageStyle: YsImageStyle(width: 186.w, height: 186.w),
            radius: 16.w,
            placeholder: "assets/images/zhanweitu02.png",
            errorholder: "assets/images/zhanweitu04.png",
          ),
          SizedBox(width: 16.w),
          Expanded(
            child: SizedBox(
              height: 186.w,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "layout1 草莓、极光与火焰",
                    style: TextStyle(fontSize: 15.sp, color: Color(0xff1F1F1F)),
                  ),
                  Text(
                    "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和见了科技,更预见了科技和见了科技",
                    overflow: TextOverflow.ellipsis,
                    maxLines: 2,
                    style: TextStyle(fontSize: 14.sp, color: Color(0xff969696)),
                  ),
                  Spacer(),
                  Row(
                    children: [
                      Text(
                        "共5本 · 已学2%",
                        style: TextStyle(fontSize: 12.sp, color: Color(0xffb3b3b3)),
                      ),
                      Expanded(
                        child: Container(
                          height: 46.w,
                          width: 116.w,
                          color: Colors.red,
                          child: TextButton(
                            style: ButtonStyle(
                                padding: ButtonStyleButton.allOrNull(EdgeInsetsDirectional.all(0))),
                            onPressed: () {},
                            child: Text(
                              "去听书",
                              style: TextStyle(fontSize: 12.sp),
                            ),
                          ),
                        ),
                      )
                    ],
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget layout2() {
    return Container(
      padding: EdgeInsets.only(left: 30.w, top: 36.w, right: 30.w, bottom: 36.w),
      decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(20.w)),
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(16.w),
            child: Image.asset("assets/images/icon_cover.png", width: 186.w, height: 186.w),
          ),
          SizedBox(width: 16.w),
          Expanded(
            child: SizedBox(
              height: 186.w,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "草莓、极光与火焰",
                    style: TextStyle(fontSize: 15.sp, color: Color(0xff1F1F1F)),
                  ),
                  Text(
                    "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和见了科技,更预见了科技和见了科技",
                    overflow: TextOverflow.ellipsis,
                    maxLines: 2,
                    style: TextStyle(fontSize: 14.sp, color: Color(0xff969696)),
                  ),
                  Spacer(),
                  Text(
                    "共5本 · 已学2%",
                    style: TextStyle(fontSize: 12.sp, color: Color(0xffb3b3b3)),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget layout3() {
    return Container(
      padding: EdgeInsets.only(left: 30.w, top: 36.w, right: 30.w, bottom: 36.w),
      decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(20.w)),
      child: Row(
        children: [
          Stack(
            alignment: Alignment.centerRight,
            children: [
              Positioned(
                child: Image.asset(
                  "assets/images/ic_guangdie.png",
                  width: 170.w,
                  height: 170.w,
                ),
              ),
              ClipRRect(
                borderRadius: BorderRadius.circular(16.w),
                child: Padding(
                  padding: EdgeInsets.only(right: 22.w),
                  child: Image.asset("assets/images/icon_cover.png", width: 186.w, height: 186.w),
                ),
              ),
            ],
          ),
          SizedBox(width: 13.w),
          Expanded(
            child: SizedBox(
              height: 186.w,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "layout 3 草莓、极光与火焰",
                    style: TextStyle(fontSize: 15.sp, color: Color(0xff1F1F1F)),
                  ),
                  Text(
                    "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和见了科技,更预见了科技和见了科技",
                    overflow: TextOverflow.ellipsis,
                    maxLines: 2,
                    style: TextStyle(fontSize: 14.sp, color: Color(0xff969696)),
                  ),
                  Spacer(),
                  Text(
                    "共5本 · 已学2%",
                    style: TextStyle(fontSize: 12.sp, color: Color(0xffb3b3b3)),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  @override
  void setData(CardBean data) {}
}
