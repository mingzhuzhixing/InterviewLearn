import 'package:flutter/material.dart';
import 'package:flutter_module/assembly_library/basic_assembly/banner_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/button_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/card_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/expandicon_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/icon_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/image_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/linear_progress_indicator_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/radio_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/radiolisttile_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/swiper_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/tab_tabbar_controller_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/text_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/textfield_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/tooltip_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/top_tabbar_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/vertical_text_widget.dart';
import 'package:flutter_module/assembly_library/basic_assembly/wrap_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/align_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/aspectratio_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/center_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/constrainedbox_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/container_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/decoratedbox_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/dotted_border_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/fractionallysizedbox_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/padding_widget.dart';
import 'package:flutter_module/assembly_library/container_assembly/sizebox_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/datetime_picker_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/dialog_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/event_bus_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/file_storage_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/gesture_detector_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/sp_storage_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/sqflite_storage_widget.dart';
import 'package:flutter_module/assembly_library/feature_widget/willpopscope_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/column_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/expanded_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/flex_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/flexible_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/flow_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/indexedstack_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/offstage_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/positioned_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/row_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/spacer_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/stack_widget.dart';
import 'package:flutter_module/assembly_library/layout%20_assembly/visibility_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/customscrollview_vidget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/gridview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/listview_widget_build.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/pull_to_refresh_widget.dart';
import 'package:flutter_module/assembly_library/scrollable_assembly/webview_widget.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

///咨询
class NewsPage extends StatefulWidget {
  const NewsPage({Key? key}) : super(key: key);

  @override
  State<NewsPage> createState() => _NewsPageState();
}

class _NewsPageState extends State<NewsPage> {
  List<Color> colorList = [
    Colors.white,
    Colors.grey,
  ];

  List<ItemEntity> entityList = [
    ItemEntity("基础组件", null, type: "1"),
    ItemEntity("banner_widget", BannerWidgetPage()),
    ItemEntity("button_widget", ButtonWidgetPage()),
    ItemEntity("card_widget", CardWidgetPage()),
    ItemEntity("expandicon_widget", ExpandIconWidgetPage()),
    ItemEntity("text_widget", TextWidgetPage()),
    ItemEntity("textfield_widget", TextFieldWidgetPage()),
    ItemEntity("icon_widget", IconWidgetPage()),
    ItemEntity("image_widget", ImageWidgetPage()),
    ItemEntity("linear_progress_indicator_widget", LinearProgressIndicatorWidgetPage()),
    ItemEntity("swiper_widget", SwiperWidgetPage()),
    ItemEntity("radio_widget", RadioWidgetPage()),
    ItemEntity("radiolisttile_widget", RadioListTileWidgetPage()),
    ItemEntity("tooltip", TooltipWidgetPage()),
    ItemEntity("top_tabbar_widget", TopTabBarPage()),
    ItemEntity("top_tabbar_controller_widget", TabBarControllerPage()),
    ItemEntity("vertical_text_widget", VerticalTextPage()),
    //-----------------------------------------------------------------------------------
    ItemEntity("容器类组件", null, type: "1"),
    ItemEntity("align_widget", AlignWidgetPage()),
    ItemEntity("aspectradio_widget", AspectRatioWidgetPage()),
    ItemEntity("center_widget", CenterWidgetPage()),
    ItemEntity("constrainedbox_widget", ConstrainedBoxWidgetPage()),
    ItemEntity("container_widget", ContainerWidgetPage()),
    ItemEntity("decoratedbox_widget", DecoratedBoxWidgetPage()),
    ItemEntity("dotted_border_widget", DottedBorderWidgetPage()),
    ItemEntity("fractionallysizedbox_widget", FractionallySizedBoxWidgetPage()),
    ItemEntity("padding_widget", PaddingWidgetPage()),
    ItemEntity("sizebox_widget", SizeBoxWidgetPage()),
    //-----------------------------------------------------------------------------------
    ItemEntity("布局类组件-layout_widget", null, type: "1"),
    ItemEntity("column_widget", ColumnWidgetPage()),
    ItemEntity("expanded_widget", ExpandedWidgetPage()),
    ItemEntity("flex_widget", FlexWidgetPage()),
    ItemEntity("flexible_widget", FlexibleWidgetPage()),
    ItemEntity("flow_widget", FlowWidgetPage()),
    ItemEntity("positioned_widget", PositionedWidgetPage()),
    ItemEntity("row_widget", RowWidgetPage()),
    ItemEntity("spacer_widget", SpacerWidgetPage()),
    ItemEntity("stack_widget", StackWidgetPage()),
    ItemEntity("visibility_widget", VisibilityWidgetPage()),
    ItemEntity("indexstack_widget", IndexedStackWidgetPage()),
    ItemEntity("offstage_widget", OffstageWidgetPage()),
    ItemEntity("warp_widget", WrapWidgetPage()),
    //-----------------------------------------------------------------------------------
    ItemEntity("滚动组件", null, type: "1"),
    ItemEntity("customscrollview_vidget", CustomScrollViewPage()),
    ItemEntity("pull_to_refresh", PullToRefreshPage()),
    ItemEntity("gridview_widget", GridViewWidgetPage()),
    ItemEntity("listview_widget", ListViewWidgetPage()),
    ItemEntity("listview_widget_build", ListViewWidgetBuildPage()),
    ItemEntity("webview_widget", WebviewWidgetPage()),
    //-----------------------------------------------------------------------------------
    ItemEntity("功能型组件-feature_widget", null, type: "1"),
    ItemEntity("datepicker_widget", DateTimePickerBottomSheetPage()),
    ItemEntity("dialog_widget", DialogWidgetPage()),
    ItemEntity("eventbus_widget", EventBusWidgetPage()),
    ItemEntity("file_storage_widget", FileStorageWidgetPage()),
    ItemEntity("sp_storage_widget", SpStorageWidgetPage()),
    ItemEntity("gesture_detector_widget", GestureDetectorWidgetPage()),
    ItemEntity("spflite_storage_widget", SqfliteStorageWidgetPage()),
    ItemEntity("willpopscope_widget", WillPopScopeWidgetPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Color(0xffF5F5F5),
      child: ListView.separated(
          itemBuilder: (BuildContext context, int index) {
            return itemButton(
                entityList[index].title, entityList[index].type, entityList[index].widget,
                index: index);
          },
          separatorBuilder: (BuildContext context, int index) {
            return const Divider(height: 0.0, color: Colors.transparent);
          },
          itemCount: entityList.length),
    );
  }

  Widget itemButton(String title, String? type, Widget? widget,
      {Color color = Colors.grey, int index = 0}) {
    return Container(
      margin: const EdgeInsets.only(left: 0, right: 0),
      child: type == "1"
          ? Container(
              height: 40,
              padding: EdgeInsets.only(left: 10),
              alignment: Alignment.centerLeft,
              color: Colors.teal,
              child: Text(
                title,
                style: TextStyle(color: Colors.white, fontSize: 38.sp),
              ),
            )
          : MaterialButton(
              height: 50,
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return widget ?? Text("");
                }));
              },
              elevation: 0.0,
              child: Text(
                title,
                style:
                    const TextStyle(color: Colors.black, fontSize: 16, fontWeight: FontWeight.w500),
              ),
              color: colorList[index % colorList.length],
            ),
    );
  }
}

class ItemEntity {
  String title;
  Widget? widget;
  String? type;

  ItemEntity(this.title, this.widget, {this.type = "2"});
}
