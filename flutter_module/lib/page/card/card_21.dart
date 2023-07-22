import 'package:flutter/material.dart';
import 'package:flutter_module/page/card/cardSingle.dart';
import 'package:flutter_module/page/card/card_21_bean.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class Card21 extends CardSingle<Card_21_bean> {
  Card_21_bean card_21_bean;

  Card21(this.card_21_bean, {Key? key}) : super(key: key);

  @override
  Widget realView() {
    return Column(
      children: [
        Container(
          margin: EdgeInsets.only(top: 25.w),
          height: 100,
          width: 1.sw * 0.9,
          color: Colors.red,
          child: Text("我是真是的card 21 view"),
        ),
        Text(card_21_bean.name??""),
      ],
    );
  }

  @override
  void setData(Card_21_bean data) {}
}
