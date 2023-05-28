import 'package:flutter/material.dart';
import 'package:flutter_module/basic_assembly/button_widget.dart';
import 'package:flutter_module/basic_assembly/card_widget.dart';
import 'package:flutter_module/basic_assembly/dialog_widget.dart';
import 'package:flutter_module/basic_assembly/file_storage_widget.dart';
import 'package:flutter_module/basic_assembly/gesture_detector_widget.dart';
import 'package:flutter_module/basic_assembly/gridview_widget.dart';
import 'package:flutter_module/basic_assembly/icon_widget.dart';
import 'package:flutter_module/basic_assembly/image_widget.dart';
import 'package:flutter_module/basic_assembly/expanded_widget.dart';
import 'package:flutter_module/basic_assembly/listview_widget.dart';
import 'package:flutter_module/basic_assembly/listview_widget_build.dart';
import 'package:flutter_module/basic_assembly/padding_widget.dart';
import 'package:flutter_module/basic_assembly/radio_widget.dart';
import 'package:flutter_module/basic_assembly/row_widget.dart';
import 'package:flutter_module/basic_assembly/sp_storage_widget.dart';
import 'package:flutter_module/basic_assembly/sqflite_storage_widget.dart';
import 'package:flutter_module/basic_assembly/stack_widget.dart';
import 'package:flutter_module/basic_assembly/swiper_widget.dart';
import 'package:flutter_module/basic_assembly/text_widget.dart';
import 'package:flutter_module/basic_assembly/textfield_widget.dart';
import 'package:flutter_module/basic_assembly/wrap_widget.dart';

///咨询
class NewsPage extends StatefulWidget {
  const NewsPage({Key? key}) : super(key: key);

  @override
  State<NewsPage> createState() => _NewsPageState();
}

class _NewsPageState extends State<NewsPage> {
  List<Color> colorList = [
    Colors.blue,
    Colors.green,
    Colors.yellow,
    Colors.red,
    Colors.grey,
    Colors.indigo,
    Colors.cyan,
    Colors.lime,
    Colors.lightBlue,
    Colors.pink,
    Colors.amber,
    Colors.deepOrange,
    Colors.lightGreen,
    Colors.orange,
    Colors.purple,
    Colors.teal
  ];

  List<ItemEntity> entityList = [
    ItemEntity("text_widget", TextWidgetPage()),
    ItemEntity("textfield_widget", TextFieldWidgetPage()),
    ItemEntity("button_widget", ButtonWidgetPage()),
    ItemEntity("icon_widget", IconWidgetPage()),
    ItemEntity("image_widget", ImageWidgetPage()),
    ItemEntity("stack_widget", StackWidgetPage()),
    ItemEntity("expanded_widget", ExpandedWidgetPage()),
    ItemEntity("gesture_detector_widget", GestureDetectorWidgetPage()),
    ItemEntity("padding_widget", PaddingWidgetPage()),
    ItemEntity("row_widget", const RowWidgetPage()),
    ItemEntity("listview_widget", ListViewWidgetPage()),
    ItemEntity("listview_widget_build", ListViewWidgetBuildPage()),
    ItemEntity("dialog_widget", DialogWidgetPage()),
    ItemEntity("card_widget", CardWidgetPage()),
    ItemEntity("warp_widget", WrapWidgetPage()),
    ItemEntity("swiper_widget", SwiperWidgetPage()),
    ItemEntity("radio_widget", RadioWidgetPage()),
    ItemEntity("gridview_widget", GridViewWidgetPage()),
    ItemEntity("file_storage_widget", FileStorageWidgetPage()),
    ItemEntity("sp_storage_widget", SpStorageWidgetPage()),
    ItemEntity("spflite_storage_widget", SqfliteStorageWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.separated(
        itemBuilder: (BuildContext context, int index) {
          // return itemButton(entityList[index], pageList[index],
          //     color: index % 2 == 0 ? Colors.white12 : Colors.black38);
          return itemButton(entityList[index].title, entityList[index].widget, index: index);
        },
        separatorBuilder: (BuildContext context, int index) {
          return const Divider(height: 10.0, color: Colors.transparent);
        },
        itemCount: entityList.length);
  }

  Widget itemButton(String title, Widget widget, {Color color = Colors.grey, int index = 0}) {
    return Container(
      margin: const EdgeInsets.only(left: 15, right: 15),
      child: MaterialButton(
          height: 50,
          onPressed: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) {
              return widget;
            }));
          },
          child: Text(
            title,
            style: const TextStyle(color: Colors.black, fontSize: 16, fontWeight: FontWeight.w500),
          ),
          color: colorList[index % colorList.length]),
    );

    // return GestureDetector(
    //   behavior: HitTestBehavior.opaque,
    //   onTap: () {
    //     Navigator.push(context, MaterialPageRoute(builder: (context) {
    //       return widget;
    //     }));
    //   },
    //   child: Container(
    //     width: double.infinity,
    //     alignment: Alignment.center,
    //     height: 50,
    //     color: color,
    //     child: Text(
    //       title,
    //       style: const TextStyle(color: Colors.black, fontSize: 16, fontWeight: FontWeight.w500),
    //     ),
    //   ),
    // );
  }
}

class ItemEntity {
  String title;
  Widget widget;

  ItemEntity(this.title, this.widget);
}
