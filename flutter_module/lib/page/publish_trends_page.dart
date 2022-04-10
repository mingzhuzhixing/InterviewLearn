import 'package:flutter/material.dart';
class PublishTrendsPage extends StatefulWidget {
  const PublishTrendsPage({Key? key}) : super(key: key);

  @override
  State<PublishTrendsPage> createState() => _PublishTrendsPageState();
}

class _PublishTrendsPageState extends State<PublishTrendsPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("发布动态"),
      ),
    );
  }
}

