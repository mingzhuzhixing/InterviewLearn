import 'package:flutter/material.dart';
import 'package:flutter_module/page/card/cardBean.dart';
import 'package:flutter_module/page/card/cardView.dart';

abstract class CardSingle<T extends CardBean> extends CardView {
  CardSingle({Key? key});

  @override
  State<CardSingle> createState() => _CardSingleState();

  @override
  void initView(BuildContext context) {}

  // 设置数据
  void setData(T data);

  @override
  void setDataObj(Object object) {}

  @override
  void updateUi() {}
}

class _CardSingleState extends State<CardSingle> {
  @override
  void initState() {
    super.initState();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      color: Colors.transparent,
      child: Container(
        child: widget.realView(),
      ),
    );
  }
}
