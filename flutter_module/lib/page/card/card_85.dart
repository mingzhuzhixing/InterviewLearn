import 'package:flutter/material.dart';
import 'package:flutter_module/page/card/cardBean.dart';
import 'package:flutter_module/page/card/cardSingle.dart';
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
    }
    return defalutLayout();
  }

  Widget defalutLayout() {
    return Container(
      padding:
          EdgeInsets.only(left: 30.w, top: 36.w, right: 30.w, bottom: 36.w),
      decoration: BoxDecoration(
          color: Colors.white, borderRadius: BorderRadius.circular(20.w)),
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(16.w),
            child: Image.asset("assets/images/icon_cover.png",
                width: 186.w, height: 186.w),
          ),
          SizedBox(width: 16.w),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "草莓、极光与火焰",
                  style: TextStyle(fontSize: 30.sp, color: Color(0xff1F1F1F)),
                ),
                Text(
                  "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和见了科技,更预见了科技和见了科技",
                  overflow: TextOverflow.ellipsis,
                  maxLines: 2,
                  style: TextStyle(fontSize: 28.sp, color: Color(0xff969696)),
                ),
                Text(
                  "共5本 · 已学2%",
                  style: TextStyle(fontSize: 24.sp, color: Color(0xffb3b3b3)),
                )
              ],
            ),
          )
        ],
      ),
    );
  }

  Widget layout1() {
    return Container(
      padding:
          EdgeInsets.only(left: 30.w, top: 36.w, right: 30.w, bottom: 36.w),
      decoration: BoxDecoration(
          color: Colors.white, borderRadius: BorderRadius.circular(20.w)),
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(16.w),
            child: Image.asset("assets/images/icon_cover.png",
                width: 186.w, height: 186.w),
          ),
          SizedBox(width: 16.w),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "草莓、极光与火焰",
                  style: TextStyle(fontSize: 30.sp, color: Color(0xff1F1F1F)),
                ),
                Text(
                  "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和见了科技,更预见了科技和见了科技",
                  overflow: TextOverflow.ellipsis,
                  maxLines: 2,
                  style: TextStyle(fontSize: 28.sp, color: Color(0xff969696)),
                ),
                Row(
                  children: [
                    Text(
                      "共5本 · 已学2%",
                      style:
                          TextStyle(fontSize: 24.sp, color: Color(0xffb3b3b3)),
                    ),
                    Expanded(
                      child: Container(
                        height: 46.w,
                        width: 116.w,
                        color: Colors.red,
                        child: TextButton(
                          style: ButtonStyle(
                              padding: ButtonStyleButton.allOrNull(
                                  EdgeInsetsDirectional.all(0))),
                          onPressed: () {},
                          child: Text(
                            "去听书",
                            style: TextStyle(fontSize: 24.sp),
                          ),
                        ),
                      ),
                    )
                  ],
                )
              ],
            ),
          )
        ],
      ),
    );
  }

  Widget layout2() {
    return Container(
      padding:
          EdgeInsets.only(left: 30.w, top: 36.w, right: 30.w, bottom: 36.w),
      decoration: BoxDecoration(
          color: Colors.white, borderRadius: BorderRadius.circular(20.w)),
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(16.w),
            child: Image.asset("assets/images/icon_cover.png",
                width: 186.w, height: 186.w),
          ),
          SizedBox(width: 16.w),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "草莓、极光与火焰",
                  style: TextStyle(fontSize: 30.sp, color: Color(0xff1F1F1F)),
                ),
                Text(
                  "他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技和见了科技,更预见了科技和见了科技",
                  overflow: TextOverflow.ellipsis,
                  maxLines: 2,
                  style: TextStyle(fontSize: 28.sp, color: Color(0xff969696)),
                ),
                Text(
                  "共5本 · 已学2%",
                  style: TextStyle(fontSize: 24.sp, color: Color(0xffb3b3b3)),
                )
              ],
            ),
          )
        ],
      ),
    );
  }

  @override
  void setData(CardBean data) {}
}
