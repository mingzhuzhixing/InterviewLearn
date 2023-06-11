import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';

///可复用的ListView长列表
// ListView.builder({
//   Key key,
//   Axis scrollDirection: Axis.vertical,
//   bool reverse: false,
//   ScrollController controller,
//   bool primary,
//   ScrollPhysics physics,
//   bool shrinkWrap: false,
//   EdgeInsetsGeometry padding,
//   this.itemExtent,
//   @required IndexedWidgetBuilder itemBuilder,//item构建者
//   int itemCount,//item数量
//   bool addAutomaticKeepAlives: true,
//   bool addRepaintBoundaries: true,
// })
/**
 * ListView.builder适合列表项比较多或者列表项不确定的情况，下面看一下ListView.builder的核心参数列表：
 * itemBuilder：它是列表项的构建器，类型为IndexedWidgetBuilder，返回值为一个widget。当列表滚动到具体的index位置时，会调用该构建器构建列表项。
 * itemCount：列表项的数量，如果为null，则为无限列表。
 */
class ListViewWidgetBuildPage extends StatefulWidget {
  const ListViewWidgetBuildPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _MyListViewWidgetBuildPage();
  }
}

class _MyListViewWidgetBuildPage extends State<ListViewWidgetBuildPage> {
  List<ItemEntity> entityList = [];
  List<ItemEntity> entityChildList = [];

  // double initialScrollOffset = 0.0, //初始滚动位置
  // this.keepScrollOffset = true,//是否保存滚动位置
  final ScrollController _controller = ScrollController(initialScrollOffset: 0.0);

  /*
   * 初始化状态
   */
  @override
  void initState() {
    super.initState();
    for (int i = 0; i < 30; i++) {
      entityList.add(ItemEntity("Item $i", Icons.accessibility));
    }
    for (int i = 0; i < 3; i++) {
      entityChildList.add(ItemEntity("Item $i", Icons.accessibility));
    }

    _controller.addListener(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("ListView 动态数据"),
      ),
      backgroundColor: Colors.white,
      body: ListView.builder(
        controller: _controller,
        itemBuilder: (BuildContext context, int index) {
          return ItemView(entityList[index], entityChildList);
        },
        //itemExtent: 450.w, //列表项高度固定时，显式指定高度是一个好习惯(性能消耗小)
        itemCount: entityList.length,
      ),
    );
  }
}

/*
 * 渲染Item的实体类
 */
class ItemEntity {
  String title;
  IconData iconData;

  ItemEntity(this.title, this.iconData);
}

/*
 * itemView
 */
class ItemView extends StatelessWidget {
  ItemEntity itemEntity;
  List<ItemEntity> entityChildList;

  ItemView(this.itemEntity, this.entityChildList);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: Column(
        children: <Widget>[
          ListTile(
            leading: Icon(itemEntity.iconData),
            title: Text(itemEntity.title),
            subtitle: Text("长列表"),
          ),
          Container(
            margin: EdgeInsets.only(left: 30.w, right: 30.w),
            padding: EdgeInsets.only(left: 30.w, top: 20.w, right: 30.w, bottom: 20.w),
            decoration: BoxDecoration(
              color: Color(0xfff7f7f7),
              borderRadius: BorderRadius.all(Radius.circular(15.w)),
            ),
            child: ListView.builder(
              shrinkWrap: true,
              physics: BouncingScrollPhysics(),
              itemBuilder: (BuildContext context, int index) {
                return Row(
                  children: [
                    Text("左边"),
                    Spacer(),
                    Text(entityChildList[index].title),
                  ],
                );
              },
              itemCount: entityChildList.length,
            ),
          ),
          SizedBox(
            height: 0.2,
            child: Container(
              color: Colors.black,
            ),
          )
        ],
      ),
      onTap: () {
        Fluttertoast.showToast(
            msg: "点击了第 ${itemEntity.title} 个view", toastLength: Toast.LENGTH_SHORT);
      },
    );
  }
}
