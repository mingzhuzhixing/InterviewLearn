// ignore_for_file: slash_for_doc_comments

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/utils/appbar_utils.dart';
import 'package:flutter_module/widget/pull_refresh_footer.dart';
import 'package:flutter_module/widget/pull_refresh_header.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

/**
 * 下拉刷新加载
 * 手写效果：https://blog.csdn.net/weixin_44911775/article/details/124985084
 * https://www.jianshu.com/p/7bb0c735ba3a
 * https://www.6hu.cc/archives/75328.html
 * https://blog.csdn.net/u013491829/article/details/122308775
 */
class PullToRefreshPage extends StatefulWidget {
  const PullToRefreshPage({Key? key}) : super(key: key);

  @override
  State<PullToRefreshPage> createState() => _PullToRefreshPageState();
}

class _PullToRefreshPageState extends State<PullToRefreshPage> {
  List<String> items = ["1", "2", "3", "4", "5", "6", "7", "8"];
  final RefreshController _refreshController = RefreshController(initialRefresh: false);

  void _onRefresh() async {
    // monitor network fetch
    await Future.delayed(const Duration(milliseconds: 1000));
    // if failed,use refreshFailed()
    items = ["1", "2", "3", "4", "5", "6", "7", "8"];
    _refreshController.refreshCompleted();
    if (mounted) {
      setState(() {});
    }
  }

  void _onLoading() async {
    // monitor network fetch
    await Future.delayed(const Duration(milliseconds: 1000));
    // if failed,use loadFailed(),if no data return,use LoadNodata()
    items.add((items.length + 1).toString());
    if (mounted) {
      setState(() {});
    }
    _refreshController.loadComplete();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "pull_to_refresh"),
      body: SmartRefresher(
        enablePullUp: true,
        controller: _refreshController,
        header: buildCustomHeader,
        footer: buildCustomFooter,
        onRefresh: _onRefresh,
        onLoading: _onLoading,
        child: ListView.builder(
          physics: const ClampingScrollPhysics(),
          itemBuilder: (c, i) => Card(
            child: Center(
              child: Text(items[i], style: const TextStyle(color: Colors.black)),
            ),
          ),
          itemExtent: 100.0,
          itemCount: items.length,
        ),
      ),
    );
  }
}
