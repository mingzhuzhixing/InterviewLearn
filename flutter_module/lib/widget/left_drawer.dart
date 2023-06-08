import 'package:flutter/material.dart';

import 'override_pending.dart';

class LeftDrawer extends StatefulWidget {
  dynamic imgPath;
  List listTitle;
  List listIcon;
  List listPage;

  LeftDrawer(
      {Key? key,
      required this.listTitle,
      required this.listIcon,
      required this.listPage,
      this.imgPath})
      : super(key: key);

  @override
  State<LeftDrawer> createState() => _LeftDrawerState();
}

class _LeftDrawerState extends State<LeftDrawer> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView.separated(
          //去除顶部间隙
          padding: const EdgeInsets.all(0.0),
          itemBuilder: (context, index) {
            if (index == 0) {
              return SizedBox(
                height: 300.0,
                child: Image.asset(
                  widget.imgPath,
                  fit: BoxFit.cover,
                ),
              );
            }
            index -= 1;
            return ListTile(
              title: Text(widget.listTitle[index]),
              leading: Icon(widget.listIcon[index]),
              trailing: const Icon(Icons.arrow_forward_ios),
              onTap: () {
                _openPage(widget.listPage[index]);
              },
            );
          },
          //分割线
          separatorBuilder: (context, index) {
            if (index == 0) {
              return const Divider(thickness: 0.0);
            }
            return const Divider(thickness: 1.0);
          },
          itemCount: widget.listTitle.length + 1),
    );
  }

  _openPage(Widget page) {
    // Navigator.of(context).push(MaterialPageRoute(builder: (context) {
    //   return page;
    // }));

    Navigator.of(context).push(SlidePageRouteBuilder(page));
  }
}
