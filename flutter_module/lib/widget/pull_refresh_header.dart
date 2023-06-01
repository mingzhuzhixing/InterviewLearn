import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

CustomHeader get buildCustomHeader {
  return CustomHeader(
    height: 100,
    builder: (context, mode) {
      Widget body;
      if (mode == RefreshStatus.idle) {
        body = const Text("pull down refresh");
      } else if (mode == RefreshStatus.refreshing) {
        body = const CupertinoActivityIndicator();
      } else if (mode == RefreshStatus.canRefresh) {
        body = const Text("release to refresh");
      } else if (mode == RefreshStatus.completed) {
        body = const Text("refreshCompleted!");
      } else {
        body = const Text("refresh Data");
      }
      return Container(
        height: 80.0,
        child: Center(
          child: body,
        ),
      );
    },
  );
}
