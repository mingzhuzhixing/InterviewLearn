import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

class ExpandIconWidgetPage extends StatefulWidget {
  const ExpandIconWidgetPage({Key? key}) : super(key: key);

  @override
  State<ExpandIconWidgetPage> createState() => _ExpandIconWidgetPageState();
}

class _ExpandIconWidgetPageState extends State<ExpandIconWidgetPage> {
  bool _expanded = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context,"ExpandIcon"),
      body: Column(
        children: [
          ExpandIcon(
            onPressed: (value) {
              setState(() {
                _expanded = !_expanded;
              });
            },
            isExpanded: _expanded,
          )
        ],
      ),
    );
  }
}
