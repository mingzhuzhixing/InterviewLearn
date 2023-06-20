import 'package:flutter/material.dart';

abstract class CardView extends StatefulWidget {

  CardView({Key? key}) : super(key: key) {
    impression();
    initCardClick();
  }

  void impression() {}

  void initCardClick() {}

  void initView(BuildContext context);

  Widget realView();

  void setDataObj(Object object);

  //更新ui
  void updateUi();
}
