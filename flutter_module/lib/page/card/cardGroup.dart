import 'package:flutter/material.dart';
import 'package:flutter_module/page/card/cardBean.dart';
import 'package:flutter_module/page/card/cardView.dart';

abstract class CardGroup<T extends CardBean> extends CardView {
  @override
  State<StatefulWidget> createState() => _CardGroupState();

  @override
  void initView(BuildContext context) {

  }

  Widget titleView(int layout);

  // 设置数据
  void setData(T data);

  @override
  void setDataObj(Object object) {

  }

  @override
  void updateUi() {

  }
}

class _CardGroupState extends State<CardGroup> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text(""),
    );
  }
}