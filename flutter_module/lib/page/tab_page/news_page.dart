import 'package:flutter/material.dart';
import 'package:flutter_module/page/assemble_page/animation_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/base_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/container_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/event_notice_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/feture_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/layout_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/scrollable_assembly_page.dart';
import 'package:flutter_module/page/assemble_page/third_assembly_page.dart';
import 'package:flutter_module/widget/item_button.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

///咨询
class NewsPage extends StatefulWidget {
  const NewsPage({Key? key}) : super(key: key);

  @override
  State<NewsPage> createState() => _NewsPageState();
}

class _NewsPageState extends State<NewsPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        width: 1.sw,
        margin: EdgeInsets.only(top: 50.w),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              ItemButton("基础组件", BaseAssemblyPage(), index: 0),
              ItemButton("容器类组件", ContainerAssemblyPage(), index: 1),
              ItemButton("布局类组件", LayoutAssemblyPage(), index: 2),
              ItemButton("滚动组件", ScrollableAssemblyPage(), index: 3),
              ItemButton("功能型组件", FetureAssemblyPage(), index: 4),
              ItemButton("事件处理与通知组件", EventNoticeAssemblyPage(), index: 5),
              ItemButton("动画组件", AnimationAssemblyPage(), index: 6),
              ItemButton("第三方组件", ThirdAssemblyPage(), index: 7),
            ],
          ),
        ),
      ),
    );
  }
}
